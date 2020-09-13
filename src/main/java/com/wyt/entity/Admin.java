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
public class Admin implements Serializable{
	
	private String id;
	private String adminName;
	private String adminPass;

	
}
