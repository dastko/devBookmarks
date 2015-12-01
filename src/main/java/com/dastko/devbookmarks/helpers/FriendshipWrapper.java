package com.dastko.devbookmarks.helpers;

import java.io.Serializable;

/**
 * Created by dastko on 12/1/15.
 */
public class FriendshipWrapper implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long accepterId;
    private Long requesterId;

    public FriendshipWrapper()
    {

    }
    public FriendshipWrapper(Long accepterId, Long requesterId)
    {
        this.accepterId = accepterId;
        this.requesterId = requesterId;
    }

    public Long getAccepterId()
    {
        return accepterId;
    }

    public void setAccepterId(Long accepterId)
    {
        this.accepterId = accepterId;
    }

    public Long getRequesterId()
    {
        return requesterId;
    }

    public void setRequesterId(Long requesterId)
    {
        this.requesterId = requesterId;
    }
}
