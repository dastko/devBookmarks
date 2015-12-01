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
    private User friendRequester;
    @ManyToOne
    private User friendAccepter;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column
    private Date date = new Date();
    private boolean confirmedFriendship;
    private boolean deleteRequest;

    public Friendship()
    {
    }

    public Friendship(User friendRequester, User friendAccepter)
    {
        this.friendAccepter = friendAccepter;
        this.friendRequester = friendRequester;
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

    public boolean isConfirmedFriendship()
    {
        return confirmedFriendship;
    }

    public void setConfirmedFriendship(boolean confirmedFriendship)
    {
        this.confirmedFriendship = confirmedFriendship;
    }

    public boolean isDeleteRequest()
    {
        return deleteRequest;
    }

    public void setDeleteRequest(boolean deleteRequest)
    {
        this.deleteRequest = deleteRequest;
    }
}
