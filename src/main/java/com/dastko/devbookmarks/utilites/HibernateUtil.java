package com.dastko.devbookmarks.utilites;

import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.helpers.PaginationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dastko
 */
@Repository
public class HibernateUtil
{

    // failed to lazily initialize a collection of role without EXTENDED type
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
    @Autowired
    private ElasticsearchOperations elasticsearchTemplate;


    @Transactional
    public <T> T create(final T entity, final T elasticEntity)
    {
        entityManager.persist(entity);
        Link link = (Link) entity;
        String id = Long.toString(link.getId());
        IndexQuery indexQuery = new IndexQueryBuilder().withId(id).withObject(elasticEntity).build();
        elasticsearchTemplate.index(indexQuery);
        entityManager.flush();
        return entity;
    }

    @Transactional
    public <T> T createObject(final T entity)
    {
        if (entityManager.contains(entity))
        {
            entityManager.merge(entity);
        } else
        {
            entityManager.persist(entity);
            entityManager.flush();
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
    public <T> List<T> fetchLike(Class<T> entityClass, String input)
    {
        return entityManager.createQuery("select t from " + entityClass.getSimpleName() + " t " + "WHERE t.name LIKE :custInput").setParameter("custInput", "%" + input + "%").getResultList();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> findObjectsById(Class<T> entityClass, Long id)
    {
        return entityManager.createQuery("select t from " + entityClass.getSimpleName() + " t " + "WHERE t.user.id= :id" + " ORDER BY t.date desc").setParameter("id", id).getResultList();
    }

    public <T> PaginationWrapper pagination(Class<T> entityClass, int pageNumber)
    {
        PaginationWrapper paginationWrapper = new PaginationWrapper();
        int pageSize = 10;
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(entityClass)));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> from = criteriaQuery.from(entityClass);
        criteriaQuery.orderBy(criteriaBuilder.desc(from.get("date")));
        CriteriaQuery<T> select = criteriaQuery.select(from);
        TypedQuery<T> typedQuery = entityManager.createQuery(select);
        // Sort
        if (pageNumber < count.intValue()) {
            typedQuery.setFirstResult((pageNumber - 1) * pageSize);
            typedQuery.setMaxResults(pageSize);
            typedQuery.getResultList();
            paginationWrapper.setLinkList(typedQuery.getResultList());
            paginationWrapper.setCount(count);
            return paginationWrapper;
        }

        return null;
    }
}
