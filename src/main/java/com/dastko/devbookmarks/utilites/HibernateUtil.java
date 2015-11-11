package com.dastko.devbookmarks.utilites;

import org.springframework.stereotype.Repository;

import javax.management.Query;
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

    @PersistenceContext
    private EntityManager entityManager;

    public <T> T create(final T entity)
    {
        entityManager.persist(entity);
        return entity;
    }

    public <T> T update(final T entity)
    {
        return entityManager.merge(entity);
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
        return entityManager.createQuery("select t from " + entityClass.getSimpleName() + " t").getResultList();
    }

    @SuppressWarnings("rawtypes")
    public <T> List fetchAll(String query)
    {
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T fetchById(Serializable id, Class<T> entityClass)
    {
        return entityManager.find(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public <T> List <T> fetchLike(Class<T> entityClass, String input)
    {
        return entityManager.createQuery("select t from " + entityClass.getSimpleName() + " t " + "WHERE t.name LIKE :custInput").setParameter("custInput", "%" + input + "%").getResultList();
    }
}
