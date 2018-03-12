package com.jt.manage.service;

import java.util.List;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

public interface ItemService {
	
	public List<Item> findAll();

	public List<Item> findMapper();
	
	public EasyUIResult findItemList(int page, int rows);

	public String findItemCatName(Long cid);
	
	//测试通用Mapper方法
	public int findCount();
	
	//商品的新增
	public void saveItem(Item item,String desc);
	
	//商品的修改
	public void updateItem(Item item,String desc);
	
	//表示批量删除
	public void deleteItems(Long[] ids);
	
	//修改商品的状态
	public void updateStatus(Long[] ids, int status);
	
	//根据itemId 查询商品描述信息
	public ItemDesc findItemDescById(Long itemId);
	
	//根据itemId查询商品信息
	public Item findItemById(Long itemId);
	
	
}
