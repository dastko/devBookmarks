package com.dastko.devbookmarks.dao;

import com.dastko.devbookmarks.entity.Link;
import com.dastko.devbookmarks.entity.User;

import java.util.List;

/**
 * Created by dastko on 11/10/15.
 */
public interface UserDAO
{
    public Link createUser(User user);

    public Link updateUser(User user);

    public void deleteUser(User user);

    public List<User> getAllUsers();

    public User getUser(long id);
}
