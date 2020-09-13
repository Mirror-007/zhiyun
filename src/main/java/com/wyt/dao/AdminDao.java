package com.wyt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wyt.entity.Admin;
import com.wyt.entity.Order;
import com.wyt.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AdminDao extends Mapper<Admin> {

	public Admin queryByNameAndPass(@Param("name") String adminName, @Param("pass") String adminPass);

	public Admin queryByName(String adminName);

	public List<User> queryAllUsers();

	public void updateUser(@Param("id") String id, @Param("status") String status);

	public List<Order> queryAllOrders();

	public Order queryOrderItemByOrderId(String orderId);

	public List<Order> queryOrderByUserId(String id);
}
