package com.dastko.devbookmarks.controller;

import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.service.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by dastko on 11/5/15.
 */
@Controller
public class LinkController
{

    private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

    public LinkController()
    {

    }
    @Autowired
    private LinkService linkService;

    /**
     * Simply selects the home view to render by returning its name.
     */
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String home(Locale locale, Model model) {
//        logger.info("Welcome home! The client locale is {}.", locale);
//
//        Date date = new Date();
//        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//
//        String formattedDate = dateFormat.format(date);
//
//        model.addAttribute("serverTime", formattedDate );
//
//        return "home";
//    }


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

    @RequestMapping(value = {"getAllLinks", "/"})
    public ModelAndView getAllLinks()
    {
        logger.info("Getting the all Links.");
        List<Link> linkList = linkService.getAllLinks();
        return new ModelAndView("linkList", "linkList", linkList);
    }


}
