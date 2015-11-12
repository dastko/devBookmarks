package com.dastko.devbookmarks.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dastko
 */
@Entity
public class Link implements Serializable
{
    private static final long serialVersionUID = -7988799579036225137L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column
    private Date date = new Date();
    @Column
    private String details;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "link", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Tag> tags = new HashSet<>();

    public Link()
    {

    }

    public Link(User user, Date date, String name, String details)
    {
        this.user = user;
        this.date = date;
        this.name = name;
        this.details = details;
    }

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public Set<Tag> getTags()
    {
        return tags;
    }

    public void setTags(Set<Tag> tags)
    {
        this.tags = tags;
    }

    public void tagIt(Tag tag)
    {
        tag.setLink(this);
        getTags().add(tag);
    }
}
