package com.dastko.devbookmarks.service;

import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.entity.Tag;
import com.dastko.devbookmarks.helpers.LinkWrapper;

import java.util.List;
import java.util.Set;

/**
 * Created by dastko
 */
public interface TagService
{
    public Set<Tag> getRecommendedTags(String input);
    public Set<String> fetchByInputString(String input);



}
