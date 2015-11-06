package com.dastko.devbookmarks.service.impl;

import com.dastko.devbookmarks.dao.LinkDAO;
import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dastko on 11/5/15.
 */
@Service
@Transactional
public class LinkServiceImpl implements LinkService
{

    public LinkServiceImpl()
    {

    }

    @Autowired
    private LinkDAO linkDAO;

    @Override
    public void createLink(Link link)
    {
       linkDAO.createLink(link);
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
        return null;
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
