package com.wyt.service;

import com.wyt.entity.Order;
import com.wyt.entity.OrderItem;

public interface OrderService {

	public void updateBookStorages(String id, int storages);

	public void updateBookSaleCount(String id, int saleCount);
	
	public void addBookOrder(Order order);
	
	public void addOrderItem(OrderItem orderItem);
	
	public void addOrder();

}
