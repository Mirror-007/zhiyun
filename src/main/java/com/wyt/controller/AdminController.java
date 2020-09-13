package com.wyt.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wyt.entity.Admin;
import com.wyt.entity.Order;
import com.wyt.entity.User;
import com.wyt.service.AdminService;
import com.wyt.util.GetRequestUtil;
import com.wyt.util.SecurityCode;
import com.wyt.util.SecurityImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController{
	@Autowired
	private AdminService adminService;

	/**
	 * 生成验证码
	 * @return
	 */
	@RequestMapping("capture")
	@ResponseBody
	public String capture(){
		//1.生成随机数、
		String code = SecurityCode.getSecurityCode();
		HttpServletRequest req = GetRequestUtil.getRequest();
		req.getSession().setAttribute("code", code);
		//2.将随机数 写在图片上
		BufferedImage Image = SecurityImage.createImage(code);
		//3 将验证码 响应到浏览器端
		HttpServletResponse res = GetRequestUtil.getResponse();
		try {
			OutputStream out = res.getOutputStream();
			ImageIO.write(Image, "png", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @category 登录
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String vcode,Admin admin){
		String msg;
		HttpSession session = GetRequestUtil.getSession();
		String code = (String)session.getAttribute("code");
		System.out.println("验证码：       "+vcode);
		Admin ad1 = adminService.queryByName(admin.getAdminName().trim());
		if(ad1!=null){
			if(ad1.getAdminPass().equals(admin.getAdminPass())){
				if(vcode.equals(code)){
					session.setAttribute("ADMIN", ad1);
					return "redirect:/index.jsp";
				}else{
					msg="验证码输入错误！";
				}
			}else{
				msg="密码输入错误！";
			}
		}else{
			msg="管理员不存在！";
		}
		session.setAttribute("msg",msg);
		return "redirect:/index.jsp";
	}

	/**
	 * @category 安全退出
	 * @return
	 */
	@RequestMapping("exit")
	public String exit(){
		HttpSession session = GetRequestUtil.getSession();
		session.invalidate();
		return "redirect:/login.jsp";
	}

	/**
	 * @category 展示所有用户的信息
	 * @return
	 */
	@RequestMapping("/showUsers")
	public String showUsers(){
		List<User> list = adminService.queryAllUsers();
		HttpServletRequest req = GetRequestUtil.getRequest();
		req.setAttribute("AllUser", list);
		return "forward:/back/user/show.jsp";
	}

	/**
	 * @category 修改用户状态
	 * @return
	 */
	@RequestMapping("/updateUserStatus")
	public String updateUserStatus(String id,String email){
		adminService.updateUser(id,email);
		return "redirect:/admin/showUsers";
	}

	/**
	 * @category 展示所有订单
	 * @return
	 */
	@RequestMapping("/showOrders")
	public String showOrders(){
		List<Order> list = adminService.queryAllOrders();
		HttpServletRequest req = GetRequestUtil.getRequest();
		req.setAttribute("AllOrder", list);
		return "forward:/back/order/show.jsp";
	}

	/**
	 * @category 展示具体信息，订单项
	 * @return
	 */
	@RequestMapping("showOrderItem")
	public String showOrderItem(String orderId,String id){
		Order order = adminService.queryOrderItemByOrderId(orderId);
		HttpServletRequest req = GetRequestUtil.getRequest();
		req.setAttribute("OrderItemss", order);
		if(id!=null&&!"".equals(id)){
			//跳转到前台展示订单详情页面
			return "forward:/front/user/orderdetail.jsp";
		}else{
			//跳转到后台展示订单详情界面
			return "forward:/back/order/detail.jsp";
		}
	}

	/**
	 * @category 根据用户id查询订单
	 * @return
	 */
	@RequestMapping("/queryOrderById")
	public String queryOrderById(String id){
		List<Order> list = adminService.queryOrderByUserId(id);
		HttpServletRequest req = GetRequestUtil.getRequest();
		req.setAttribute("AllOrder", list);
		return"forward:/front/user/orderhistory.jsp";
	}














}
