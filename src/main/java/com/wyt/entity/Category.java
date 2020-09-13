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
public class Category implements Serializable {

	private String id;
	private String cateName;
	private String parentCateName;
	private int grade;
	private List<Category> categories;

	private List<Book> books;


}
