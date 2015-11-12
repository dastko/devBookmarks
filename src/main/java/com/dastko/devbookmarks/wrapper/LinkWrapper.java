package com.dastko.devbookmarks.wrapper;

import java.io.Serializable;

/**
 * Created by dastko
 */
public class LinkWrapper implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String name;
    private String details;
    private String tags;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public String getTags()
    {
        return tags;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }
}
