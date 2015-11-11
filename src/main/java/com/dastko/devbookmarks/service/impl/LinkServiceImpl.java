package com.dastko.devbookmarks.service.impl;

import com.dastko.devbookmarks.dao.GenericDAO;
import com.dastko.devbookmarks.dao.LinkDAO;
import com.dastko.devbookmarks.dto.LinkDTO;
import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.entity.Tag;
import com.dastko.devbookmarks.entity.User;
import com.dastko.devbookmarks.service.LinkService;
import com.dastko.devbookmarks.utilites.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.*;
import java.util.jar.JarEntry;

/**
 * Created by dastko on 11/5/15.
 */
@Service
@Transactional
public class LinkServiceImpl implements LinkService
{
    private String search = null;

    public LinkServiceImpl()
    {

    }

    @Autowired
    private LinkDAO linkDAO;

    @Autowired
    private GenericDAO genericDAO;


    @Override
    public void createLink(Link link)
    {
        linkDAO.createLink(link);
    }


    /**
     * save the transient instance before flushing
     *
     * @param link
     */
    @Override
    public List<Link> createLinkParametars(LinkDTO link)
    {
        final String name = link.getName();
        ValidationUtils.assertSizeLength(name, 6, 250, "Minimum 6, Maximum 250 characters");
        ValidationUtils.assertNotBlank(name, "Not null");
        ValidationUtils.urlValidation(name, "Invalid URL");
        urlValidation(name);
        System.out.println("GET HOST: " + search);

        List<Link> linksByInput = linkDAO.fetchByInputString(name);
        Set<Tag> recommendedTags = new HashSet<>();
        if (linksByInput != null)
        {
            for (Link links : linksByInput)
            {
                recommendedTags.addAll(links.getTags());
            }
        }

        link.setName(name);
        Link linkMapper = DTOUtils.map(link, Link.class);
        //TODO retrieve user from session
        User user = new User();
        user.setId(1);
        linkMapper.setUser(user);
        linkDAO.createLink(linkMapper);

        //return DTOUtils.map(linkMapper, LinkDTO.class);
        return linksByInput;
    }

    public void createTestLink(LinkDTO linkDTO, Principal principal)
    {
        //principal.getName();
        User user = new User();
        user.setId(2);

        String[] splitTagsByComma = linkDTO.getTags().trim().split("\\s*,\\s*");
        Link link = new Link();
        link.setUser(user);
        link.setDate(new Date());
        link.setName(linkDTO.getName());
        link.setDetails(linkDTO.getDetails());
        for (String strings : splitTagsByComma)
        {
            link.tagIt(new Tag(strings));
        }
        user.addLink(link);
        genericDAO.createLink(user);
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
    public void deleteById(long id)
    {
        linkDAO.deleteById(id);
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

    @Override
    public List<Link> fetchByInputString(String input)
    {
        return linkDAO.fetchByInputString(input);
    }

    public void urlValidation(String name)
    {
        if (name.contains("http://") || name.contains("https://"))
        {
            try
            {
                URI url = new URI(name);
                search = url.getHost();
            } catch (Exception e)
            {
                search = null;
            }
        } else if (name.contains("www.") && !name.contains("http://") && !name.contains("https://"))
        {
            StringBuilder sb = new StringBuilder();
            sb.append("http://");
            sb.append(name);
            try
            {
                URI uri = new URI(sb.toString());
                uri.getHost();
                search = uri.getHost();
            } catch (URISyntaxException e)
            {
            }
        } else
        {
            search = name;
        }
    }
}
