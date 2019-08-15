package com.atul.configservice.api;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

public class BucketService {

    public static BucketService INSTANCE = new BucketService();
    private BucketService(){}

    //TODO Implement database backend
    //TODO Implement juice dependency injection

    Map<String,Bucket> buckets = new HashMap<>();

    public Bucket createBucket(String name, String value){
        buckets.put(name, new Bucket(name,value));
        return buckets.get(name);
    }

    public Bucket getBucket(String name){
        return buckets.get(name);
    }

    public boolean deleteBucket(String name){
        return buckets.remove(name)!=null ?true :false;
    }

    public int count(){
        return buckets.size();
    }

}
