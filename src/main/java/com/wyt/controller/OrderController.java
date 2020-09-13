package com.wyt.controller;

import com.wyt.service.OrderService;
import com.wyt.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController{

	@Autowired
	private OrderService orderService;

	/**
	 * @category 添加订单
	 * @return
	 */
	@RequestMapping("/addOrder")
	public String addOrder(){
		orderService.addOrder();
		return "forward:/front/order/order_ok.jsp";
	}
}
