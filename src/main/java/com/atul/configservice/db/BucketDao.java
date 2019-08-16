package com.atul.configservice.db;

import com.atul.configservice.core.Bucket;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class BucketDao extends AbstractDAO<Bucket> {
    public BucketDao(SessionFactory factory) {
        super(factory);
    }

    public Bucket find(String bucketName) {
        return get(bucketName);
    }

    public Bucket create(Bucket bucket) {
        return persist(bucket);
    }

    public void delete(String bucketName){
        currentSession().delete(find(bucketName));
    }
}
