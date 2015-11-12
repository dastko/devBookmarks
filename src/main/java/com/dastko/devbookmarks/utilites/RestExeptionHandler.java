package com.dastko.devbookmarks.utilites;

import com.dastko.devbookmarks.error.ApiErrors;
import com.dastko.devbookmarks.error.InvalidRequestException;
import com.dastko.devbookmarks.error.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by dastko
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExeptionHandler extends ResponseEntityExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(RestExeptionHandler.class);

    @Inject
    private MessageSource messageSource;

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    @ResponseBody
    public ResponseEntity<ResponseMessage> handleGenericException(Exception ex, WebRequest request)
    {
        if (log.isDebugEnabled())
        {
            log.debug("handling exception...");
        }
        return new ResponseEntity<>(new ResponseMessage(ResponseMessage.Type.danger, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseBody
    public ResponseEntity<ResponseMessage> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request)
    {
        if (log.isDebugEnabled())
        {
            log.debug("handling ResourceNotFoundException...");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {InvalidRequestException.class})
    public ResponseEntity<ResponseMessage> handleInvalidRequestException(InvalidRequestException ex, WebRequest req)
    {
        if (log.isDebugEnabled())
        {
            log.debug("handling InvalidRequestException...");
        }

        ResponseMessage alert = new ResponseMessage(
                ResponseMessage.Type.danger,
                ApiErrors.INVALID_REQUEST,
                messageSource.getMessage(ApiErrors.INVALID_REQUEST, new String[]{}, null));

        BindingResult result = ex.getErrors();

        List<FieldError> fieldErrors = result.getFieldErrors();

        if (!fieldErrors.isEmpty())
        {
            fieldErrors.stream().forEach((e) -> {
                alert.addError(e.getField(), e.getCode(), e.getDefaultMessage());
            });
        }

        return new ResponseEntity<>(alert, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
