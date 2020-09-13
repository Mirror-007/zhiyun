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
public class User implements Serializable{
	
	private String id;
	private String petName;
	private String email;
	private String password;
	private String actiCode;
	private String salt;
	private String status;
	private Address addrs;
	private List<Order> orders;
	
	

	
	
	
	
	
}
