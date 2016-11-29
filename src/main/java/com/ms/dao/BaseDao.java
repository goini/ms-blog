package com.ms.dao;

import com.ms.dto.EntityErrorMessage;
import com.ms.entity.BaseEntity;
import com.ms.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
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
@Transactional(propagation = Propagation.REQUIRED)
public abstract class BaseDao<T extends BaseEntity> {

    private Class<T> entityClass;

    private EntityManager entityManager;
    @PersistenceUnit
    private EntityManagerFactory emf;
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
                .orElseThrow(() -> new ResourceNotFoundException(new EntityErrorMessage(id, entityClass)));
    }

    public void update(T obj) {
   //     entityManager.getTransaction().commit();
        //entityManager.lock(obj, LockModeType.NONE);
//        entityManager.refresh(get(obj.getId()));
       // entityManager.detach(obj);
        obj.setUpdated( Timestamp.valueOf(LocalDateTime.now()));
       entityManager.merge(obj);
    }

    public T create(T obj) {
        entityManager.persist(obj);
        entityManager.refresh(obj);
        return obj;
    }

    public void delete(long id) {
        try {
            T obj = getReference(id);
            entityManager.remove(obj);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(new EntityErrorMessage(id, entityClass));
        }
    }

    public List<T> find(CriteriaQuery<T> criteriaQuery) {
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    protected T getReference(long id) {
        try {
            return entityManager.getReference(entityClass, id);
        } catch (Exception e) {
            throw e;
        }
    }

    protected CriteriaQuery<T> createQuery() {
        return entityManager.getCriteriaBuilder().createQuery(entityClass);
    }
}
