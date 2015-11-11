package com.dastko.devbookmarks.dao;

import com.dastko.devbookmarks.entity.Tag;


import java.util.List;

/**
 * Created by dastko on 11/10/15.
 */
public interface TagDAO
{
    public Tag createTag(Tag tag);

    public Tag updateTag(Tag tag);

    public void deleteTag(Tag tag);

    public List<Tag> getAllTags();

    public Tag getTag(long id);
}
