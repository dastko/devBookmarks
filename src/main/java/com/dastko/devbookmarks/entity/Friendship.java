package com.dastko.devbookmarks.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dastko
 */
@Entity
public class Friendship implements Serializable
{
    @Id
    @ManyToOne
    User friendRequester;
    @Id
    @ManyToOne
    User friendAccepter;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column
    Date date = new Date();

    public Friendship()
    {
    }

    public User getFriendAccepter()
    {
        return friendAccepter;
    }

    public void setFriendAccepter(User friendAccepter)
    {
        this.friendAccepter = friendAccepter;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public User getFriendRequester()
    {
        return friendRequester;
    }

    public void setFriendRequester(User friendRequester)
    {
        this.friendRequester = friendRequester;
    }
}
