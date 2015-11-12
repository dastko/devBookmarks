package com.dastko.devbookmarks.controller;

import com.dastko.devbookmarks.service.TagService;
import com.dastko.devbookmarks.wrapper.LinkWrapper;
import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.entity.Tag;
import com.dastko.devbookmarks.service.LinkService;
import com.dastko.devbookmarks.utilites.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.*;

/**
 * Created by dastko on 11/5/15.
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

    @RequestMapping("createLink")
    public ModelAndView createLink(@ModelAttribute("link") LinkWrapper link)
    {
        logger.info("Creating Link. Data: " + link);
        return new ModelAndView("linkForm");
    }


    @RequestMapping(value = "saveLink", method = RequestMethod.POST)
    public ResponseEntity<Set<String>> saveLink(@ModelAttribute LinkWrapper link, Principal principal)
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ObjectNode> errorHandler(Exception exc)
    {
        logger.error(exc.getMessage(), exc);
        return new ResponseEntity<>(JsonResponse.buildJsonResponse("error", exc.getMessage()), HttpStatus.BAD_REQUEST);
    }

//    @RequestMapping("/links")
//    public List<Link> getAllLinksApi(@PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page)
//    {
//
//        return Collections.unmodifiableList(linkService.getAllLinks());
//    }
}
