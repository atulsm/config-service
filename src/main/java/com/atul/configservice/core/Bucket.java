package com.atul.configservice.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@lombok.ToString
@Entity
@Table(name = "bucket")
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
//TODO add version and dates
public class Bucket {

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value", nullable = false)
    private String value;

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
