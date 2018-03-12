package com.jt.web.service;

import java.util.List;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;

public interface CartService {

	public List<Cart> findCartListByUserId(Long userId);
	
	//根据userId和itemId修改商品的数量
	public SysResult updateCartNum(Long userId, Long itemId, Integer num);
	
	//根据userId和itemId 删除购物车信息
	public void deleteCart(Long userId, Long itemId);
	
	//新增购物车信息
	public SysResult insertCart(Cart cart);

}
