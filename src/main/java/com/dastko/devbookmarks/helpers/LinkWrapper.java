package com.dastko.devbookmarks.helpers;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by dastko
 */
public class LinkWrapper implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Size(min = 8, max = 250)
    private String name;
    @Size(max = 200)
    @NotNull
    private String details;
    @Size(max = 300)
    @NotNull
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
