package com.dastko.devbookmarks.controller;

import com.dastko.devbookmarks.dto.LinkDTO;
import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.service.LinkService;
import com.dastko.devbookmarks.utilites.JsonResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @RequestMapping("createLink")
    public ModelAndView createLink(@ModelAttribute Link link)
    {
        logger.info("Creating Link. Data: " + link);
        return new ModelAndView("linkForm");
    }

    @RequestMapping("saveLink")
    public ModelAndView saveLink(@ModelAttribute Link link)
    {
        logger.info("Saving Link. Data : " + link.getName());
        if (link.getId() == 0)
        { // if link id is 0 then creating the link other updating the link
            linkService.createLink(link);
        } else
        {
        }
        return new ModelAndView("redirect:");
    }

    @RequestMapping(value = "/addlink", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public LinkDTO saveLinkDTO(@Valid @RequestBody LinkDTO link, BindingResult bindingResult, Errors errors)
    {
        if(errors.hasErrors())
        {
            throw new IllegalArgumentException(errors.getFieldError().getDefaultMessage());
        }
        linkService.createLinkParametars(link.getLink());
        return new LinkDTO(link.getLink());
    }

    @RequestMapping(value = {"getAllLinks", "/"})
    public ModelAndView getAllLinks()
    {
        logger.info("Getting the all Links.");
        List<Link> linkList = linkService.getAllLinks();
        return new ModelAndView("linkList", "linkList", linkList);
    }

    @RequestMapping("/links")
    public List<Link> getAllLinksApi()
    {
        return Collections.unmodifiableList(linkService.getAllLinks());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Link> saveLink2(@RequestBody Link link)
    {
        HttpStatus status = HttpStatus.OK;
        if (!linkService.exists(link.getName()))
        {
            status = HttpStatus.CREATED;
        }
        linkService.createLink(link);
        return new ResponseEntity<Link>(link, status);
    }

    @RequestMapping("deleteLink")
    public ModelAndView deleteLink(@RequestParam long id)
    {
        logger.info("Deleting the Link. Id : " + id);
        Link link = new Link();
        link.setId(id);
        linkService.deleteLink(link);
        return new ModelAndView("redirect:");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ObjectNode> errorHandler(Exception exc) {
        logger.error(exc.getMessage(), exc);
        return new ResponseEntity<ObjectNode>(JsonResponse.buildJsonResponse("error", exc.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
