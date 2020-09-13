package com.wyt.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.wyt.util.*;
import com.wyt.dao.UserDao;
import com.wyt.entity.Address;
import com.wyt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	/**
	 * @category 验证码
	 */
	@Override
	public void createImageCode() {
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

	}

	/**
	 * @category 接收注册页面的信息
	 */
	@Override
	public void toRegisterUser(String email,String password,String petName) {

		User u = new User();
		u.setEmail(email);
		u.setPassword(password);
		u.setPetName(petName);
		HttpServletRequest req = GetRequestUtil.getRequest();
		HttpSession session = req.getSession();
		//发送激活码
		NetEaseSendEmailUtil.getEmail("1803837435@qq.com");
		session.setAttribute("toRegisterUser", u);
	}

	/**
	 * @category 添加用户
	 */
	@Override
	public void addUser(String actiCode) {
		//比对激活码，正确进行注册
		HttpServletRequest req = GetRequestUtil.getRequest();
		HttpSession session = req.getSession();
		User u = (User)session.getAttribute("toRegisterUser");
		String salt = Md5Utils.getSalt(5);
		String md5Code = Md5Utils.getMd5Code(salt+u.getPassword()+salt);
		u.setSalt(salt);
		u.setPassword(md5Code);
		u.setId(UUID.randomUUID().toString().replace("-", ""));
		u.setStatus("正常");
		u.setActiCode(actiCode);
		userDao.addUser(u);
		session.setAttribute("a", u);
	}

	/**
	 * @category 根据邮箱查询用户
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public User queryByEmail(String email) {
		User user = userDao.queryByEmail(email);
		return user;
	}

	/**
	 * @category 检查登录
	 */
	@Override
	public boolean checkLogin(String email, String password) {
		String checkEmail = null;
		String checkPass = null;
		HttpServletRequest req = GetRequestUtil.getRequest();
		HttpSession session = req.getSession();
		User user = userDao.queryByEmail(email);
		if(user==null){
			checkEmail = "当前用户不存在！";
			req.setAttribute("checkEmail", checkEmail);
			return false;
		}else{
			String salt = user.getSalt();
			String md5Code = Md5Utils.getMd5Code(salt+password+salt);
			if(user.getStatus().equals("冻结")){
				checkEmail = "当前用户已被冻结！";
				req.setAttribute("checkEmail", checkEmail);
				return false;
			}else{
				if(!md5Code.equals(user.getPassword())){
					checkPass = "密码填写错误";
					req.setAttribute("checkPass", checkPass);
					return false;
				}else{
					session.setAttribute("a", user);
					return true;
				}
			}
		}
	}

	/**
	 * @category 通过用户id查询地址
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Address> queryAddress() {
		HttpServletRequest req = GetRequestUtil.getRequest();
		HttpSession session = req.getSession();
		User u = (User)session.getAttribute("a");
		List<Address> list = userDao.queryAddressByUserId(u.getId());
		//查询默认地址
		for (Address address : list) {
			if(address.getDefaultAddr()==1){
				req.setAttribute("defaultAddr", address);
			}
		}
		return list;
	}

	//修改用户信息
	@Override
	public void updateUser(User user, String pass) {

		userDao.updateUser(user,pass);
	}

	//通过用户id查询用户
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public User queryById(String id) {
		User user = userDao.queryById(id);
		return user;
	}
















}
