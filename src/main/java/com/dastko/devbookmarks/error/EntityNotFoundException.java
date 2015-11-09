package com.dastko.devbookmarks.error;

/**
 * Created by dastko on 11/6/15.
 */

/**
 * @ExceptionHandler (Exception.class)
 * @ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
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
