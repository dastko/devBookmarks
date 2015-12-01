package com.dastko.devbookmarks.service.impl;

import com.dastko.devbookmarks.config.ElasticsearchConfiguration;
import com.dastko.devbookmarks.dao.GenericDAO;
import com.dastko.devbookmarks.entity.BookElasticsearch;
import com.dastko.devbookmarks.helpers.LinkWrapper;
import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.entity.Tag;
import com.dastko.devbookmarks.entity.User;
import com.dastko.devbookmarks.service.LinkService;
import com.dastko.devbookmarks.utilites.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.OxmSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.security.Principal;
import java.util.*;

/**
 * Created by dastko
 */
@Service
@Transactional
public class LinkServiceImpl implements LinkService
{

    public LinkServiceImpl()
    {
    }

    @Autowired
    private GenericDAO genericDAO;
    @Autowired
    private ElasticsearchOperations elasticsearchTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public Set<String> createLink(LinkWrapper linkWrapper, Principal principal)
    {
        final String name = linkWrapper.getName();
        List <Tag> tags = new ArrayList<>();
        ValidationUtils.assertSizeLength(name, 8, 250, "Minimum 8, Maximum 250 characters");
        ValidationUtils.assertNotBlank(name, "Not null");
        ValidationUtils.urlValidation(name, "Invalid URL");

        //User user = (User)((Authentication) principal).getPrincipal();
        User user = new User();
        user.setId(2);
        //String[] splitTagsByComma = linkWrapper.getTags().trim().split("\\s*,\\s*");
        Link link = new Link(user, new Date(), linkWrapper.getName(), linkWrapper.getDetails());
        for(LinkWrapper.Tag tagger : linkWrapper.getTags()){
            Tag tag = new Tag(tagger.getText());
            link.tagIt(tag);
            tags.add(tag);
        }

        user.addLink(link);
        final String id = Long.toString(link.getId());
        BookElasticsearch bookElasticsearch = new BookElasticsearch(id, link.getName(), 123456L);
        bookElasticsearch.setTags(tags);
        genericDAO.createObject(link, bookElasticsearch);
        List <Link> links = genericDAO.getAllObjects(Link.class);
        //StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //String key = "KEYDAVOR1";
        //addList(links, key, 123456L);
//        RedisSerializer redisSerializer = new OxmSerializer();
//        ListOperations<String, Link> linkListOperations = redisTemplate.opsForList();
//        linkListOperations.rightPush("test", link);
//        add(link);
//        Link link1 = (Link) get("91");
//        System.out.println("LINK NAME: " + link1.getName() + "LINK DETAILS:" + link1.getDetails());
        // Return list of suggested tags
        Map<String, Integer> suggestion = Crawler.grabURLContent(link.getName());
        return Collections.unmodifiableSet(suggestion.keySet());
    }

    public void add(Link link)
    {
        redisTemplate.opsForValue().set(Long.toString(link.getId()), link);
    }
    public void addList(List <Link> links, String key, Long l)
    {
        redisTemplate.opsForList().leftPushAll(key, links);
    }
    public Object get(String key)
    {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Link updateLink(Link link)
    {
        return null;
    }

    @Override
    public void deleteLink(Link link)
    {
        genericDAO.deleteObject(link);
    }

    @Override
    public void deleteById(long id)
    {
        genericDAO.deleteById(id, Link.class);
    }

    @Override
    public List<Link> getAllLinks()
    {
        return genericDAO.getAllObjects(Link.class);
    }

    @Override
    public Link getLink(long id)
    {
        return null;
    }

    @Override
    public List<Link> getAllLinks(String linkName)
    {
        return null;
    }

    @Override
    public boolean exists(String name)
    {
        return false;
    }

    @Override
    public List<Link> fetchByInputString(String input)
    {
        return genericDAO.fetchByInputString(Link.class, input);
    }

    @Override
    public List<LinkWrapper> getAllLinksByUserId(Long id)
    {
        List <Link> links = genericDAO.getAllObjectByUserId(Link.class, id);
        List <LinkWrapper> linkWrappers = DTOUtils.mapList(links, LinkWrapper.class);
        return Collections.unmodifiableList(linkWrappers);
    }
}
