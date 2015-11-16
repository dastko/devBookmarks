package com.dastko.devbookmarks.dao.impl;

import com.dastko.devbookmarks.dao.GenericDAO;
import com.dastko.devbookmarks.utilites.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dastko
 */
@Repository
public class GenericDAOImpl implements GenericDAO
{
    @Autowired
    private HibernateUtil hibernateUtil;


    @Override
    public <T> T createObject(T object, T secondObject)
    {
        return hibernateUtil.create(object, secondObject);

    }

    @Override
    public <T> T updateObject(T object)
    {
        return hibernateUtil.update(object);
    }

    @Override
    public <T> void deleteObject(T object)
    {
        hibernateUtil.delete(object);
    }

    @Override
    public <T> void deleteById(long id, Class<T> entityClass)
    {
        hibernateUtil.delete(id, entityClass);
    }

    @Override
    public <T> List<T> getAllObjects(Class<T> entityClass)
    {
        return hibernateUtil.fetchAll(entityClass);
    }

    @Override
    public <T> T getObject(long id, Class<T> entityClass)
    {
        return hibernateUtil.fetchById(id, entityClass);
    }

    @Override
    public <T> List<T> getAllObjects(String inputName)
    {
        return null;
    }

    @Override
    public <T> List<T> fetchByInputString(Class<T> entityClass, String input)
    {
        return hibernateUtil.fetchLike(entityClass, input);
    }

    @Override
    public <T> T createTransaction(final T entity, final T elasticEntity){
        return hibernateUtil.createTransaction(entity, elasticEntity);
    }

}
