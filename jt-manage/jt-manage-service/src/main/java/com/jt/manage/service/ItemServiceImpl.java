package com.jt.manage.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

import redis.clients.jedis.JedisCluster;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@Value(value="${ITEM_KEY}")
	private String itemKey;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private static final Logger logger = Logger.getLogger(ItemServiceImpl.class);
	@Override
	public List<Item> findAll() {
		
		return itemMapper.findAll();
	}

	@Override
	public List<Item> findMapper() {
		/**
		 * 总结:
		 * 	1.当使用通用Mapper时,如果传入的对象为null 或者是一个空对象 这是通用Mapper将不会添加where条件
		 *    select * from tb_item
		 *  2.如果传入的对象某些属性不为null 这会以这些属性的值为where条件进行查询
		 *    select * from tb_item where cid = 12
		 *  3.多条件查询
		 *    对象中有多个不为null的属性,这时通用mapper会进行对条件查询
		 *    select * from tb_item where id = 1474391952 and cid = 12
		 */
		Item item = new Item();
		item.setCid(12L);
		item.setId(1474391952L);
		return itemMapper.select(item);
	}
	
	/**
	 * 由于前台页面采用EasyUI的方式进行调用 所以返回值应该满足EasyUI的要求
	 * {"total":2000,"rows":[{},{},{}]}   total 是记录总数   rows表示查询的数据
	 * 准备一个对象 对象中有属性:
	 * int total
	 * List<Item>   rows 
	 */
	/*@Override
	public EasyUIResult findItemList(int page, int rows) {
		*//**
		 * 分页的业务逻辑
		 * page 页数  rows 行数
		 * 第1页   SELECT * FROM tb_item ORDER BY updated DESC LIMIT 0,20
		 * 第2页   SELECT * FROM tb_item ORDER BY updated DESC LIMIT 20,20
		 * 第3页   SELECT * FROM tb_item ORDER BY updated DESC LIMIT 40,20
		 * 第n页   SELECT * FROM tb_item ORDER BY updated DESC LIMIT (page-1)*20,20
		 *//*
		int begin = (page-1)*rows;  //起始页数
		List<Item> itemList = itemMapper.findItemList(begin,rows);
		int count = itemMapper.selectCount(null);
		//sql:select count(*) from tb_item
		return new EasyUIResult(count, itemList);
	}*/
	
	
	/**
	 * page:当前的页数
	 * rows:展现的记录数
	 */
	@Override
	public EasyUIResult findItemList(int page, int rows) {
		//表示开始使用分发工具
		PageHelper.startPage(page, rows);
		
		//表示查询全部记录     3100     当前的查询操作,必须为之分页开始之后一行 否则报错.
		List<Item> itemList = itemMapper.findAll();
		System.out.println(itemList.size());
		
		PageInfo<Item> itemInfo = new PageInfo<Item>(itemList);
		
		return new EasyUIResult(itemInfo.getTotal(), itemList);
	}
	
	

	@Override
	public String findItemCatName(Long cid) {
		
		return itemMapper.findItemName(cid);
	}

	@Override
	public int findCount() {
		
		//查询的是tb_item表的记录数
		return itemMapper.findCount();
	}

	@Override
	public void saveItem(Item item,String desc) {
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		item.setStatus(1);  //表示正常
		//调用通用Mapper实现入库操作
		itemMapper.insert(item);
		
		//通过查询方式获取itemId  select * from tb_item where 
		
		//商品描述信息中的id号应该和itemid保存一致
		ItemDesc itemDesc = new ItemDesc();
		//System.out.println(item.getId()+"测试是否能够获取数据");
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
		
		/*
		 * 为了实现查询速度更快,将新增的数据插入到缓存中
		 * 定义redis中的key时,需要和前台一致,否则添加缓存没有任何效果
		 * 如果能够保证前后台的key值一致???
		 */
		try {
			String JSON = objectMapper.writeValueAsString(item);
			jedisCluster.set(itemKey+item.getId(), JSON);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
	}

	@Override
	public void updateItem(Item item,String desc) {
		
		item.setUpdated(new Date());
		//全部的修改  不管是否有数据 
		//itemMapper.updateByPrimaryKey(record)
		
		//动态修改
		itemMapper.updateByPrimaryKeySelective(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(item.getId());
		itemDesc.setUpdated(item.getUpdated());
		
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		
		//商品修改后应该将商品从缓存中删除
		jedisCluster.del(itemKey+item.getId());
	}

	@Override
	public void deleteItems(Long[] ids) {
		
		//关联删除
		itemDescMapper.deleteByIDS(ids);
		itemMapper.deleteByIDS(ids);
		
		for (Long id : ids) {
			jedisCluster.del(itemKey+id);
		}
		
	}

	@Override
	public void updateStatus(Long[] ids, int status) {
		
		itemMapper.updateStatus(ids,status);

		
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(itemId);
		return itemDescMapper.selectByPrimaryKey(itemDesc);
	}

	@Override
	public Item findItemById(Long itemId) {
		
		return itemMapper.selectByPrimaryKey(itemId);
	}

	
	
	
	
	
	
	
	
	
	
}
