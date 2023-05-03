package com.abscript.brightcodingspringv2.controllers;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/{id}")
    public UsersResponse getUser(@PathVariable String userId){
        return null;
    }
    @GetMapping
    public ArrayList<UsersResponse> getUsers(){
        ArrayList<UsersResponse> responses=new ArrayList<>();
        var usersDto=userService.getAllUsers();
        for(UserDto user:usersDto){
            UsersResponse userResponse=new UsersResponse();
            BeanUtils.copyProperties(user, userResponse);
            responses.add( userResponse);
        }
        return responses;
    }

    @PostMapping
    public UsersResponse createUser(@RequestBody UserRequest userRequest){
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);
        UserDto createdUser=userService.createUser(userDto);
        UsersResponse usersResponse=new UsersResponse();
        BeanUtils.copyProperties(createdUser, usersResponse);
        return usersResponse;
    }

    @PutMapping
    public String updateUser(){
        return "upsate user was called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete User was called";
    }
}
