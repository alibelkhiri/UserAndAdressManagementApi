package com.abscript.brightcodingspringv2.services;



import java.util.ArrayList;

import com.abscript.brightcodingspringv2.dto.UserDto;

public interface UserService{
   UserDto createUser(UserDto userDto);
   UserDto getUser(String email);
   ArrayList<UserDto> getAllUsers();
}
