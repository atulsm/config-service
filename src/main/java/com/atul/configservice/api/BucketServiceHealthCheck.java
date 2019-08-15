package com.atul.configservice.api;

import com.codahale.metrics.health.HealthCheck;

public class BucketServiceHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        int count = BucketService.INSTANCE.count();
        if(count>0) {
            return Result.healthy(String.format("Bucket count : %s", count));
        }else{
            return Result.unhealthy(String.format("Bucket count : %s", count));
        }
    }
}
