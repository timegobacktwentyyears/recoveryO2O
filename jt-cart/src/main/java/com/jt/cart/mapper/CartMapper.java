package com.jt.cart.mapper;

import org.apache.ibatis.annotations.Insert;

import com.jt.cart.pojo.Cart;
import com.jt.common.mapper.SysMapper;

public interface CartMapper extends SysMapper<Cart>{

	public void updateCartNum(Cart cart);

	@Insert("insert into tb_cart (item_id,item_title,id,item_price,user_id,updated,item_image,num,created) values ( #{itemId},#{itemTitle},null,#{itemPrice},#{userId},#{updated},#{itemImage},#{num},#{created} )")
	public void insertCart(Cart cart);

}
