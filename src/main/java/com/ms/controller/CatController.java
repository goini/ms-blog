package com.ms.controller;

import com.ms.dao.CatDao;
import com.ms.entities.Cat;
import com.ms.filter.CatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by go in on 01.11.2016.
 */
@RestController
@RequestMapping("/cat")
public class CatController {

    @Autowired
    private CatDao catDao;

    @RequestMapping("/")
    public List<Cat> getAll() {
        return catDao.getAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Cat create(@RequestBody Cat catName) {
        return catDao.create(catName);
    }

    @RequestMapping("/{id}")
    public Cat getOne(@PathVariable(name = "id") int id){
        return catDao.getCat(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Cat update(@RequestBody Cat cat) {
        return catDao.update(cat);
    }

    @RequestMapping("/filter")
    public List<Cat> getByFilter(@RequestParam(name = "color", required = false) String color,
                                 @RequestParam(name = "name", required = false) String name,
                                 @RequestParam(name = "id", defaultValue = "0") String id){
        CatFilter filter = new CatFilter();
        if(color != null) {
            filter.setColor(color.toLowerCase());
        }
        filter.setId(Integer.valueOf(id));
        filter.setName(name);
        return catDao.getByFilter(filter);
    }

    @RequestMapping (value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable (name = "id") int id){
        catDao.delete(id);
    }
}
