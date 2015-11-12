package com.dastko.devbookmarks.exception;

/**
 * Created by dastko
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
