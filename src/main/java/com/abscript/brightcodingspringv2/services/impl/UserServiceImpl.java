package com.abscript.brightcodingspringv2.services.impl;

import java.util.ArrayList;


import java.util.List;

import org.apache.tomcat.jni.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abscript.brightcodingspringv2.dto.AddressDto;
import com.abscript.brightcodingspringv2.dto.UserDto;
import com.abscript.brightcodingspringv2.entity.UserEntity;
import com.abscript.brightcodingspringv2.exceptions.UserException;
import com.abscript.brightcodingspringv2.repository.UserRepository;
import com.abscript.brightcodingspringv2.services.UserService;
import com.abscript.brightcodingspringv2.shared.Utils;

import ch.qos.logback.classic.pattern.Util;
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
       
       
      for(int i=0;i<userDto.getAddresses().size();i++){
        AddressDto addressDto=userDto.getAddresses().get(i);
        addressDto.setUser(userDto);
        addressDto.setAddressId(util.generateStringId(30));
        userDto.getAddresses().set(i, addressDto);
      }

      userDto.getContact().setContactId(util.generateStringId(30));
      userDto.getContact().setUser(userDto);
      ModelMapper modelMapper=new ModelMapper();
      UserEntity userEntity=modelMapper.map(userDto, UserEntity.class);
       
        userEntity.setEncryptedPassword(userDto.getPassword());
        userEntity.setUserId(util.generateStringId(32));

        UserEntity createdUser=userRepository.save(userEntity);
        UserDto userDto2=modelMapper.map(createdUser, UserDto.class);
        return userDto2;  
    }

 

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity=userRepository.findByEmail(email);
        if(userEntity==null){
            throw new UserException(email+" this email not found");
        }
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }



    @Override
    public ArrayList<UserDto> getAllUsers(int page, int limit,String search, int status) {
      if(page>0)page-=1;
      ArrayList<UserDto> usersDto=new ArrayList<>();
      Pageable pageableRequest=PageRequest.of(page, limit);
      Page<UserEntity> userPage;
      if(search.isEmpty()){
         userPage=userRepository.findAllUsers(pageableRequest);
      }
      else{
        userPage=userRepository.findAllUsersByCreteria(pageableRequest,search,status);
      }
      
      var users=userPage.getContent();
      for(UserEntity user: users){
        
        ModelMapper modelMapper= new ModelMapper();
        UserDto  userDto=modelMapper.map(user,UserDto.class);
       
        usersDto.add(userDto);
      }
      return usersDto;
    }



    @Override
    public UserDto getUserByUserId(String userId) {
      UserEntity userEntity=userRepository.findByUserId(userId);
      if(userEntity==null){
        throw new UserException(userId+" this userId not found");
    }
    UserDto userDto=new UserDto();
    BeanUtils.copyProperties(userEntity, userDto);
      return userDto;
    }



    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
      UserEntity userEntity=userRepository.findByUserId(userId);
      if(userEntity==null){
        throw new UsernameNotFoundException(userId+" this userId not found");
      }
      userEntity.setFirstName(userDto.getFirstName());
      userEntity.setLastName(userDto.getLastName());
      UserEntity updatedUser=userRepository.save(userEntity);
      UserDto updatedUserDto=new UserDto();
      BeanUtils.copyProperties(updatedUser, updatedUserDto);
      return updatedUserDto;
    }



    @Override
    public void deleteUser(String userId) {
      UserEntity userEntity= userRepository.findByUserId(userId);
      if(userEntity==null){ throw new UserException("UserNotFound");}
      userRepository.delete(userEntity);
      
    }
    
}
