package com.dastko.devbookmarks.dao;


import java.util.List;

/**
 * Created by dastko on 11/11/15.
 */
public interface GenericDAO
{
    public <T> T createLink(T link);

    public <T> T updateLink(T link);

    public <T> void deleteLink(T link);

    public <T> void deleteById(long id, Class <T> entityClass);

    public <T> List<T> getAllLinks(Class<T> entityClass);

    public <T> T getLink(long id);

    public <T> List<T> getAllLinks(String linkName);

    public <T> List<T> fetchByInputString(String input);
}
