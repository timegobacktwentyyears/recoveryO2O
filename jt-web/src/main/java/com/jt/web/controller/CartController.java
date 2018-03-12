package com.jt.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.service.CartService;
import com.jt.web.util.UserThreadLocal;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	
	private static final Logger logger = Logger.getLogger(CartController.class);
	@RequestMapping("/show")
	public String toCart(Model model){
		
		Long userId = UserThreadLocal.getUser().getId();
		//查询时应该根据用户Id号查询购物车信息
		List<Cart> cartList = cartService.findCartListByUserId(userId);
		
		model.addAttribute("cartList", cartList);
		
		//表示转向购物车页面
		return "cart";
	}
	
	//修改商品的数量   http://www.jt.com/service/cart/update/num/1474391989/105
	@RequestMapping("/update/num/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCartNum(@PathVariable Long itemId,@PathVariable Integer num){
		/**
			1.说明哪些操作需要添加try-catch
			如果需要对返回值进行进一步操作,可能由于方法的原因造成程序异常终止,需要再次添加try-catch
		*/
		try {
			Long userId = 19L;
			SysResult sysResult =  cartService.updateCartNum(userId,itemId,num);
				
			return sysResult;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return SysResult.build(201, "修改商品数量失败");
		}
		
	}
	
	
	@RequestMapping("/delete/{itemId}")
	public String deleteCart(@PathVariable Long itemId){
		
		Long userId = UserThreadLocal.getUser().getId();
		cartService.deleteCart(userId,itemId);
		
		//应该转向到购物车页面
		return "redirect:/cart/show.html"; //采用伪静态的方式返回
	}
	
	
	//商品的新增操作  /cart/add/1474392000.html
	@RequestMapping("/add/{itemId}")
	public String insertCart(@PathVariable Long itemId,Cart cart){
		
		Long userId = UserThreadLocal.getUser().getId();
		cart.setUserId(userId);
		cart.setItemId(itemId);
		
		cartService.insertCart(cart);
		
		return "redirect:/cart/show.html"; //通过伪静态的方式提交
	}
	
	
}
