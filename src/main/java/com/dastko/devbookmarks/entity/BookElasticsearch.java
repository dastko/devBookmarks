package com.dastko.devbookmarks.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dastko on 11/13/15.
 */
@Document(indexName = "book", type = "book", shards = 1, replicas = 1)
public class BookElasticsearch
{
    @Id
    private String id;
    private String name;
    private Long price;
    @Version
    private Long version;
    @Field(type = FieldType.Nested)
    private List<Tag> tags;

    public BookElasticsearch()
    {
    }

    public BookElasticsearch(String id, String name, Long version)
    {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getPrice()
    {
        return price;
    }

    public void setPrice(Long price)
    {
        this.price = price;
    }

    public long getVersion()
    {
        return version;
    }

    public void setVersion(Long version)
    {
        this.version = version;
    }

    public List<Tag> getTags()
    {
        return tags;
    }

    public void setTags(List<Tag> tags)
    {
        this.tags = tags;
    }
}
