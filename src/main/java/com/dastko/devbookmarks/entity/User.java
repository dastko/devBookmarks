package com.dastko.devbookmarks.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dastko
 */
@Entity
public class User implements Serializable
{
    private static final long serialVersionUID = -7988799579036225137L;

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Link> links = new ArrayList<>();
    @OneToMany(mappedBy = "friendRequester", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Friendship> requestedFriends = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "friendAccepter", cascade = CascadeType.ALL)
    private Set<Friendship> receivedFriends = new HashSet<>();

    public User()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public List<Link> getLinks()
    {
        return links;
    }

    public void setLinks(List<Link> links)
    {
        this.links = links;
    }

    public Set<Friendship> getRequestedFriends()
    {
        return requestedFriends;
    }

    public void setRequestedFriends(Set<Friendship> requestedFriends)
    {
        this.requestedFriends = requestedFriends;
    }

    public Set<Friendship> getReceivedFriends()
    {
        return receivedFriends;
    }

    public void setReceivedFriends(Set<Friendship> receivedFriends)
    {
        this.receivedFriends = receivedFriends;
    }

    public void addLink(Link link)
    {
        link.setUser(this);
        getLinks().add(link);
    }

    public void sendFriendRequest(Friendship friendRequester)
    {
        requestedFriends.add(friendRequester);
    }
}
