package com.dastko.devbookmarks.service;

import com.dastko.devbookmarks.dto.LinkDTO;
import com.dastko.devbookmarks.entity.Link;

import java.security.Principal;
import java.util.List;

/**
 * Created by dastko on 11/5/15.
 */
public interface LinkService
{
    public void createLink(Link link);

    public List <Link> createLinkParametars(LinkDTO link);

    public void createTestLink(LinkDTO linkDTO, Principal principal);

    public Link updateLink(Link link);

    public void deleteLink(Link link);

    public void deleteById(long id);

    public List<Link> getAllLinks();

    public Link getLink(long id);

    public List<Link> getAllLinks(String linkName);

    boolean exists(String name);

    public List<Link> fetchByInputString(String input);

}
