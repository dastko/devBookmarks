package com.dastko.devbookmarks.utilites;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dastko on 11/9/15.
 */
public class ValidationUtils
{

    private ValidationUtils()
    {
        throw new NotImplementedException("Utility classes cannot be instantiated");
    }

    public static void assertNotBlank(String field, String message)
    {
        if (StringUtils.isBlank(field))
        {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertSizeLength(String field, int minLength, int maxLenght, String message)
    {
        if (field.length() < minLength || field.length() > maxLenght)
        {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertMatches(String email, Pattern regex, String message)
    {
        if (!regex.matcher(email).matches())
        {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertFieldConfirmation(String field1, String field2, String message)
    {
        if (!field1.equals(field2))
        {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isValidURL(String url, String message)
    {
        URL u = null;

        try
        {
            u = new URL(url);
        } catch (MalformedURLException e)
        {
            throw new IllegalArgumentException(message);
        }

        try
        {
            u.toURI();
        } catch (URISyntaxException e)
        {
            throw new IllegalArgumentException(message);
        }
    }

    public static void urlValidation(String url, String message)
    {
        String regex = "((https?://|ftp://|www\\.|[^\\s:=]+@www\\.).*?[a-z_\\/0-9\\-\\#=&])(?=(\\.|,|;|\\?|\\!)?(\"|'|«|»|\\[|\\s|\\r|\\n|$))";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(url);
        if(!matcher.matches())
        {
            throw new IllegalArgumentException(message);
        }
    }
}
