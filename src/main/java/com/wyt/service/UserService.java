package com.wyt.service;

import java.util.List;

import com.wyt.entity.Address;
import com.wyt.entity.User;

public interface UserService {

	public void addUser(String actiCode);

	public void toRegisterUser(String email, String password, String petName);

	public void createImageCode();
	
	public User queryByEmail(String email);

	public boolean checkLogin(String email, String password);

	public List<Address> queryAddress();

	public void updateUser(User user, String pass);

	public User queryById(String id);


}
