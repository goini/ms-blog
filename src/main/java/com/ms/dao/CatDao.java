package com.ms.dao;

import com.ms.entities.Cat;
import com.ms.filter.CatFilter;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by go in on 01.11.2016.
 */
@Repository
@Transactional
public class CatDao {

    @Autowired
    private SessionFactory sf;

    public Cat getCat(int id){
        Criteria criteria = sf.getCurrentSession().createCriteria(Cat.class);
        criteria.add(Restrictions.eq("id", id));
        return (Cat) criteria.uniqueResult();
    }

    public List<Cat> getAll (){
        Criteria criteria = sf.getCurrentSession().createCriteria(Cat.class);
        return criteria.list();
    }

    public Cat create(Cat cat) {
        sf.getCurrentSession().save(cat);
        return cat;
    }

    public Cat update(Cat newCat){
      // sf.getCurrentSession().merge(cat);
        Cat oldCat =  sf.getCurrentSession().get(Cat.class, newCat.getId());
        if(!StringUtils.isEmpty(newCat.getName())) {
            oldCat.setName(newCat.getName());
        }
        if(!StringUtils.isEmpty(newCat.getColor())) {
            oldCat.setColor(newCat.getColor());
        }
        return oldCat;
    }

    public void delete (int id){
        Cat cat = sf.getCurrentSession().get(Cat.class, id);
        sf.getCurrentSession().delete(cat);
    }

    public List<Cat> getByFilter(CatFilter filter){
        Criteria criteria = filter.prepare(sf.getCurrentSession().createCriteria(Cat.class));
        return criteria.list();
    }
}
