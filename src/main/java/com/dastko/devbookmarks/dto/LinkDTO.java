package com.dastko.devbookmarks.dto;

import com.dastko.devbookmarks.entity.Link;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by dastko on 11/9/15.
 */
public class LinkDTO
{
    @Size(min = 9, max = 30)
    @NotNull
    private String link;

    public LinkDTO()
    {

    }

    public LinkDTO(String link)
    {
        this.link = link;
    }

    public static LinkDTO mapFromLinkEntity(Link link)
    {
        return new LinkDTO(link.getName());
    }

    public String getLink()
    {
        return link;
    }
}
