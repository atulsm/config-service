package com.atul.configservice.api;

import junit.framework.TestCase;

public class TestBucketService extends TestCase {

    public void testBucketCreate(){
        BucketService.INSTANCE.createBucket("es-ip", "192.168.1.1");
        Bucket bucket = BucketService.INSTANCE.getBucket("es-ip");
        assertEquals("192.168.1.1",bucket.getValue());
    }
}
