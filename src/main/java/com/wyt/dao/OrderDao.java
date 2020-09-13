package com.wyt.dao;

import org.apache.ibatis.annotations.Param;

import com.wyt.entity.Order;
import com.wyt.entity.OrderItem;
import tk.mybatis.mapper.common.Mapper;

public interface OrderDao extends Mapper<Order> {

	public void updateBook(@Param("id") String id, @Param("storages") int storages, @Param("saleCount") int saleCount);

	public void addBookOrderOrOrderItem(@Param("order") Order or, @Param("oi") OrderItem oi);

}
