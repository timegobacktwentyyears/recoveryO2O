package com.jt.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisSentinelService;
import com.jt.common.vo.ItemCatData;
import com.jt.common.vo.ItemCatResult;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemCat;

import redis.clients.jedis.JedisCluster;
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper;
	
	//@Autowired
	//private ShardRedisService redisService;
	
	//@Autowired
	//private RedisSentinelService redisService;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	private static final Logger LOGGER = Logger.getLogger(ItemCatServiceImpl.class);
	
	//将java数据和JSON串之间进行转化
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	//引入缓存机制
	/**
	 * 1.当用户通过parentId查询子级数据时,应该先从缓存中获取数据
	 * 2.如果获取的数据不为null.需要将字符串String转化为ItemCat对象
	 * 3.如果获取的数据为null,需要通过parentId从数据库中查询对应的信息后得到List<ItemCat>对象
	 * 4.需要将查询到的数据转化为JOSN串
	 * 5.将数据写入缓存中 返回用户结果
	 * 
	 */
	@Override
	public List<ItemCat> findItemCatList(Long parentId) {
		
		List<ItemCat> itemCatList = new ArrayList<ItemCat>();
		
		String itemCatKey = "ITEM_CAT_"+parentId;  //ITEM_CAT_0
		
		String JSONData = jedisCluster.get(itemCatKey);
		try {
			
			if(StringUtils.isEmpty(JSONData)){
				ItemCat itemCat = new ItemCat();
				itemCat.setParentId(parentId);
				itemCatList = itemCatMapper.select(itemCat);
				
				//[{id:name...,text:name值,state:'1'},{},{},{}]  以List集合为主
				String JSONResult = objectMapper.writeValueAsString(itemCatList);
				System.out.println(JSONResult);
				jedisCluster.set(itemCatKey, JSONResult);
				return itemCatList;
			}else{
				
				//需要将返回后的JSON数据转化为具体对象  
				//可以直接将对象转化为想要的格式 {id:1,name:tom,age:18}
				//由于为了实现EasyUI回显为pojo对象中添加了text属性和state这些属性在转化时必然报错.
				//因为其中没有对用的属性及set方法
				ItemCat[] itemCats = objectMapper.readValue(JSONData, ItemCat[].class);
				
				for (ItemCat itemCat : itemCats) {
					itemCatList.add(itemCat);
				}
				return itemCatList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		
		return itemCatList;
	}

	/**
	 * List<ItemCat> itemAllList = itemCatMapper.select(record)k
		
		List<ItemCat> onelist= itemCatMapper.select(OL)
		for (ItemCat itemCat : onelist) {
			准备一级菜单的数据
			
			List<ItemCat> twoList = itemCatMapper.select(一级菜单的Id)
			
			for (ItemCat itemCat2 : twoList) {
				List<ItemCat> twoList = itemCatMapper.select(一级菜单的Id)
			}
		}
		实际开发中这样的代码so bad
	 */
	
	/**
	 * 1.查询商品分类表的全部数据 应该是上架产品  where status = 1
	 * 2.准备一级商品分类菜单 where parent_id = 0;
	 * 3.根据一级菜单查询二级菜单 where parent_id = 一级菜单的Id
	 * 4.根据二级菜单查询三级菜单 where parent_id + 二级菜单的Id
	 * 5.根据以上代码的实现过程 会发现频繁的访问数据库.这样的访问次数可能达到上百次甚至是前次
	 */
	
	@Override
	public ItemCatResult findItemCatAll() {
		
		//表示查询商品的全部信息 上架的商品
		ItemCat itemCatAll = new ItemCat();
		itemCatAll.setStatus(1);
		List<ItemCat> itemCatList = itemCatMapper.select(itemCatAll);
		
		//定义一个数据中转的集合   long数据表示上级的Id  List<ItemCat> 当前父级下的所有子级菜单
		Map<Long, List<ItemCat>> mapItem = new HashMap<Long,List<ItemCat>>();
		
		/**
		 * 作用:通过map集合将全部数据的子父级关系进行保存
		 */
		for (ItemCat itemCat : itemCatList) {
			if(mapItem.containsKey(itemCat.getParentId())){
				//表示当前分类的上级已经存入集合中
				mapItem.get(itemCat.getParentId()).add(itemCat);
			}else{
				//当前集合中没有该父级
				List<ItemCat> itemCatMapList = new ArrayList<ItemCat>();
				itemCatMapList.add(itemCat);
				mapItem.put(itemCat.getParentId(), itemCatMapList);
			}
		}
		
		//定义一级菜单list集合
		List<ItemCatData> itemCatList1 = new ArrayList<ItemCatData>();
		//循环遍历一级菜单
		for (ItemCat itemCat : mapItem.get(0L)) {
			ItemCatData itemCatData1 = new ItemCatData();
			itemCatData1.setUrl("/products/"+itemCat.getId()+".html");
			itemCatData1.setName("<a href='"+itemCatData1.getUrl()+"'>"+itemCat.getName()+"</a>");
			
			//准备当前菜单下的二级菜单
			List<ItemCatData> itemCatList2 = new ArrayList<ItemCatData>();
			for (ItemCat itemCat2 :mapItem.get(itemCat.getId())) {
				ItemCatData itemCatData2 = new ItemCatData();
				itemCatData2.setUrl("/products/"+itemCat2.getId());
				itemCatData2.setName(itemCat2.getName());
				
				//准备3级分类菜单
				List<String> itemCatList3 = new ArrayList<String>();
				for (ItemCat itemCat3 :mapItem.get(itemCat2.getId())) {
					itemCatList3.add("/products/"+itemCat3.getId()+"|"+itemCat3.getName());
				}
				
				itemCatData2.setItems(itemCatList3);
				itemCatList2.add(itemCatData2);
			}
			
			//将2级菜单list集合存入一级菜单对象
			itemCatData1.setItems(itemCatList2);
			
			if(itemCatList1.size()>13)
			break;

			//将一级菜单的对象保存入list集合中
			itemCatList1.add(itemCatData1);
		}

		ItemCatResult itemCatResult = new ItemCatResult();
		itemCatResult.setItemCats(itemCatList1);
		
		
		return itemCatResult;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
