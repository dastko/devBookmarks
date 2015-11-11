package com.dastko.devbookmarks.utilites;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dastko on 11/10/15.
 */
public class ResponseMessage
{
    public enum Type
    {
        success, warning, danger, info;
    }

    private Type type;
    private String text;
    private String code;

    public ResponseMessage(Type type, String text)
    {
        this.type = type;
        this.text = text;
    }

    public ResponseMessage(Type type, String code, String message)
    {
        this.type = type;
        this.code = code;
        this.text = message;
    }

    public String getText()
    {
        return text;
    }

    public Type getType()
    {
        return type;
    }

    public String getCode()
    {
        return code;
    }

    public static ResponseMessage success(String text)
    {
        return new ResponseMessage(Type.success, text);
    }

    public static ResponseMessage warning(String text)
    {
        return new ResponseMessage(Type.warning, text);
    }

    public static ResponseMessage danger(String text)
    {
        return new ResponseMessage(Type.danger, text);
    }

    public static ResponseMessage info(String text)
    {
        return new ResponseMessage(Type.info, text);
    }

    private List<Error> errors = new ArrayList<Error>();

    public List<Error> getErrors()
    {
        return errors;
    }

    public void setErrors(List<Error> errors)
    {
        this.errors = errors;
    }

    public void addError(String field, String code, String message)
    {
        this.errors.add(new Error(field, code, message));
    }

    private List<TagSugestion> tagSugestions = new ArrayList<>();

    public List<TagSugestion> getTagSugestions()
    {
        return tagSugestions;
    }

    public void setTagSugestions(List<TagSugestion> tagSugestions)
    {
        this.tagSugestions = tagSugestions;
    }

    public void addTagSuggetion(String tag)
    {
        this.tagSugestions.add(new TagSugestion(tag));
    }

    class Error
    {

        private String code;
        private String message;
        private String field;

        private Error(String field, String code, String message)
        {
            this.field = field;
            this.code = code;
            this.message = message;
        }

        public String getCode()
        {
            return code;
        }

        public void setCode(String code)
        {
            this.code = code;
        }

        public String getMessage()
        {
            return message;
        }

        public void setMessage(String message)
        {
            this.message = message;
        }

        public String getField()
        {
            return field;
        }

        public void setField(String field)
        {
            this.field = field;
        }
    }

    class TagSugestion
    {
        private String tag;

        private TagSugestion(String tag)
        {
            this.tag = tag;
        }

        public String getTag()
        {
            return tag;
        }

        public void setTag(String tag)
        {
            this.tag = tag;
        }
    }
}