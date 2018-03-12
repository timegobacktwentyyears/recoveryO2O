package com.jt.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private HttpClientService httpClientService;
	
	private static final Logger logger = Logger.getLogger(CartServiceImpl.class);
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public List<Cart> findCartListByUserId(Long userId) {
		//定义URL
		String url = "http://cart.jt.com/cart/query/"+userId;
		try {
			String sysResultJSON = httpClientService.doGet(url);
			
			//将JSON串转化为节点信息
			JsonNode jsonNode = objectMapper.readTree(sysResultJSON);
			
			//[{},{},{},{}]  获取JSON串中获取list集合数据
			String data = jsonNode.get("data").asText();
			
			//将JOSN数据转化为Carts对象
			Cart[] carts = objectMapper.readValue(data, Cart[].class);
			
			List<Cart> cartList = new ArrayList<Cart>();
			//将对象赋值到List集合中
			for (Cart cart : carts) {
				cartList.add(cart);
			}	
			return cartList;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public SysResult updateCartNum(Long userId, Long itemId, Integer num) {
		
		try {
			//定义url
			String url = "http://cart.jt.com/cart/update/num/"+userId+"/"+itemId+"/"+num;
			String sysResultJSON = httpClientService.doGet(url);
			
			SysResult sysResult = objectMapper.readValue(sysResultJSON, SysResult.class);
			return sysResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "商品数量修改失败");
		}
	}

	@Override
	public void deleteCart(Long userId, Long itemId) {
		String url = "http://cart.jt.com/cart/delete/"+userId+"/"+itemId;
		
		try {
			String sysResulthtJSON = httpClientService.doGet(url);
			objectMapper.readValue(sysResulthtJSON, SysResult.class);
			
			//保存数据   后期使用
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public SysResult insertCart(Cart cart) {
		String url = "http://cart.jt.com/cart/save";
		
		Map<String, String> cartMap = new HashMap<String,String>();
		cartMap.put("userId", cart.getUserId()+"");
		cartMap.put("itemId", cart.getItemId()+"");
		cartMap.put("itemTitle", cart.getItemTitle());
		cartMap.put("itemImage", cart.getItemImage());  //只保留一张图片
		cartMap.put("itemPrice", cart.getItemPrice()+"");
		cartMap.put("num", cart.getNum()+"");
		
		try {
			String sysResultJSON  = httpClientService.doPost(url, cartMap, "utf-8");
			SysResult sysResult = objectMapper.readValue(sysResultJSON, SysResult.class);
			return sysResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "商品的新增失败");
		}
		
	}
	
	
	
	

}
