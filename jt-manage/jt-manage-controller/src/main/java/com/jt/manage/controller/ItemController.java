package com.jt.manage.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	//获取日志对象
	private static final Logger logger = Logger.getLogger(ItemController.class);
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/findAll")
	@ResponseBody  //返回值是String  则以原样返回  如果是对象则转化为JSON串 
	public List<Item> findAll(){
		
		return itemService.findAll();
		
	}
	
	//测试通用Mapper的方法
	/*@RequestMapping("/findMapper")
	@ResponseBody  //返回值是String  则以原样返回  如果是对象则转化为JSON串 
	public List<Item> findMapper(){
		
		//表示测试通用Mapper
		return itemService.findMapper();
		
	}*/
	
	
	//url:http://localhost:8091/item/query?page=1&rows=20
	@RequestMapping("/query")
	@ResponseBody  //将返回值数据进行对象封装,通过注解自动的转化为JSON串
	public EasyUIResult findItemList(int page,int rows){
		
		return itemService.findItemList(page,rows);
	}
	
	
	//查询商品分类名称
	/*@RequestMapping("/query/name")
	public void findItemCatName(Long cid,HttpServletResponse response) throws IOException{
		
		String name = itemService.findItemCatName(cid);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(name);
	}*/
	
	//Ajax提交会有乱码问题 和正常的页面提交的方式不同  springMVC内部处理不一样     
	//将返回值进行utf-8编码
	@RequestMapping(value="/query/name",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findItemCatName(Long cid,HttpServletResponse response){
		
		return itemService.findItemCatName(cid);
		
	}
	
	
	//商品的新增      desc表示商品描述信息
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveItem(Item item,String desc){
		try {
			itemService.saveItem(item,desc);
			//System.out.println("");
			logger.info("{商品新增成功}");  //速度更优
			return SysResult.build(200, "商品新增成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("{新增商品失败}");
			return SysResult.build(201, "商品新增失败");
		}
	}
	
	//商品的修改      desc表示商品的描述信息
	@RequestMapping("/update")
	@ResponseBody
	public SysResult updateItem(Item item,String desc){
		try {
			itemService.updateItem(item,desc);
			logger.info("{商品修改成功}");
			return SysResult.build(200, "商品修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("{修改商品失败}");
			return SysResult.build(201, "商品修改失败");
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteItems(Long[] ids){
		
		try {
			itemService.deleteItems(ids);
			logger.info("{商品删除成功"+Arrays.toString(ids)+"}");
			return SysResult.build(200, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("{"+e.getMessage()+"}");
			return SysResult.build(201, "删除失败");
		}
	}
	
	
	//商品的下架
	@RequestMapping("/instock")
	@ResponseBody
	public SysResult instockItem(Long[] ids){
		
		try {
			int status = 2;  //下架
			itemService.updateStatus(ids,status);
			
			logger.info("{商品下架成功}");
			return SysResult.build(200, "商品下架成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "商品下架失败");
		}
	}
	//商品的上架
	@RequestMapping("/reshelf")
	@ResponseBody
	public SysResult reshelfItem(Long[] ids){
		
		try {
			int status = 1;  //上架
			itemService.updateStatus(ids,status);
			
			logger.info("{商品上架成功}");
			return SysResult.build(200, "商品上架成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "商品上架失败");
		}
	}
	
	
	//根据itemId获取商品描述信息
	@RequestMapping("/query/item/desc/{itemId}")
	@ResponseBody
	public SysResult findItemDescById(@PathVariable Long itemId){
		
		try {
			ItemDesc itemDesc = itemService.findItemDescById(itemId);
			logger.info("{商品描述信息查询成功"+itemId+"}");
			return SysResult.build(200, "商品描述查询成功", itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("{商品描述信息查询失败}");
			return SysResult.build(201, "商品描述信息查询失败");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
}
