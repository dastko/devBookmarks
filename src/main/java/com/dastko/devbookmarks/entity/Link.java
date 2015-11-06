package com.dastko.devbookmarks.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dastko on 11/5/15.
 */
@Entity
public class Link implements Serializable
{
    private static final long serialVersionUID = -7988799579036225137L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column
    private Date date = new Date();

    public Link()
    {

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
}
