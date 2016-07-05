package com.kaishengit.service;

import com.kaishengit.pojo.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class BookServiceTestCase {

    @Inject
    private BookService bookService;

    @Test
    public void testFindAllBook(){
        List<Book> bookList = bookService.findAllBook();
        Assert.assertEquals(bookList.size(),29);
    }


    @Test
    public void testSaveBook(){
        Book book = new Book();
        book.setBookname("图解html");
        book.setBookprice(20F);
        book.setBookauthor("john");
        book.setBooknum(10);
        book.setPubid(2);
        book.setTypeid(1);

        bookService.saveBook(book);
    }

    @Test
    public void testFindById(){
        Book book = bookService.findById(13);
        Assert.assertNotNull(book);
    }

    @Test
    public void testUpdateBook(){
        Book book = bookService.findById(36);
        book.setBookprice(13F);
        book.setBooknum(50);
        bookService.updateBook(book);

    }

    @Test
    public void testDelBook(){
        bookService.delBook(36);
    }
}
