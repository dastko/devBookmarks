package com.dastko.devbookmarks.service;

import com.dastko.devbookmarks.entity.BookElasticsearch;
import com.dastko.devbookmarks.entity.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by dastko on 12/2/15.
 */
public interface ElasticsearchService
{
    Link save(BookElasticsearch post);
    Link findOne(String id);
    Iterable<BookElasticsearch> findAll();
    Page<BookElasticsearch> findByTagsName(String tagName, PageRequest pageRequest);
}
