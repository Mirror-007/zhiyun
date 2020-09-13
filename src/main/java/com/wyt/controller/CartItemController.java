package com.wyt.controller;
import com.wyt.entity.Book;
import com.wyt.service.BookService;
import com.wyt.service.BookServiceImpl;
import com.wyt.service.CartItemService;
import com.wyt.service.CartItemServiceImpl;
import com.wyt.util.GetRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/cart")
public class CartItemController{

	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private BookService bookService;

	/**
	 * @category 添加数据到购物车
	 * @return
	 */
	@RequestMapping("/addCart")
	public void addCart(String id){

		HttpServletResponse resp = GetRequestUtil.getResponse();
		Book book = bookService.queryById(id);
		resp.setCharacterEncoding("utf-8");
		try {
			PrintWriter out = resp.getWriter();
			if(book.getStorages()>=1){
				cartItemService.add(id);
				out.println("恭喜您，添加购物车成功！");
			}else{
				out.println("库存余量不足，添加失败！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @category 删除购物车的一条数据到恢复区
	 * @return
	 */
	@RequestMapping("/deleteCartItem")
	public String deleteCartItem(String id){
		cartItemService.delete(id);
		return "forward:/front/cart/cart_list.jsp";
	}

	/**
	 * @category 恢复到购物车
	 * @return
	 */
	@RequestMapping("/recoverCartItem")
	public String recoverCartItem(String id){
		cartItemService.recover(id);
		return "forward:/front/cart/cart_list.jsp";
	}

	/**
	 * @category 更新购物车商品数量
	 * @return
	 */
	@RequestMapping("/updateCartItem")
	public String updateCartItem(String id,int count){
		cartItemService.update(id,count);
		return "forward:/front/cart/cart_list.jsp";
	}








}
