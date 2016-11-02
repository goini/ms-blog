package com.ms.dao;

import com.ms.entity.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by max shemet on 11/2/2016.
 */
public abstract class BaseDao<T extends BaseEntity> {

    private SessionFactory factory;
    private Class<T> entityClass;


    public BaseDao(SessionFactory factory, Class<T> entityClass) {
        this.factory = factory;
        this.entityClass = entityClass;
    }

    public List<T> getAll() {
        return createCriteria(entityClass).list();
    }

    public T get(int id) {
        return getSession().get(entityClass, id);
    }

    @Transactional
    public T update(T obj) {
        getSession().merge(obj);
        return obj;
    }

    @Transactional
    public T create(T obj) {
        getSession().save(obj);
        return obj;
    }

    public void delete(int id) {
        T obj = get(id);
        getSession().delete(obj);
    }

    protected Criteria createCriteria(Class<T> aClass) {
        return getSession().createCriteria(aClass);
    }

    protected Session getSession() {
        return factory.getCurrentSession();
    }
}
