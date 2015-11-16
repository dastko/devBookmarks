package com.dastko.devbookmarks.utilites;

import com.dastko.devbookmarks.entity.BookElasticsearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dastko
 */
@Repository
public class HibernateUtil
{

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ElasticsearchOperations elasticsearchTemplate;


    @Transactional
    public <T> T create(final T entity, final T elasticEntity)
    {
        entityManager.persist(entity);
        IndexQuery indexQuery = new IndexQueryBuilder().withObject(elasticEntity).build();
        elasticsearchTemplate.index(indexQuery);
        entityManager.flush();
        return entity;
    }

    public <T> T createTransaction(final T entity, final T elasticEntity)
    {
        EntityTransaction tx = null;
        try
        {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.persist(entity);
            IndexQuery indexQuery = new IndexQueryBuilder().withObject(elasticEntity).build();
            elasticsearchTemplate.index(indexQuery);
            tx.commit();
        } catch (RuntimeException e)
        {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e; // or display error message
        } finally
        {
            entityManager.close();
        }
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
