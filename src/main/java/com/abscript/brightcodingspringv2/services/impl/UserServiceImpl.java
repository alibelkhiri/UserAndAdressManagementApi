package com.abscript.brightcodingspringv2.services.impl;

import java.util.ArrayList;

import org.apache.tomcat.jni.User;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.abscript.brightcodingspringv2.Entity.UserEntity;
import com.abscript.brightcodingspringv2.dto.UserDto;
import com.abscript.brightcodingspringv2.repository.UserRepository;
import com.abscript.brightcodingspringv2.services.UserService;
import com.abscript.brightcodingspringv2.shared.Utils;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import lombok.var;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Utils util;
    

    @Override
    public UserDto createUser(UserDto userDto) {
       UserEntity foundUser= userRepository.findByEmail(userDto.getEmail());
       if(foundUser!=null) throw new RuntimeException("User alrady exists!"); 
       UserEntity userEntity=new UserEntity();

        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setEncryptedPassword(userDto.getPassword());
        userEntity.setUserId(util.generateUserId(32));

        UserEntity createdUser=userRepository.save(userEntity);
        
        UserDto userDto2=new UserDto();
        BeanUtils.copyProperties(createdUser, userDto2);
        return userDto2;  
    }

 

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity=userRepository.findByEmail(email);
        if(userEntity==null){
            System.out.println("User not found");
        }
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }



    @Override
    public ArrayList<UserDto> getAllUsers() {
      ArrayList<UserDto> usersDto=new ArrayList<>();
      var users=userRepository.findAll();
      for(UserEntity user: users){
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(user, userDto);
        usersDto.add(userDto);
      }
      return usersDto;
    }
    
}
