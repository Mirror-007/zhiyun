package com.wyt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Order implements Serializable{

	private String id;
	private String orderNumber;
	private Date orderTime;
	private double total;
	private String status;
	private String addressee;
	private String address;
	private String userId;
	private String addressId;
	private List<OrderItem> orderItems;
	private User user;
	

	
}
