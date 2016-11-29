package com.ms.controller;

import com.ms.dao.BookDao;
import com.ms.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by go in on 22.11.2016.
 */

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    public BookDao bookDao;

    @RequestMapping("/")
    public List<Book> getAll(){
        return bookDao.getAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Book create(@RequestBody Book book){
        return bookDao.create(book);
    }

    @RequestMapping("/{id}")
    public Book getAuthor(@PathVariable(name = "id") int id){
        return bookDao.getBook(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Book update(@RequestBody Book book){
        return bookDao.update(book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") int id){
        bookDao.delete(id);
    }

}
