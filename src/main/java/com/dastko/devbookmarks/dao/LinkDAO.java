package com.dastko.devbookmarks.dao;

import com.dastko.devbookmarks.entity.Link;

import java.util.List;

/**
 * Created by dastko on 11/5/15.
 */
public interface LinkDAO
{
    public Link createLink(Link link);

    public Link updateLink(Link link);

    public void deleteLink(Link link);

    public List<Link> getAllLinks();

    public Link getLink(long id);

    public List<Link> getAllLinks(String linkName);

}
