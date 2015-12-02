package com.dastko.devbookmarks.helpers;

import com.dastko.devbookmarks.entity.Link;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dastko on 12/2/15.
 */
public class PaginationWrapper implements Serializable
{
    private long count;
    private List<Link> linkList;
    private boolean hasPrevious;
    private boolean hasNext;

    public PaginationWrapper()
    {
    }

    public long getCount()
    {
        return count;
    }

    public void setCount(long count)
    {
        this.count = count;
    }

    public List<Link> getLinkList()
    {
        return linkList;
    }

    public void setLinkList(List<Link> linkList)
    {
        this.linkList = linkList;
    }

    public boolean isHasPrevious()
    {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious)
    {
        this.hasPrevious = hasPrevious;
    }

    public boolean isHasNext()
    {
        return hasNext;
    }

    public void setHasNext(boolean hasNext)
    {
        this.hasNext = hasNext;
    }
}
