package com.dastko.devbookmarks.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dastko
 */
@Entity
public class User implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Link> links = new ArrayList<>();

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

    public void addLink(Link link)
    {
        link.setUser(this);
        getLinks().add(link);
    }
}
