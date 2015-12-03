package com.dastko.devbookmarks.helpers;


import java.io.Serializable;
import java.util.List;

/**
 * Created by dastko
 */
public class PaginationWrapper <T> implements Serializable
{
    private long count;
    private boolean hasPrevious;
    private boolean hasNext;
    private List<T> linkList;

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

    public List<T> getLinkList()
    {
        return linkList;
    }

    public void setLinkList(List<T> linkList)
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
