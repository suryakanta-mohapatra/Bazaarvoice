package com.bazaarvoice;

import java.util.Map;

public class Document {

    /*
    * for example
    *
    *  {
    *     "description" : "another day in my life!",
    *     "text" : "sample text",
    *     "word" : "purple",
    *     "name" : "test document",
    *     "id" : 101
    *  }
    *
    *  or
    *
    *  {
    *     "description" : "another day in my life!",
    *     "text" : "sample text",
    *     "name" : "Test1",
    *     "id" : 102
    *  }
    *
    *  any field can be missing except id.
    *
    * */
    private Map<String,Object> fields;

    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(final Map<String, Object> fields) {
        this.fields = fields;
    }
}
