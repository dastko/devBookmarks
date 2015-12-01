package com.dastko.devbookmarks.service;

import com.dastko.devbookmarks.entity.Friendship;

import java.util.Set;

/**
 * Created by dastko on 12/1/15.
 */
public interface FriendshipService
{
    public void addFriendship(Long friendRequester, Long friendAccepter);
    public Set<Friendship> getFriendRequests(Long id);

}
