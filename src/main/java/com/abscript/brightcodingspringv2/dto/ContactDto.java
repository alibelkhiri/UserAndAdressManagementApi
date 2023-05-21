package com.abscript.brightcodingspringv2.dto;

import java.io.Serializable;

public class ContactDto implements Serializable{
    private Long id;
    private String contactId;
    private String mobile;
    private String skype;
    private UserDto user;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContactId() {
        return contactId;
    }
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getSkype() {
        return skype;
    }
    public void setSkype(String skype) {
        this.skype = skype;
    }
    public UserDto getUser() {
        return user;
    }
    public void setUser(UserDto user) {
        this.user = user;
    }
    
}
