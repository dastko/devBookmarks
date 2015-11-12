package com.dastko.devbookmarks.service;

import com.dastko.devbookmarks.dto.LinkDTO;
import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.entity.Tag;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dastko on 11/5/15.
 */
public interface LinkService
{
    public Set<String> createLink(LinkDTO linkDTO, Principal principal);

    public Set<Tag> getRecommendedTags(String input);

    public Link updateLink(Link link);

    public void deleteLink(Link link);

    public void deleteById(long id);

    public List<Link> getAllLinks();

    public Link getLink(long id);

    public List<Link> getAllLinks(String linkName);

    boolean exists(String name);

    public List<Link> fetchByInputString(String input);

}
