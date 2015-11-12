package com.dastko.devbookmarks.utilites;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by dastko on
 */
public class JsonResponse
{
    public static ObjectNode buildJsonResponse(String type, String message)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode wrapper = objectMapper.createObjectNode();
        ObjectNode msg = objectMapper.createObjectNode();
        msg.put("message", message);
        wrapper.put(type, message);
        return wrapper;
    }
}
