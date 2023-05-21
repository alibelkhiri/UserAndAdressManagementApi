package com.abscript.brightcodingspringv2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;


@Entity(name = "contacts")
public class ContactEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    @Column(length = 30)
    private String contactId;
    @NotBlank
    private String mobile;
    private String skype;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }
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

    
}
