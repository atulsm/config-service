package com.atul.configservice.resources;

import com.atul.configservice.core.Bucket;
import com.atul.configservice.api.BucketService;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bucket/{bucketName}")
public class BucketResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork(transactional = false)
    public Bucket getBucket(@PathParam("bucketName") String bucketName){
        return BucketService.INSTANCE.getBucket(bucketName);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Bucket createBucket(@PathParam("bucketName") String bucketName, Bucket incomingBucket){
    	if(incomingBucket==null) {
    		System.out.println("Receieved empty bucket");
    		return null;
    	}
        return BucketService.INSTANCE.createBucket(bucketName, incomingBucket.getValue());
    }

}
