package com.ms.controller;

import com.ms.dao.ArticeDao;
import com.ms.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by max shemet on 11/2/2016.
 */
@RestController
@RequestMapping("/article")
public class ArticleControlller {

    private ArticeDao dao;

    @Autowired
    public ArticleControlller(ArticeDao dao) {
        this.dao = dao;
    }

    @RequestMapping("/")
    public List<Article> getAll() {
        return dao.getAll();
    }
}
