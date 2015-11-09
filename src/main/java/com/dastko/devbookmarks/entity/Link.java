package com.dastko.devbookmarks.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by dastko on 11/5/15.
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
    @ManyToOne
    private User user;
    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    @JsonIgnore
    private Set<Tag> tags;

    public Link()
    {

    }

    public Link(User user, Date date, String name)
    {
        this.user = user;
        this.date = date;
        this.name = name;
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
}
