package com.dastko.devbookmarks.service;

import com.dastko.devbookmarks.entity.Tag;

import java.util.Set;

/**
 * Created by dastko
 */
public interface TagService
{
    public Set<Tag> getRecommendedTags(String input);

}
