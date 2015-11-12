package com.dastko.devbookmarks.service.impl;

import com.dastko.devbookmarks.dao.GenericDAO;
import com.dastko.devbookmarks.wrapper.LinkWrapper;
import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.entity.Tag;
import com.dastko.devbookmarks.entity.User;
import com.dastko.devbookmarks.service.LinkService;
import com.dastko.devbookmarks.utilites.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.*;

/**
 * Created by dastko
 */
@Service
@Transactional
public class LinkServiceImpl implements LinkService
{

    public LinkServiceImpl()
    {
    }

    @Autowired
    private GenericDAO genericDAO;

    @Override
    public Set<String> createLink(LinkWrapper linkWrapper, Principal principal)
    {
        final String name = linkWrapper.getName();
        ValidationUtils.assertSizeLength(name, 8, 250, "Minimum 8, Maximum 250 characters");
        ValidationUtils.assertNotBlank(name, "Not null");
        ValidationUtils.urlValidation(name, "Invalid URL");

        //User user = (User)((Authentication) principal).getPrincipal();
        User user = new User();
        user.setId(2);
        String[] splitTagsByComma = linkWrapper.getTags().trim().split("\\s*,\\s*");
        Link link = new Link(user, new Date(), linkWrapper.getName(), linkWrapper.getDetails());
        for (String strings : splitTagsByComma)
        {
            link.tagIt(new Tag(strings));
        }
        user.addLink(link);
        genericDAO.createObject(link);

        // Return list of suggested tags
        Map<String, Integer> suggestion = Crawler.grabURLContent(link.getName());
        return Collections.unmodifiableSet(suggestion.keySet());
    }

    @Override
    public Link updateLink(Link link)
    {
        return null;
    }

    @Override
    public void deleteLink(Link link)
    {
        genericDAO.deleteObject(link);
    }

    @Override
    public void deleteById(long id)
    {
        genericDAO.deleteById(id, Link.class);
    }

    @Override
    public List<Link> getAllLinks()
    {
        return genericDAO.getAllObjects(Link.class);
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

    @Override
    public List<Link> fetchByInputString(String input)
    {
        return genericDAO.fetchByInputString(Link.class, input);
    }
}
