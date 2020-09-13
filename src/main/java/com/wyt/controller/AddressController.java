package com.wyt.controller;

import com.wyt.entity.Address;
import com.wyt.entity.User;
import com.wyt.service.AddressService;
import com.wyt.util.GetRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/addr")
public class AddressController{

	@Autowired
	private AddressService addressService;

	//接收添加订单时提交的地址
	@RequestMapping("/toAddAddress")
	public String toAddAddress(Address addr){
		addressService.addAddress(addr);
		return "redirect:/order/addOrder";
	}

	//根据id查询地址
	@RequestMapping("/queryAddressById")
	public String queryAddressById(){
		HttpSession session = GetRequestUtil.getSession();
		User a = (User)session.getAttribute("a");
		Address addr = new Address();
		addr.setUserId(a.getId());
		List<Address> list = addressService.queryByUserId(addr);
		HttpServletRequest req = GetRequestUtil.getRequest();
		req.setAttribute("ads", list);
		return "forward:/front/user/setaddress.jsp";
	}

	//删除地址
	@RequestMapping("/deleteAddr")
	public String deleteAddr(Address addr){
		addressService.deleteAddr(addr.getId());
		return "redirect:/addr/queryAddressById";
	}

	//设置默认地址
	@RequestMapping("/setDefault")
	public String setDefault(Address addr){
		addressService.updateOriginDefaultAddr(0);
		addressService.updateDefaultAddr(addr.getId(),1);
		Address add = addressService.queryByDefault(1);
		HttpSession session =GetRequestUtil.getSession();
		session.setAttribute("defaultAddr", add);
		return "redirect:/addr/queryAddressById";
	}

	//添加一个新的地址
	@RequestMapping("/addAddress")
	public String addAddress(Address addr){
		HttpSession session = GetRequestUtil.getSession();
		User u = (User)session.getAttribute("a");
		addr.setUserId(u.getId());
		List<Address> addr1 = addressService.queryByUserId(addr);
		addr.setId(UUID.randomUUID().toString().replace("-", ""));
		//添加的第一个地址
		if(addr1.size()==0){
			addr.setDefaultAddr(1);
			addressService.addNewAddress(addr);
			return "redirect:/front/cart/cart_list.jsp";
		}else{		//不是第一个地址
			addr.setDefaultAddr(0);
			addressService.addNewAddress(addr);
			return "redirect:/addr/queryAddressById";
		}
	}
}
