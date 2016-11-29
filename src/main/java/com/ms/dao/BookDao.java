package com.ms.dao;

import com.ms.entities.Book;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by go in on 07.11.2016.
 */
@Repository
@Transactional
public class BookDao {

    @Autowired
    private SessionFactory sf;

    public List<Book> getAll(){
        Criteria criteria = sf.getCurrentSession().createCriteria(Book.class);
        return criteria.list();
    }

    public Book getBook(int id){
        Criteria criteria = sf.getCurrentSession().createCriteria(Book.class);
        criteria.add(Restrictions.eq("id", id));
        return (Book) criteria.uniqueResult();
    }

    public Book create(Book book){
        sf.getCurrentSession().save(book);
        return book;
    }

    public Book update(Book book) {
        sf.openSession().update(book);
        return book;
    }

    public void delete(int id){
        Book book = sf.getCurrentSession().get(Book.class, id);
        sf.getCurrentSession().delete(book);
    }
}
