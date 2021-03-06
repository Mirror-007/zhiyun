package com.wyt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class OrderItem implements Serializable{
	
	private String id;
	private double price;
	private int count;
	private double miniPrice;
	private String bookId;
	private String orderId;
	private Book book;

	



	
	
	
}
