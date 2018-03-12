package com.jt.cart.service;

import java.util.List;

import com.jt.cart.pojo.Cart;
import com.jt.common.vo.SysResult;

public interface CartService {

	public List<Cart> findCartListByUserId(Long userId);
	
	//根据userId和itemId修改商品数量
	public SysResult updateCartNum(Long userId, Long itemId, Integer num);
	
	//根据userId和ItemId删除购物车数据
	public void deleteCart(Long userId, Long itemId);
	
	//新增购物车
	public void saveCart(Cart cart);

}
