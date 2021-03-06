package com.kaishengit.service;

import com.kaishengit.mapper.BookMapper;
import com.kaishengit.mapper.BookTypeMapper;
import com.kaishengit.mapper.PublisherMapper;
import com.kaishengit.pojo.Book;
import com.kaishengit.pojo.BookType;
import com.kaishengit.pojo.Publisher;
import com.kaishengit.util.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
@Transactional
public class BookService {

    @Inject
    private BookMapper bookMapper;
    @Inject
    private BookTypeMapper bookTypeMapper;
    @Inject
    private PublisherMapper publisherMapper;

    public List<BookType> findAllBookType(){
        return bookTypeMapper.findAll();
    }

    public List<Publisher> findAllPublisher(){
        return publisherMapper.findAll();
    }

    public List<Book> findAllBook(){
        return bookMapper.findAll();
    }

    public void saveBook(Book book){
        bookMapper.save(book);
    }

    public void delBook(Integer id){
        bookMapper.del(id);
    }

    public void updateBook(Book book){
        bookMapper.update(book);
    }

    public Book findById(Integer id){
        return bookMapper.findById(id);
    }

    public Page<Book> findBookPage(Integer p, Map<String,Object> param) {
        int totalSize = bookMapper.countByParam(param).intValue();

        Page<Book> page = new Page<>(p,5,totalSize);

        //List<Book> bookList = bookMapper.findByPage(page.getStart(),5);
        param.put("start",page.getStart());
        param.put("size",5);
        List<Book> bookList = bookMapper.findByParam(param);
        page.setItems(bookList);
        return page;
    }


    public List<Book> findByDataTables(Map<String,Object> param) {
        return bookMapper.findByDataTable(param);
    }

    public Long count() {
        return bookMapper.count();
    }

    public Object countByParam(Map<String,Object> param) {
        return bookMapper.countByParam(param);
    }
}

