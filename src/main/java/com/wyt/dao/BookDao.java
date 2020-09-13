package com.wyt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wyt.entity.Book;
import tk.mybatis.mapper.common.Mapper;

public interface BookDao extends Mapper<Book> {

	//查询所有图书
	public List<Book> queryBooks();

	//添加图书
	public void addBook(Book bo);

	//根据id查询图书
	public Book queryById(String id);

	//根据id和需要修改的信息更新信息
	public void update(@Param("bo") Book bo, @Param("idd") String id);

	//根据书名查询图书
	public Book queryByBookName(String bookName);

	//删除图书
	public void deleteBook(String id);

	//根据条件模糊查询
	public List<Book> likeQueryByKey(@Param("bookName") String bookName, @Param("writer") String writer, @Param("press") String press);

	//前台：
	public List<Book> queryByGrade2(@Param("grade2") String grade2, @Param("startIndex") int a, @Param("endIndex") int b);

	public List<Book> queryFrontCondition(@Param("recNum") int recommend, @Param("hotsNum") int hots, @Param("newsNum") int news);




}
