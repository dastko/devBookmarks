package com.dastko.devbookmarks.dao.impl;

import com.dastko.devbookmarks.dao.LinkDAO;
import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.utilites.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dastko on 11/5/15.
 */
@Repository
public class LinkDAOImpl implements LinkDAO
{
    public LinkDAOImpl()
    {

    }

    @Autowired
    private HibernateUtil hibernateUtil;

    @Override
    public Link createLink(Link link)
    {
        return hibernateUtil.create(link);
    }

    @Override
    public Link updateLink(Link link)
    {
        return null;
    }

    @Override
    public void deleteLink(long id)
    {

    }

    @Override
    public List<Link> getAllLinks()
    {

        return hibernateUtil.fetchAll(Link.class);
    }

    @Override
    public Link getLink(long id)
    {
        return null;
    }

    @Override
    public List<Link> getAllLinks(String linkName)
    {
        return null;
    }
}
