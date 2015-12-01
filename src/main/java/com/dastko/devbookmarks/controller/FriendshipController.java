package com.dastko.devbookmarks.controller;

import com.dastko.devbookmarks.entity.Friendship;
import com.dastko.devbookmarks.helpers.FriendshipWrapper;
import com.dastko.devbookmarks.service.FriendshipService;
import com.dastko.devbookmarks.utilites.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by dastko
 */
@RestController
public class FriendshipController
{
    @Autowired
    private FriendshipService friendshipService;

    @RequestMapping(value = "api/addFriend", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> saveFriendship(@RequestBody FriendshipWrapper friendship)
    {
        friendshipService.addFriendship(friendship.getRequesterId(), friendship.getAccepterId());
        return new ResponseEntity<>(ResponseMessage.success("Added"), HttpStatus.OK);
    }

    @RequestMapping(value = "api/getFriends/{id}", method = RequestMethod.GET)
    public ResponseEntity<Set<Friendship>> getFriendRequests(@PathVariable("id") long id)
    {
        return new ResponseEntity<>(friendshipService.getFriendRequests(id), HttpStatus.OK);
    }
}
