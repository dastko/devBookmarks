package com.dastko.devbookmarks.dao;


import java.util.List;

/**
 * Created by dastko
 */
public interface GenericDAO
{

    public <T> T createObject(T object, T secondObject);

    public <T> T updateObject(T object);

    public <T> void deleteObject(T object);

    public <T> void deleteById(long id, Class <T> entityClass);

    public <T> List<T> getAllObjects(Class<T> entityClass);

    public <T> T getObject(long id, Class <T> entityClass);

    public <T> List<T> getAllObjects(String inputName);

    public <T> List<T> fetchByInputString(Class<T> entityClass, String input);

    public <T> T createTransaction(final T entity, final T elasticEntity);

}
