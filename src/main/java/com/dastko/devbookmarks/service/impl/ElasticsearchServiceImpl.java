package com.dastko.devbookmarks.service.impl;

import com.dastko.devbookmarks.dao.ElasticsearchDAO;
import com.dastko.devbookmarks.entity.BookElasticsearch;
import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.service.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


/**
 * Created by dastko
 */
@Service
public class ElasticsearchServiceImpl implements ElasticsearchService
{

//    @Autowired
    private ElasticsearchDAO elasticsearchDAO;

    @Override
    public Link save(BookElasticsearch post)
    {
        return null;
    }

    @Override
    public Link findOne(String id)
    {
        return null;
    }

    @Override
    public Iterable<BookElasticsearch> findAll()
    {
        elasticsearchDAO.findAll();
        return elasticsearchDAO.findAll();
    }

    @Override
    public Page<BookElasticsearch> findByTagsName(String tagName, PageRequest pageRequest)
    {
        return null;
    }
}
