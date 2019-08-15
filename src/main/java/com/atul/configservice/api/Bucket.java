package com.atul.configservice.api;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.ToString
public class Bucket {

    private String name;
    private String value;

    //TODO add version and dates

    public Bucket() {
        //For jackson
    }

    public Bucket(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getValue() {
        return value;
    }

}
