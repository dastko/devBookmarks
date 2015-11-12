package com.dastko.devbookmarks.service;

import com.dastko.devbookmarks.entity.Tag;

import java.util.Set;

/**
 * Created by dastko on 11/12/15.
 */
public interface TagService
{
    public Set<Tag> getRecommendedTags(String input);

}
