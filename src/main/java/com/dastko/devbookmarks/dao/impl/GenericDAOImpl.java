package com.dastko.devbookmarks.dao.impl;

import com.dastko.devbookmarks.dao.GenericDAO;
import com.dastko.devbookmarks.utilites.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dastko on 11/11/15.
 */
@Repository
public class GenericDAOImpl implements GenericDAO
{
    @Autowired
    private HibernateUtil hibernateUtil;

    @Override
    public <T> T createLink(T link)
    {
        return hibernateUtil.create(link);
    }

    @Override
    public <T> T updateLink(T link)
    {
        return hibernateUtil.update(link);
    }

    @Override
    public <T> void deleteLink(T link)
    {

    }

    @Override
    public <T> void deleteById(long id, Class<T> entityClass)
    {
        hibernateUtil.delete(id, entityClass);
    }

    @Override
    public <T> List<T> getAllLinks(Class<T> entityClass)
    {
        return hibernateUtil.fetchAll(entityClass);
    }

    @Override
    public <T> T getLink(long id)
    {
        return null;
    }

    @Override
    public <T> List<T> getAllLinks(String linkName)
    {
        return null;
    }

    @Override
    public <T> List<T> fetchByInputString(String input)
    {
        return null;
    }
}
