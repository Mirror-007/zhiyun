package com.wyt.service;



import java.util.List;

import com.wyt.dao.AdminDao;
import com.wyt.entity.Admin;
import com.wyt.entity.Order;
import com.wyt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserService userService;
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Admin queryByNameAndPass(String adminName, String adminPass) {
		Admin admin = adminDao.queryByNameAndPass(adminName,adminPass);
		return admin;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Admin queryByName(String adminName) {
		Admin adm = adminDao.queryByName(adminName);
		return adm;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> queryAllUsers() {
		List<User> list = adminDao.queryAllUsers();
		return list;
	}

	@Override
	public void updateUser(String id,String email) {
		User user = userService.queryByEmail(email);
		//获得sqlSession需要放在us.queryByEmail(email)方法后面-》-》-》防止sqlSession被关闭
		if(user.getStatus().equals("正常")){
			adminDao.updateUser(id,"冻结");
		}else{
			adminDao.updateUser(id,"正常");
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Order> queryAllOrders() {
		List<Order> list = adminDao.queryAllOrders();
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Order queryOrderItemByOrderId(String orderId) {
		Order order = adminDao.queryOrderItemByOrderId(orderId);
		return order;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Order> queryOrderByUserId(String id) {
		List<Order> list = adminDao.queryOrderByUserId(id);
		return list;
	}

}
