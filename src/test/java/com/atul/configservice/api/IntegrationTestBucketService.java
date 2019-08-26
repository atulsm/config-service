package com.atul.configservice.api;


import com.atul.configservice.core.Bucket;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.jackson.Jackson;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class IntegrationTestBucketService {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    public void testBucketResource(int attempts, boolean showOutput) {
        try {
            Client client = Client.create();
            for(int i=0;i<attempts;i++) {
                WebResource webResource = client.resource("http://localhost:8080/bucket/es-ip");
                ClientResponse response = webResource.accept("application/json")
                        .get(ClientResponse.class);
                if (response.getStatus() > 205) {
                    throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
                }

                String output = response.getEntity(String.class);
                if(showOutput) {
                    Bucket bucket = MAPPER.readValue(output, Bucket.class);
                    System.out.println(String.format("Output from Server .... %s", bucket));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBucketResourceSingleCall() {
        testBucketResource(1,true);
    }

    @Test
    public void loadTestBucketResource() {
        long start = System.currentTimeMillis();
        int attempts = 10000;
        testBucketResource(attempts,false);
        long end = System.currentTimeMillis();

        System.out.println(String.format("Took %d ms to process %d calls at QPS: %d",end-start,attempts, (attempts*1000)/(end-start)));
    }
}
