package com.abscript.brightcodingspringv2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.servlet.FlashMap;

@Entity(name = "addresses")
public class AddressEntity implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 30,nullable = false)
    private String addressId;
    private String city;
    private String country;
    private String street;
    private String postalCode;
    private String type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        id = id;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }
    
}
