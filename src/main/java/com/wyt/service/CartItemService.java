package com.wyt.service;

public interface CartItemService {

	public void add(String id);

	public void delete(String id);

	public void recover(String id);

	public void update(String id, int count);

}
