package com.wyt.service;

import java.util.List;

import com.wyt.entity.Address;

public interface AddressService {

	public List<Address> queryByAddresseeAndAdress(Address addr);
	
	public List<Address> queryByUserId(Address addr);
	
	public void addAddress(Address addr);

	public void deleteAddr(String id);

	public void updateOriginDefaultAddr(int i);

	public void updateDefaultAddr(String id, int i);

	public Address queryByDefault(int i);

	public void addNewAddress(Address addr);

}
