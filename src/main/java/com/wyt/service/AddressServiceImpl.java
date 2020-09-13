package com.wyt.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.wyt.util.GetRequestUtil;

import com.wyt.dao.AddressDao;
import com.wyt.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressDao addressDao;

	//通过用户地址和收件人查找有没有此地址
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Address> queryByAddresseeAndAdress(Address addr){
		List<Address> list = addressDao.queryByCondition(addr.getAddressee(),addr.getAddress(),addr.getUserId());
		return list;
	}

	//通过用户id查找当前用户有没有绑定地址
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Address> queryByUserId(Address addr) {
		List<Address> list = addressDao.queryByCondition(null,null,addr.getUserId());
		return list;
	}

	//根据判断条件添加新地址
	@Override
	public void addAddress(Address addr) {
		HttpSession session = GetRequestUtil.getSession();
		List<Address> address = queryByAddresseeAndAdress(addr);
		addr.setId(address.get(0).getId());
		session.setAttribute("addressToOrder", addr);
	}

	@Override
	public void deleteAddr(String id) {
		addressDao.deleteAddr(id);
	}

	@Override
	public void updateOriginDefaultAddr(int i) {
		addressDao.update(i);
	}
	@Override
	public void updateDefaultAddr(String id, int i) {
		addressDao.updateDefault(id,i);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Address queryByDefault(int i) {
		Address addr = addressDao.queryBydefault(i);
		return addr;
	}
	@Override
	public void addNewAddress(Address addr) {

		addressDao.insertOneAddress(addr);

	}








}
