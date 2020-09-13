package com.wyt.test;

import com.wyt.dao.BookDao;
import com.wyt.entity.Book;
import com.wyt.service.BookService;
import com.wyt.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class Test {

    @org.junit.Test
    public void test1(){
        BookService bs = new BookServiceImpl();
        Book book = bs.queryById("6e2f2b3d16e8413faa1f63e7a88b9ead");
        System.out.println(book);
    }
}
