package com.dastko.devbookmarks.helpers;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @JsonProperty("tags")
    private List<Tag> tags = new ArrayList<>();

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

    public List<Tag> getTags()
    {
        return tags;
    }

    public void setTags(List<Tag> tags)
    {
        this.tags = tags;
    }

    // Must be static
    public static class Tag
    {
        private String text;

        public Tag()
        {
        }

        public String getText()
        {
            return text;
        }

        public void setText(String text)
        {
            this.text = text;
        }
    }

    public static class Tag2
    {
        private String name;

        public Tag2()
        {
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


}
