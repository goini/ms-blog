package com.ms.dao;

import com.ms.dto.EntityErrorMessage;
import com.ms.entity.BaseEntity;
import com.ms.exception.EntityNotFoundException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by max shemet on 11/2/2016.
 */
@Repository
@Transactional
public abstract class BaseDao<T extends BaseEntity> {

    private SessionFactory factory;
    private Class<T> entityClass;

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public BaseDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    public List<T> getAll() {
        CriteriaQuery<T> criteriaQuery = createQuery();
        criteriaQuery.from(entityClass);
        return find(criteriaQuery);
    }

    public T get(long id) {
        return Optional.ofNullable(entityManager
                .find(entityClass, id))
                .orElseThrow(() -> new EntityNotFoundException(new EntityErrorMessage(id, entityClass)));
    }

    public void update(T obj) {
        obj = get(obj.getId());
        obj.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        entityManager.merge(obj);
    }

    public T create(T obj) {
        LocalDateTime now = LocalDateTime.now();
        obj.setCreated(Timestamp.valueOf(now));
        obj.setUpdated(Timestamp.valueOf(now));
        entityManager.persist(obj);
        return obj;
    }

    public void delete(long id) {
        T obj = entityManager.getReference(entityClass, id);
        entityManager.remove(id);
    }

    public List<T> find(CriteriaQuery<T> criteriaQuery) {
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    protected Criteria createCriteria(Class<T> aClass) {
        return getSession().createCriteria(aClass);
    }

    protected Session getSession() {
        return factory.getCurrentSession();
    }

    protected CriteriaQuery<T> createQuery() {
        return entityManager.getCriteriaBuilder().createQuery(entityClass);
    }
}
