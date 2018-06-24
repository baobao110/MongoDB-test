package com.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.regex.Pattern;

public class MongoUtil {

    public static void main(String[] args) {
        MongoClientURI uri=new MongoClientURI("mongodb://localhost:27017");
        MongoClient client=new MongoClient(uri);
        MongoDatabase  db=client.getDatabase("test");
        Document doc=new Document();
        doc.put("name","11");
         MongoCursor<Document> list=db.getCollection("first").find(doc).iterator();
         while(list.hasNext()){
             System.out.println(list.next().getString("name"));
         }
         //模糊查询
        Pattern pattern=Pattern.compile("^.*" +1 + ".*$", Pattern.CASE_INSENSITIVE);
         Document docu=new Document();
         docu.put("_id",-1);
         //分页技术的使用
        MongoCursor<Document> x=db.getCollection("first").find(Filters.regex("name",pattern)).skip(0).limit(10).sort(docu).iterator();
        while(x.hasNext()){
            System.out.println(x.next().getString("name"));
        }
    }
}