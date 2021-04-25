package com.sergei.batch.processing.domain.dao;

import javax.persistence.Entity;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
@Entity
public class Address extends BaseEntity {
    private static final long serialVersionUID = 2923593175765625196L;
    
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
