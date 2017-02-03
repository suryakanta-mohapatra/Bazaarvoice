package com.bazaarvoice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.io.File;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.print.Doc;

public class Main {
    DocumentSearchImpl implementation  =  new DocumentSearchImpl();
    List<Document> results = new ArrayList<>();
    //Loads all the json files
    public void storeDocument() throws FileNotFoundException, IOException{
        /******Store as document********/
        ArrayList<String> stringObjects = new ArrayList<>();
        File dir  = new File("json");
        if(dir.isDirectory()){
            for(File file:dir.listFiles()){
                String res="";
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                StringBuilder sb = new StringBuilder();

                while(line!=null){
                    sb.append(line);
                    line=br.readLine();
                }
                res = sb.toString();
                stringObjects.add(res);
                Document doc =  new Document();
                Type type = new TypeToken<Map<String, String>>(){}.getType();
                Map<String, Object> map = new Gson().fromJson(res, type);
               if(   (map.containsKey("id"))   &&    (Integer.valueOf((String)map.get("id"))>0) ){
                doc.setFields(map);
                implementation.storeDocument(doc);
                }
            }
        }
    }
    //Loads the schema
    public void loadSchema() throws FileNotFoundException, IOException{
        /******Set Schema********/
        Schema schema = new Schema();
        List<SchemaRow> schemaRows = new ArrayList<>();
        FileReader reader = new FileReader("src/com/bazaarvoice/schema.txt");
        BufferedReader br = new BufferedReader(reader);
        String currentLine = null;
        while((currentLine = br.readLine())!=null){
            String[] arr = currentLine.replaceAll("\\s+",":").split(":");
            SchemaRow row = new SchemaRow();
            row.setFieldName(arr[0]);
            row.setFieldType(arr[1]);
            if(arr.length>2)
                row.setOptions(Arrays.asList(arr[2].split(",")));
            schemaRows.add(row);
        }
        schema.setRows(schemaRows);
        implementation.setSchema(schema);

    }
    //Search result
    public int searchResults(String property, Object value) throws FileNotFoundException, IOException{

        if((!(property instanceof String)) || ((property.replaceAll("\\s+","")).equals(""))){
           System.out.println("invalid");
            return -1;
        }
        System.out.println("valid");
        results.clear();
        /******Loads all the json file******/
        storeDocument();
        /******Loads the schemat********/
        loadSchema();


        /******Printing Result********/
        results=implementation.search(property,value);
        String string =""; int counter=0;
        int resultCount = results.size();
        System.out.println("\n");
        System.out.println("====================================");
        System.out.println("RESULT COUNT :: "+resultCount);
        System.out.println("====================================");

        for(Document result:results){
            string+=++counter+"."+"{"+"\n";
            for(String key:result.getFields().keySet())
                string+=key+" : "+result.getFields().get(key)+"\n";
            string+="}"+"\n"+"------------------------------------"+"\n";
        }
        System.out.println((string!="")?string:"Sorry !!! No match found :-(");
        return resultCount;
    }

    public static void main(String[] args) throws  IOException {

        Main main = new Main();
        /*main.storeDocument();
        main.loadSchema();*/
        //System.out.println("***"+main.searchResults("text","sample")+"***");

    }
}
