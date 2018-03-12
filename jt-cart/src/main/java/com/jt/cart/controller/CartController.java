package com.jt.cart.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	private static final ObjectMapper objectMapper  = new ObjectMapper();
	private static final Logger logger = Logger.getLogger(CartController.class);
	
	@RequestMapping("/query/{userId}")
	@ResponseBody
	public SysResult findCartListByUserId(@PathVariable Long userId){
		
		try {
			List<Cart> cartList = cartService.findCartListByUserId(userId);	
			String cartJSON = objectMapper.writeValueAsString(cartList);
			return SysResult.oK(cartJSON);
		} catch (Exception e) {
			
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "查询购物车失败");
		}
	}
	
	
	//修改商品的数量
	@RequestMapping("/update/num/{userId}/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCartNum(@PathVariable Long userId, 
			@PathVariable Long itemId,@PathVariable Integer num){
		
		
		return cartService.updateCartNum(userId,itemId,num);
		
	}
	
	
	//根据userId和itemId删除购物车
	@RequestMapping("/delete/{userId}/{itemId}")
	@ResponseBody
	public SysResult deleteCart(@PathVariable Long userId,@PathVariable Long itemId){
		
		try {
			cartService.deleteCart(userId,itemId);
			return SysResult.oK();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "购物车删除失败");
		}
	}
	
	
	
	//购物车的新增   http://cart.jt.com/cart/save
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveCart(Cart cart){
		
		try {
			cartService.saveCart(cart);
			
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "商品新增失败");
		}
		
	}
	
	
}
