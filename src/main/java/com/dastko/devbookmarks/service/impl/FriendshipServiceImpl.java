package com.dastko.devbookmarks.service.impl;

import com.dastko.devbookmarks.dao.GenericDAO;
import com.dastko.devbookmarks.entity.Friendship;
import com.dastko.devbookmarks.entity.User;
import com.dastko.devbookmarks.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

/**
 * Created by dastko
 */
@Service
@Transactional
public class FriendshipServiceImpl implements FriendshipService
{
    @Autowired
    private GenericDAO genericDAO;

    @Override
    public void addFriendship(Long friendRequester, Long friendAccepter)
    {
        User accepter = genericDAO.fetchById(friendRequester, User.class);
        User requester = genericDAO.fetchById(friendAccepter, User.class);

        Friendship friendship = new Friendship(accepter,requester);
        requester.sendFriendRequest(friendship);

        genericDAO.create(accepter);
    }

    @Override
    public Set<Friendship> getFriendRequests(Long id)
    {
        User accepter = genericDAO.fetchById(id, User.class);
        return Collections.unmodifiableSet(accepter.getRequestedFriends());
    }
}
