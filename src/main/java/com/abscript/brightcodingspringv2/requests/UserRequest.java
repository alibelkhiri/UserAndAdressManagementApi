package com.abscript.brightcodingspringv2.requests;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
    @NotBlank(message = "first name must not be null")
    @Size(min=3,message = "this attribute must has more than 3 carachters!")
    private String firstName;
    @NotNull(message = "last name must not be null")
    @Size(min=3)
    private String lastName;
    @NotNull(message = "email name must not be null")
    @Email
    private String email;
    @NotNull
    @Size(min=8,max = 12)
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",message = "password doit avoir des Maj et min et numero")
    private String password;
    private List<AddressRequest> addresses;
    public List<AddressRequest> getAddresses() {
        return addresses;
    }
    public void setAddresses(List<AddressRequest> addresses) {
        this.addresses = addresses;
    }
    public String getFirstName() {
        return firstName;
    } 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
