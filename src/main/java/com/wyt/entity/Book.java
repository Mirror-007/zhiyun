package com.wyt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Book implements Serializable{
	
	private String id;
	private String bookName;
	private String parentCateName;
	private double price;
	private double ddPrice;
	private int storages;
	private String writer;
	private String press;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date publishDate;
	private int publishNum;
	private Date printDate;
	private int printNum;
	private String ISBN;
	private int kaiBen;
	private String paper;
	private String pack;
	private int pageNum;
	private int characterNum;
	private String image;
	private String recommend;
	private String bookIntroduce;
	private String writerIntroduce;
	private String catalogue;
	private String evaluate;
	private int saleCount;
	

	
	
	
	
	
	
}
