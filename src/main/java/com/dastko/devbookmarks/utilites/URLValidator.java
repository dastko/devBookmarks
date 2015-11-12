package com.dastko.devbookmarks.utilites;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by dastko
 */
public class URLValidator
{

    public static String urlValidation(String name)
    {
        String search = null;

        if (name.contains("http://") || name.contains("https://"))
        {
            try
            {
                URI url = new URI(name);
                search = url.getHost();
            } catch (Exception e)
            {
                search = null;
            }
        } else if (name.contains("www.") && !name.contains("http://") && !name.contains("https://"))
        {
            StringBuilder sb = new StringBuilder();
            sb.append("http://");
            sb.append(name);
            try
            {
                URI uri = new URI(sb.toString());
                uri.getHost();
                search = uri.getHost();
            } catch (URISyntaxException e)
            {
            }
        } else
        {
            search = name;
        }
        return search;
    }
}
