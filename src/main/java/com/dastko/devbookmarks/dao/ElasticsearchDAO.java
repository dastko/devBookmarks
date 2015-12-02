package com.dastko.devbookmarks.dao;

import com.dastko.devbookmarks.entity.BookElasticsearch;
import com.dastko.devbookmarks.entity.Link;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

/**
 * Created by dastko
 */
@Repository
public interface ElasticsearchDAO extends ElasticsearchRepository<BookElasticsearch, String>
{
    Page<BookElasticsearch> findByTagsName(String name, Pageable pageable);
}
