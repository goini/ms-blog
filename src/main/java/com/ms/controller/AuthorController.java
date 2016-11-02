package com.ms.controller;

import com.ms.dao.AuthorDao;
import com.ms.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by go in on 02.11.2016.
 */

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorDao authorDao;

    @RequestMapping("/")
    public List<Author> getAll(){
        return authorDao.getAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Author create(@RequestBody Author author){
        return authorDao.create(author);
    }

    @RequestMapping("/{id}")
    public Author getAuthor(@PathVariable(name = "id") int id){
        return authorDao.getAuthor(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Author update(@RequestBody Author author){
        return authorDao.update(author);
    }

    @RequestMapping (value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable (name = "id") int id){
        authorDao.delete(id);
    }

}
