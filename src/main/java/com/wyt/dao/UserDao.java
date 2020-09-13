package com.wyt.dao;

import java.util.List;

import com.wyt.entity.Address;
import com.wyt.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

public interface UserDao extends Mapper<User> {

	public void addUser(User u);

	public User queryByEmail(String email);

	public List<Address> queryAddressByUserId(String id);

	public void updateUser(@Param("user") User user, @Param("pass") String pass);

	public User queryById(String id);


}
