package com.wyt.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wyt.entity.Address;
import tk.mybatis.mapper.common.Mapper;

public interface AddressDao extends Mapper<Address> {
	
	public List<Address> queryByCondition(@Param("addressee") String addressee, @Param("address") String address, @Param("userId") String userId);

	public void insertOneAddress(Address addr);

	public void deleteAddr(String id);

	public void update(int i);

	public void updateDefault(@Param("id") String id, @Param("i") int i);

	public Address queryBydefault(int i);


}
