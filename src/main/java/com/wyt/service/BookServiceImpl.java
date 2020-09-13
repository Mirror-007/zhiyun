package com.wyt.service;

import java.util.List;

import com.wyt.dao.BookDao;
import com.wyt.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao bookDao;
	//查询所有图书
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Book> showBooks() {
		List<Book> list = bookDao.queryBooks();
		return list;
	}

	//添加图书
	@Override
	public void addBook(Book bo) {
		bookDao.addBook(bo);
	}

	//根据id查询图书
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Book queryById(String id) {
		Book bo = bookDao.queryById(id);
		return bo;
	}

	//根据id和需要修改的信息更新信息
	@Override
	public void update(Book bo, String id) {
		bookDao.update(bo,id);
	}

	//根据书名查询图书
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Book queryByBookName(String bookName) {
		Book bo = bookDao.queryByBookName(bookName);
		return bo;
	}

	//删除图书
	@Override
	public void deleteBook(String id) {
		bookDao.deleteBook(id);
	}

	//根据条件模糊查询
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Book> likeQueryByKey(String key,String content) {
		List<Book> bk = null;
		if(key.equals("bookName")){
			bk = bookDao.likeQueryByKey(content,null,null);
		}
		if(key.equals("writer")){
			bk = bookDao.likeQueryByKey(null,content,null);
		}
		if(key.equals("press")){
			bk = bookDao.likeQueryByKey(null,null,content);
		}
		return bk;
	}

	//查找二级对应的所有图书
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Book> queryByGrade2(String grade2 , int a , int b) {
		List<Book> bk = bookDao.queryByGrade2(grade2 ,a ,b);
		return bk;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Book> queryFrontCondition(int a, int b , int c) {
		List<Book> book = bookDao.queryFrontCondition(a, b, c);
		return book;
	}



}
