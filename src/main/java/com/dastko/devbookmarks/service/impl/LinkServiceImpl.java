package com.dastko.devbookmarks.service.impl;

import com.dastko.devbookmarks.dao.LinkDAO;
import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.entity.User;
import com.dastko.devbookmarks.service.LinkService;
import com.dastko.devbookmarks.utilites.JsonResponse;
import com.dastko.devbookmarks.utilites.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.jar.JarEntry;

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


    /**
     * save the transient instance before flushing
     * @param link
     */
    @Override
    public void createLinkParametars(String link)
    {

        ValidationUtils.assertSizeLength(link, 6, 60, "Minimum 6, Maximum 60 characters");
        ValidationUtils.assertNotBlank(link, "Not null");
        //ValidationUtils.isValidURL(link, "Invalid URL");
        ValidationUtils.urlValidation(link, "Invalid URL 2");

        Link validationLink = new Link();
        validationLink.setName(link);
        User user = new User();
        user.setId(1);
        validationLink.setUser(user);
        //we need to save User object before
        linkDAO.createLink(validationLink);
    }

    @Override
    public Link updateLink(Link link)
    {
        return null;
    }

    @Override
    public void deleteLink(Link link)
    {
        linkDAO.deleteLink(link);
    }

    @Override
    public List<Link> getAllLinks()
    {
        return linkDAO.getAllLinks();
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

    @Override
    public boolean exists(String name)
    {
        return false;
    }


}
