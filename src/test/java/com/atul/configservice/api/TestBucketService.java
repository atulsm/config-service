package com.atul.configservice.api;

import com.atul.configservice.core.Bucket;
import junit.framework.TestCase;
import org.junit.Ignore;

@Ignore
//TODO Fix the issue with testing
public class TestBucketService extends TestCase {

    public void testBucketCreate(){
        BucketService.INSTANCE.createBucket("es-ip", "192.168.1.1");
        Bucket bucket = BucketService.INSTANCE.getBucket("es-ip");
        assertEquals("192.168.1.1",bucket.getValue());
    }
}
