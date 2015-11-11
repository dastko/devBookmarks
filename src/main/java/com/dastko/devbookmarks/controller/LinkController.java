package com.dastko.devbookmarks.controller;

import com.dastko.devbookmarks.dao.impl.LinkDAOImpl;
import com.dastko.devbookmarks.dto.LinkDTO;
import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.entity.Tag;
import com.dastko.devbookmarks.error.ResourceNotFoundException;
import com.dastko.devbookmarks.service.LinkService;
import com.dastko.devbookmarks.utilites.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    public ModelAndView createLink(@ModelAttribute("link") LinkDTO link)
    {
        logger.info("Creating Link. Data: " + link);
        return new ModelAndView("linkForm");
    }


    @RequestMapping(value = "saveLink", method = RequestMethod.POST)
    public ModelAndView saveLink(@ModelAttribute LinkDTO link, Principal principal)
    {
        logger.info("Saving Link. Data : " + principal.getName() + "get: " + link);
        linkService.createTestLink(link, principal);
        return new ModelAndView("redirect:");
    }

    @RequestMapping(value = "/addlink", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<String>> saveLinkDTO(@RequestBody LinkDTO link)
    {
        linkService.createLinkParametars(link);
        Map<String, Integer> sugesstion = Crawler.grabURLContent(link.getName());
        List<String> values = new ArrayList<>(sugesstion.keySet());
        return new ResponseEntity<>(values, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/addtest", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> savetest(@RequestBody LinkDTO linkDTO, Principal principal)
    {
        //Link link = DTOUtils.map(linkDTO, Link.class);
        linkService.createTestLink(linkDTO, principal);
        return new ResponseEntity<>(ResponseMessage.success("Successfully created!"), HttpStatus.OK);
    }

    @RequestMapping(value = {"getAllLinks", "/"})
    public ModelAndView getAllLinks()
    {
        logger.info("Getting the all Links.");
        List<Link> linkList = linkService.getAllLinks();
        return new ModelAndView("linkList", "linkList", linkList);
    }

    @RequestMapping(value = "/test")
    public ResponseEntity<List<Link>> test(@RequestBody LinkDTO link)
    {
        //TODO retrieve only some information
        List<Link> links = linkService.fetchByInputString(link.getName());
        return new ResponseEntity<>(Collections.unmodifiableList(links), HttpStatus.OK);
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
        return new ResponseEntity<>(link, status);
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
}
