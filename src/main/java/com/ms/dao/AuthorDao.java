package com.ms.dao;

import com.ms.entities.Author;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by go in on 02.11.2016.
 */
@Repository
@Transactional
public class AuthorDao{

    @Autowired
    private SessionFactory sf;

    public List<Author> getAll(){
        Criteria criteria = sf.getCurrentSession().createCriteria(Author.class);
        return criteria.list();
    }


    public Author create(Author author) {
        sf.getCurrentSession().save(author);
        return author;
    }

    public Author getAuthor(int id){
        Criteria criteria = sf.getCurrentSession().createCriteria(Author.class);
        criteria.add(Restrictions.eq("id", id));
        return (Author) criteria.uniqueResult();
    }

    public Author update(Author author) {
        Author oldAuthor = sf.getCurrentSession().get(Author.class, author.getId());

        if(!StringUtils.isEmpty(author.getName())){
            oldAuthor.setName(author.getName());
        }
        if(!StringUtils.isEmpty(author.getBookName())){
            oldAuthor.setBookName(author.getBookName());
        }
        if(author.getPages() > 0){
            oldAuthor.setPages(author.getPages());
        }

        return oldAuthor;
    }

    public void delete(int id) {
        Author author = sf.getCurrentSession().get(Author.class, id);
        sf.getCurrentSession().delete(author);
    }
}
