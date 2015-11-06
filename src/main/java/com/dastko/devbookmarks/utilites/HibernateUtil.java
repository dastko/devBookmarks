package com.dastko.devbookmarks.utilites;

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

    public <T> T create(final T entity)
    {
        //sessionFactory.getCurrentSession().save(entity)
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    public <T> T update(final T entity)
    {
//        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    /**
     * EntityManager#remove() works only on entities which are managed in the current transaction/context. In your case,
     * you're retrieving the entity in an earlier transaction, storing it in the HTTP session and then attempting to remove it in a different transaction/context.
     * This just won't work. You need to check if the entity is managed by EntityManager#contains() and if not, then make it managed it EntityManager#merge().
     *
     * @param entity
     * @param <T>
     */
    public <T> void delete(final T entity)
    {
        //sessionFactory.getCurrentSession().delete(entity);
        //entityManager.remove(entity);
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    public <T> void delete(Serializable id, Class<T> entityClass)
    {
        T entity = fetchById(id, entityClass);
        delete(entity);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> fetchAll(Class<T> entityClass)
    {
        //return sessionFactory.getCurrentSession().createQuery(" FROM "+entityClass.getName()).list();
        return entityManager.createQuery("select t from " + entityClass.getSimpleName() + " t").getResultList();
    }

    @SuppressWarnings("rawtypes")
    public <T> List fetchAll(String query)
    {
        //return sessionFactory.getCurrentSession().createSQLQuery(query).list();
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T fetchById(Serializable id, Class<T> entityClass)
    {
        //Return the persistent instance of the given named entity with the given identifier, or null if there is no such persistent instance.
        //return (T)sessionFactory.getCurrentSession().get(entityClass, id);
        return null;
    }
}
