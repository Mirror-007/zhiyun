package com.wyt.service;

import java.util.List;

import com.wyt.entity.Admin;
import com.wyt.entity.Order;
import com.wyt.entity.User;

public interface AdminService {

	public Admin queryByNameAndPass(String adminName, String adminPass);

	public Admin queryByName(String adminName);

	public List<User> queryAllUsers();

	public void updateUser(String id, String email);

	public List<Order> queryAllOrders();

	public Order queryOrderItemByOrderId(String orderId);

	public List<Order> queryOrderByUserId(String id);

}
