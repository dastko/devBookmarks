package com.dastko.devbookmarks.service.impl;

import com.dastko.devbookmarks.dao.GenericDAO;
import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.entity.Tag;
import com.dastko.devbookmarks.service.TagService;
import com.dastko.devbookmarks.utilites.URLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dastko
 */
@Service
@Transactional
public class TagServiceImpl implements TagService
{

    @Autowired
    private GenericDAO genericDAO;

    @Override
    public Set<Tag> getRecommendedTags(String input)
    {
        String search = URLValidator.urlValidation(input);
        List<Link> linksByInput = genericDAO.fetchByInputString(Link.class, search);
        Set<Tag> recommendedTags = new HashSet<>();
        if (linksByInput != null)
        {
            for (Link links : linksByInput)
            {
                recommendedTags.addAll(links.getTags());
            }
        }
        return Collections.unmodifiableSet(recommendedTags);
    }
}
