package com.dastko.devbookmarks.service.impl;

import com.dastko.devbookmarks.dao.GenericDAO;
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
    private GenericDAO genericDAO;

    @Override
    public Set<String> createLink(LinkDTO linkDTO, Principal principal)
    {
        final String name = linkDTO.getName();
        ValidationUtils.assertSizeLength(name, 8, 250, "Minimum 8, Maximum 250 characters");
        ValidationUtils.assertNotBlank(name, "Not null");
        ValidationUtils.urlValidation(name, "Invalid URL");

        //User user = (User)((Authentication) principal).getPrincipal();
        User user = new User();
        user.setId(2);
        String[] splitTagsByComma = linkDTO.getTags().trim().split("\\s*,\\s*");
        Link link = new Link(user, new Date(), linkDTO.getName(), linkDTO.getDetails());
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

    @Override
    public Set<Tag> getRecommendedTags(String input)
    {
        urlValidation(input);
        List<Link> linksByInput = genericDAO.fetchByInputString(Link.class, search);
        Set<Tag> recommendedTags = new HashSet<>();
        if (linksByInput != null)
        {
            for (Link links : linksByInput)
            {
                recommendedTags.addAll(links.getTags());
            }
        }
        return recommendedTags;
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
