package com.dastko.devbookmarks.utilites;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dastko on 11/5/15.
 */
@Repository
public class HibernateUtil
{
//    @Autowired
//    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public <T> T create(final T entity) {
        //sessionFactory.getCurrentSession().save(entity)

        entityManager.persist(entity);
        entityManager.flush();
        return entity;

    }

    public <T> T update(final T entity) {
//        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    public <T> void delete(final T entity) {

        //sessionFactory.getCurrentSession().delete(entity);
    }

    public <T> void delete(Serializable id, Class<T> entityClass) {
        T entity = fetchById(id, entityClass);
        delete(entity);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> fetchAll(Class<T> entityClass) {
        //return sessionFactory.getCurrentSession().createQuery(" FROM "+entityClass.getName()).list();
        return entityManager.createQuery("select t from " + entityClass.getSimpleName() + " t").getResultList();
    }

    @SuppressWarnings("rawtypes")
    public <T> List fetchAll(String query) {

        //return sessionFactory.getCurrentSession().createSQLQuery(query).list();
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T fetchById(Serializable id, Class<T> entityClass) {
        //Return the persistent instance of the given named entity with the given identifier, or null if there is no such persistent instance.
        //return (T)sessionFactory.getCurrentSession().get(entityClass, id);
        return null;
    }
}
