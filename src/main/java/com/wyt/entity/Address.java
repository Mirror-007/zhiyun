package com.wyt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Address implements Serializable{
	
	private String id;
	private String addressee;
	private String address;
	private String postcode;
	private String phone;
	private String userId;
	private int defaultAddr;
	private User user;
	
}
