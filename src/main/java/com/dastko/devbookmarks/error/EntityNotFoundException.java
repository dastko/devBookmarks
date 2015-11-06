package com.dastko.devbookmarks.error;

/**
 * Created by dastko on 11/6/15.
 */
public class EntityNotFoundException extends Exception
{
    public EntityNotFoundException(String message)
    {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
