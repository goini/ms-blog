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

<<<<<<< HEAD
    private EntityManager entityManager;
    @PersistenceUnit
    private EntityManagerFactory emf;
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
=======
    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEntityManager(EntityManagerFactory emf) {
        this.emf = emf;
>>>>>>> refs/remotes/maksymshemet/master
    }

    public BaseDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

<<<<<<< HEAD
=======
    @Transactional(propagation = Propagation.SUPPORTS)
>>>>>>> refs/remotes/maksymshemet/master
    public List<T> getAll() {
        CriteriaQuery<T> criteriaQuery = createQuery();
        criteriaQuery.from(entityClass);
        return find(criteriaQuery);
    }

<<<<<<< HEAD
    public T get(long id) {
        return Optional.ofNullable(entityManager
=======
    @Transactional(propagation = Propagation.SUPPORTS)
    public T get(long id) {
        return Optional.ofNullable(createEntityManager()
>>>>>>> refs/remotes/maksymshemet/master
                .find(entityClass, id))
                .orElseThrow(() -> new ResourceNotFoundException(new EntityErrorMessage(id, entityClass)));
    }

    public void update(T obj) {
<<<<<<< HEAD
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
=======
        try {
            EntityManager em = createEntityManager();
            em.getReference(entityClass, obj.getId()).getId();
            obj.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
            em.merge(obj);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(new EntityErrorMessage(obj.getId(), entityClass));
        }
    }

    public T create(T obj) {
        EntityManager em = createEntityManager();
        em.persist(obj);
        em.refresh(obj);
>>>>>>> refs/remotes/maksymshemet/master
        return obj;
    }

    public void delete(long id) {
        try {
<<<<<<< HEAD
            T obj = getReference(id);
            entityManager.remove(obj);
=======
            EntityManager em = createEntityManager();
            T obj = em.getReference(entityClass, id);
            em.remove(obj);
>>>>>>> refs/remotes/maksymshemet/master
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(new EntityErrorMessage(id, entityClass));
        }
    }

    public List<T> find(CriteriaQuery<T> criteriaQuery) {
<<<<<<< HEAD
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
=======
        TypedQuery<T> query = createEntityManager().createQuery(criteriaQuery);
>>>>>>> refs/remotes/maksymshemet/master
        return query.getResultList();
    }

    protected T getReference(long id) {
<<<<<<< HEAD
        try {
            return entityManager.getReference(entityClass, id);
        } catch (Exception e) {
            throw e;
        }
    }

    protected CriteriaQuery<T> createQuery() {
        return entityManager.getCriteriaBuilder().createQuery(entityClass);
=======
        return createEntityManager().getReference(entityClass, id);
    }

    protected CriteriaQuery<T> createQuery() {
        return createEntityManager().getCriteriaBuilder().createQuery(entityClass);
    }

    protected EntityManager createEntityManager() {
        return emf.createEntityManager(SynchronizationType.SYNCHRONIZED);
>>>>>>> refs/remotes/maksymshemet/master
    }
}
