package com.abscript.brightcodingspringv2.services;



import java.util.ArrayList;

import com.abscript.brightcodingspringv2.dto.UserDto;

public interface UserService{
   UserDto createUser(UserDto userDto);
   UserDto getUser(String email);
   ArrayList<UserDto> getAllUsers(int page,int limit,String search, int status);
   UserDto getUserByUserId(String userId);
   UserDto updateUser(String id, UserDto user);
   void deleteUser(String userId);
}
