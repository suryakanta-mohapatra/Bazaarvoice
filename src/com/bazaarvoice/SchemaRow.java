package com.bazaarvoice;

import java.util.List;


public class SchemaRow {

    //an attribute of a document. eg, name
    private String fieldName;

    //type of field. allowed values are string, int.
    //string - means one or many strings. eg, "word", "
    //int - a number.
    private String fieldType;

    //search options, allowed values are fulltext and/or lowercase or just no options/empty list. only valid on type string.

    // fulltext - search by any word in the string, eg "what a beautiful day" is the input, search by word "what" or "day" should return this document.
    // lowercase - search by lowercase version of the string . eg if there is a value "RECORD", searching for "record" should match this document.

    private List<String> options;


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(final String fieldType) {
        this.fieldType = fieldType;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(final List<String> options) {
        this.options = options;
    }
}
