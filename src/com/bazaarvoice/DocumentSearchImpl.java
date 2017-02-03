package com.bazaarvoice;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//implement this interface. you could choose to store the documents in memory or in a file.

public class DocumentSearchImpl implements DocumentSearch {

    private List<Document> documentList;
    private List<SchemaRow> rows;
    public DocumentSearchImpl(){
        documentList = new ArrayList<>();
        rows = new ArrayList<>();
    }


    @Override public void setSchema(final Schema schema) {
        //implement to set schema
        rows = schema.getRows();
    }

    @Override public void storeDocument(final Document document) {
        //implement to store documents
        documentList.add(document);
    }

    @Override public List<Document> search(final String field, final Object value) {

        List<Document> documentListTemp= new ArrayList<>();
        Map<String, Object> fieldTemp;
        //Always look for documentList;
        for(Document document:documentList){
            fieldTemp    =  document.getFields();
            if(fieldTemp.containsKey(field)){
                Object val = fieldTemp.get(field);
                //check which row matches
                for(SchemaRow schemaRow:rows){
                    if((schemaRow.getFieldName().equals(field)) /*&&  (val.getClass().getName().contains(schemaRow.getFieldType()))*/){
                        List<String> options = schemaRow.getOptions();
                        //iterate through option list

                        if((options == null) && (val.equals(value.toString()))){documentListTemp.add(document);}
                        else if(options != null){
                            boolean isOptionMatching = true;
                            for(String option:options){
                                switch (option){

                                    case "lowercase":
                                        if(options.contains("fulltext"))
                                            isOptionMatching &=  val.toString().toLowerCase().contains(value.toString());
                                        else
                                            isOptionMatching &=  val.toString().toLowerCase().equals(value.toString());
                                        break;

                                    case "fulltext":
                                        if(options.contains("lowercase"))
                                            isOptionMatching &=  val.toString().toLowerCase().contains(value.toString())  ;
                                        else
                                            isOptionMatching &= val.toString().contains(value.toString());

                                        break;

                                }
                            }
                            //check if document to be included
                            if(isOptionMatching) {
                                documentListTemp.add(document);
                            }
                        }
                    }
                }

            }
        }
        return documentListTemp;
    }
}
