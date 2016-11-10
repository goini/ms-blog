package com.ms.controller;

import com.ms.dao.ArticleDao;
import com.ms.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by max shemet on 11/2/2016.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    private ArticleDao dao;

    @Autowired
    public ArticleController(ArticleDao dao) {
        this.dao = dao;
    }

    @RequestMapping("/")
    public List<Article> getAll() {
        return dao.getAll();
    }

    @RequestMapping("/{id}")
    public Article get(@PathVariable(name = "id") int id) {
        return dao.get(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity create(@Validated @RequestBody Article article) throws URISyntaxException {
        article = dao.create(article);
        return ResponseEntity.created(new URI(String.format("/article/%d", article.getId()))).body(article);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@RequestBody Article article, @PathVariable(name = "id") int id) {
        article.setId(id);
        dao.update(article);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void update(@PathVariable(name = "id") int id) {
        dao.delete(id);
    }
}
