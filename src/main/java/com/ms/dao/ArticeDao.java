package com.ms.dao;

import com.ms.entity.Article;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by max shemet on 11/2/2016.
 */

@Repository
public class ArticeDao extends BaseDao<Article> {
    @Autowired
    public ArticeDao(SessionFactory factory) {
        super(factory, Article.class);
    }
}
