package com.dastko.devbookmarks.service;

import com.dastko.devbookmarks.entity.BookElasticsearch;
import com.dastko.devbookmarks.helpers.LinkWrapper;
import com.dastko.devbookmarks.entity.Link;

import java.security.Principal;
import java.util.List;
import java.util.Set;

/**
 * Created by dastko
 */
public interface LinkService
{
    public Set<String> createLink(LinkWrapper linkWrapper, Principal principal);

    public Link updateLink(Link link);

    public void deleteLink(Link link);

    public void deleteById(long id);

    public List<Link> getAllLinks();

    public Link getLink(long id);

    public List<Link> getAllLinks(String linkName);

    boolean exists(String name);

    public List<Link> fetchByInputString(String input);

    public List <LinkWrapper> getAllLinksByUserId(Long id);

    }
