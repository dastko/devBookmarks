package com.dastko.devbookmarks.dao;


import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.helpers.PaginationWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dastko
 */
public interface GenericDAO
{

    public <T> T create(T object);

    public <T> T createObject(T object, T elasticObject);

    public <T> T updateObject(T object);

    public <T> void deleteObject(T object);

    public <T> void deleteById(long id, Class <T> entityClass);

    public <T> List<T> getAllObjects(Class<T> entityClass);

    public <T> T getObject(long id, Class <T> entityClass);

    public <T> List<T> getAllObjects(String inputName);

    public <T> List<T> fetchByInputString(Class<T> entityClass, String input);

    public <T> T fetchById(Serializable id, Class<T> entityClass);

    public <T> List<T> getAllObjectByUserId(Class<T> entityClass, Long input);

    public PaginationWrapper pagination(int pageNumber);


}
