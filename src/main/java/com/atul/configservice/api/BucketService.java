package com.atul.configservice.api;

import com.atul.configservice.core.Bucket;
import com.atul.configservice.db.BucketDao;

//TODO Implement juice dependency injection
public class BucketService {
    public static BucketService INSTANCE = new BucketService();
    private BucketService(){}
    private BucketDao dao;

    public void init(BucketDao dao){
        this.dao=dao;
    }

    public Bucket createBucket(String name, String value){
        return dao.create(new Bucket(name,value));
    }

    public Bucket getBucket(String name){
        return dao.find(name);
    }

    public void deleteBucket(String name){
        dao.delete(name);
    }

    public int count(){
        return 1; //TODO implement  a namedquery
    }

}
