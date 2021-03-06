package com.dastko.devbookmarks.utilites;

import de.l3s.boilerpipe.extractors.ArticleExtractor;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.*;

/**
 * Created by dastko
 */
public class Crawler
{

    public static Map<String, Integer> grabURLContent(String content)
    {
        List<String> list = new ArrayList<String>();

        try
        {
            URL url = new URL(content);
            InputSource is = new InputSource();
            is.setEncoding("ISO-8859-1");
            is.setEncoding("UTF-8");
            is.setByteStream(url.openStream());
            String text = ArticleExtractor.INSTANCE.getText(is);
            String[] parsed = text.split(" ");
            for (String str : parsed)
            {
                // remove dot in string (place for regex - remove multiple chars) "\\W",
                if (str.contains("."))
                {
                    str = str.replaceAll("[^\\w]", "");
                }
                if (str.length() > 4)
                {
                    list.add(str);
                }
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }

        Map<String, Integer> map = new HashMap<String, Integer>();

        for (String temp : list)
        {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
        Map<String, Integer> tags = returnSuggestion(map);
        Map<String, Integer> suggestion = sortByComparator(tags);
        return suggestion;
    }

    private static Map<String, Integer> returnSuggestion(Map<String, Integer> map)
    {

        Map<String, Integer> suggestedTags = new HashMap<>();

        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            if (entry.getValue() >= 2)
            {
                suggestedTags.put(entry.getKey().toLowerCase(), entry.getValue());
            }
        }
        return suggestedTags;
    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortedMap)
    {

        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(unsortedMap.entrySet());
        //lamda expression inseade anonymous inner class creation
        Collections.sort(list, (Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) -> o2.getValue().compareTo(o1.getValue()));
        int i = 0;
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); )
        {
            Map.Entry<String, Integer> entry = it.next();
            i++;
            if (i <= 10)
                sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
