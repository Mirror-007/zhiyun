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
public class CartItem implements Serializable{

	private Book book;
	private int count;
	private boolean status;
	private double miniPrice;

	
	
	
}
