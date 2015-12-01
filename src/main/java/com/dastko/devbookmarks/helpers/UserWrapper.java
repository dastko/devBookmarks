package com.dastko.devbookmarks.helpers;

import java.io.Serializable;

/**
 * Created by dastko on 11/30/15.
 */
public class UserWrapper implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String name;
    private String details;

    public UserWrapper(String name, String details)
    {
        this.name = name;
        this.details = details;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
