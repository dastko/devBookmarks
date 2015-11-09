package com.dastko.devbookmarks.entity;

import javax.persistence.*;

/**
 * Created by dastko on 11/9/15.
 */
@Entity
public class Tag
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private Link link;

}
