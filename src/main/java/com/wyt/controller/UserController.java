package com.wyt.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wyt.entity.Address;
import com.wyt.entity.CartItem;
import com.wyt.entity.User;
import com.wyt.service.UserService;
import com.wyt.util.GetRequestUtil;
import com.wyt.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController{

	@Autowired
	private UserService userService;

	/**
	 * 生成验证码
	 * @return
	 */
	@RequestMapping("/getImageCode")
	@ResponseBody
	public String getImageCode(){
		userService.createImageCode();
		return null;
	}

	/**
	 * @category 接收注册页面传来的数据
	 * @return
	 */
	@RequestMapping("/toRegisterUser")
	public String toRegisterUser(User user,String code){
		HttpServletRequest req = GetRequestUtil.getRequest();
		HttpSession session = req.getSession();
		User useruser = userService.queryByEmail(user.getEmail());
		String vcode = (String)session.getAttribute("code");
		if(useruser==null){
			if(vcode.equals(code)){
				userService.toRegisterUser(user.getEmail(),user.getPassword(),user.getPetName());
				return "forward:/front/user/verify_form.jsp";
			}else{
				String yanzhengma = "验证码错误！";
				req.setAttribute("yanzhengma", yanzhengma);
				return "forward:/front/user/register_form.jsp";
			}
		}else{
			String checkEmail = "当前邮箱已被绑定";
			req.setAttribute("checkEmail", checkEmail);
			return "forward:/front/user/register_form.jsp";
		}
	}

	/**
	 * @category 邮箱验证激活码
	 * @return
	 */
	@RequestMapping("/verifyRegister")
	public String verifyRegister(User user){
		String actiMsg = null;
		//比对激活码，正确进行注册，失败，给出提示
		HttpServletRequest req = GetRequestUtil.getRequest();
		HttpSession session = req.getSession();
		String code = (String)session.getAttribute("emailCheck");
		if(code.equals(user.getActiCode())){
			userService.addUser(user.getActiCode());
			return "forward:/front/user/register_ok.jsp";
		}else{
			actiMsg = "激活码错误！";
			req.setAttribute("actiMsg", actiMsg);
			return "forward:/front/user/verify_form.jsp";
		}
	}

	/**
	 * @category 验证登录
	 * @return
	 */
	@RequestMapping("/userLogin")
	public String userLogin(User user){
		boolean flag = userService.checkLogin(user.getEmail(),user.getPassword());
		HttpSession session = GetRequestUtil.getRequest().getSession();
		if(flag){
			Map<String, CartItem> map = (Map<String,CartItem>)session.getAttribute("cart");
			if(map == null){
				return "forward:/cate/showFrontCate";
			}else{
				return "redirect:/user/toGetAddress";
			}

		}else{
			return "forward:/front/user/login_form.jsp";
		}
	}

	/**
	 * @category 找出用户对应的地址
	 * @return
	 */
	@RequestMapping("/toGetAddress")
	public String toGetAddress(){
		List<Address> list = userService.queryAddress();
		HttpServletRequest req = GetRequestUtil.getRequest();
		if(list.size()==0){
			String mm = "请先添加一个地址";
			req.setAttribute("mm", mm);
			return "forward:/front/user/setaddress.jsp";
		}else{
			req.setAttribute("showAddr", list);
			return "forward:/front/order/address_form.jsp";
		}
	}

	/**
	 * @category 安全退出
	 */
	@RequestMapping("/exit")
	public String exit(){
		HttpSession session = GetRequestUtil.getSession();
		session.removeAttribute("a");
		session.removeAttribute("cart");
		return "forward:/cate/showFrontCate";
	}

	@RequestMapping("/myDangDang")
	public String myDangDang(){

		return "forward:/front/user/dangdang.jsp";
	}

	//修改用户数据
	@RequestMapping("/updateUser")
	public String updateUser(User user,String pass){
		HttpServletRequest req = GetRequestUtil.getRequest();
		HttpSession session = req.getSession();
		User u = (User)session.getAttribute("a");
		String salt = u.getSalt();
		String md5 = Md5Utils.getMd5Code(salt+user.getPassword()+salt);
		String md5Code = Md5Utils.getMd5Code(salt+pass+salt);
		String fl = null;
		//判断输入密码是否正确
		if(u.getPassword().equals(md5)){
			User u1 = userService.queryByEmail(user.getEmail());
			//判断填入的邮箱是否已被其他用户绑定
			if(u1==null||u1.getId().equals(user.getId())){
				//邮箱可以使用
				//不改密码情况
				if("".equals(pass)){
					userService.updateUser(user,null);
				}else{
					userService.updateUser(user,md5Code);
				}

			}else{
				//该邮箱已经被绑定
				fl="该邮箱已经被其他用户绑定！";
			}
		}else{
			fl="填写原密码错误！";
		}
		User a = userService.queryById(user.getId());
		req.setAttribute("a", a);
		req.setAttribute("fl", fl);
		return "forward:/front/user/dangdang.jsp";
	}





















}
