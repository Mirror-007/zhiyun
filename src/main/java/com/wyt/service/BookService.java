package com.wyt.service;

import com.wyt.entity.Book;

import java.util.List;

public interface BookService {
	
	public List<Book> showBooks();

	public void addBook(Book bo);

	public Book queryById(String id);

	public void update(Book bo, String id);

	public Book queryByBookName(String bookName);

	public void deleteBook(String id);

	public List<Book> likeQueryByKey(String key, String content);
	
	public List<Book> queryByGrade2(String grade2, int a, int b);

	public List<Book> queryFrontCondition(int a, int b, int c);
	

	

}
