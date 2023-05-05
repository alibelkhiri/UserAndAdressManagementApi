package com.abscript.brightcodingspringv2.controllers;

import java.util.ArrayList;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abscript.brightcodingspringv2.dto.UserDto;
import com.abscript.brightcodingspringv2.requests.UserRequest;
import com.abscript.brightcodingspringv2.responses.UsersResponse;
import com.abscript.brightcodingspringv2.services.UserService;

import lombok.var;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UsersResponse> getUser(@PathVariable String id){
        UserDto userDto=userService.getUserByUserId(id);
        UsersResponse usersResponse=new UsersResponse();
        BeanUtils.copyProperties(userDto, usersResponse);
        return new ResponseEntity<UsersResponse>(usersResponse,HttpStatus.CREATED);
    }
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<UsersResponse>> getUsers(){
        ArrayList<UsersResponse> responses=new ArrayList<>();
       
        var usersDto=userService.getAllUsers();
        for(UserDto user:usersDto){
            UsersResponse userResponse=new UsersResponse();
            BeanUtils.copyProperties(user, userResponse);
            responses.add( userResponse);
        }
        
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UsersResponse> createUser(@RequestBody UserRequest userRequest){
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);
        UserDto createdUser=userService.createUser(userDto);
        UsersResponse usersResponse=new UsersResponse();
        BeanUtils.copyProperties(createdUser, usersResponse);
        return new ResponseEntity<UsersResponse>(usersResponse,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UsersResponse> updateUser(@PathVariable String id,@RequestBody UserRequest userRequest){
        UserDto recivedUserDto=new UserDto();
        BeanUtils.copyProperties(userRequest, recivedUserDto);
        UsersResponse usersResponse=new UsersResponse();
        UserDto updatedUser=userService.updateUser(id, recivedUserDto);
        BeanUtils.copyProperties(updatedUser, usersResponse);
        return new ResponseEntity<UsersResponse>(usersResponse,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id){
        
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
