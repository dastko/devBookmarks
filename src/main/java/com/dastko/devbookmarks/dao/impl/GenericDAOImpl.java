package com.dastko.devbookmarks.dao.impl;

import com.dastko.devbookmarks.dao.GenericDAO;
import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.helpers.PaginationWrapper;
import com.dastko.devbookmarks.utilites.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
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
    public <T> T createObject(T object, T elasticObject)
    {
        return hibernateUtil.create(object, elasticObject);

    }

    @Override
    public <T> T create(T object)
    {
        return hibernateUtil.createObject(object);
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
    public <T> List<T> getAllObjectByUserId(Class<T> entityClass, Long input)
    {
        return hibernateUtil.findObjectsById(entityClass, input);
    }

    @Override
    public PaginationWrapper pagination(int pageNumber)
    {
        return hibernateUtil.pagination(pageNumber);
    }

    @Override
    public <T> T fetchById(Serializable id, Class<T> entityClass)
    {
        return hibernateUtil.fetchById(id, entityClass);
    }

}
