package com.dastko.devbookmarks.controller;

import com.dastko.devbookmarks.entity.BookElasticsearch;
import com.dastko.devbookmarks.helpers.PaginationWrapper;
import com.dastko.devbookmarks.helpers.UserWrapper;
import com.dastko.devbookmarks.service.ElasticsearchService;
import com.dastko.devbookmarks.service.TagService;
import com.dastko.devbookmarks.helpers.LinkWrapper;
import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.entity.Tag;
import com.dastko.devbookmarks.service.LinkService;
import com.dastko.devbookmarks.utilites.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.*;

/**
 * Created by dastko
 */
@RestController
public class LinkController
{

    private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

    public LinkController()
    {
    }

    @Autowired
    private LinkService linkService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ElasticsearchService elasticsearchDAO;


    @RequestMapping("createLink")
    public ModelAndView createLink(@ModelAttribute("link") LinkWrapper link)
    {
        logger.info("Creating Link. Data: " + link.getTags());
        return new ModelAndView("linkForm");
    }


    @RequestMapping(value = "api/saveLink", method = RequestMethod.POST)
    public ResponseEntity<Set<String>> saveLink(@RequestBody LinkWrapper link, Principal principal, BindingResult bindingResult)
    {
        return new ResponseEntity<>(linkService.createLink(link, principal), HttpStatus.OK);
    }

    @RequestMapping(value = "/suggestion")
    public ResponseEntity<Set<Tag>> getRecommendedTags(@RequestParam("input") String input)
    {
        return new ResponseEntity<>(tagService.getRecommendedTags(input), HttpStatus.OK);
    }

    @RequestMapping(value = {"getAllLinks", "/"})
    public ModelAndView getAllLinks()
    {
        logger.info("Getting the all Links.");
        List<Link> linkList = linkService.getAllLinks();
        return new ModelAndView("linkList", "linkList", linkList);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> deleteLink(@PathVariable("id") long id)
    {
        logger.info("Deleting the Link. Id : " + id);
        linkService.deleteById(id);
        return new ResponseEntity<>(ResponseMessage.success("Post Deleted"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="api/user")
    public UserWrapper user(Principal user) {
        final UserWrapper userWrapper = new UserWrapper(user.getName(), "");
        return userWrapper;
    }

    @RequestMapping(value="/api/tags/{input}", method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getTagListByUserInput(@PathVariable String input)
    {
        return new ResponseEntity<>(tagService.fetchByInputString(input), HttpStatus.OK);
    }

    @RequestMapping(value="/api/user/links", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LinkWrapper>> getLinksByUser(Principal principal)
    {
        return new ResponseEntity<>(linkService.getAllLinksByUserId(2L), HttpStatus.OK);
    }

    @RequestMapping(value="api/pagination/{number}", method = RequestMethod.GET)
    public ResponseEntity<PaginationWrapper> links(@PathVariable int number)
    {
        return new ResponseEntity<>(linkService.pagination(number), HttpStatus.OK);
    }

}
