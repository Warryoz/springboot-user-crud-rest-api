package com.springboot.users.service;

import com.springboot.users.dto.UserDto;

import java.util.List;

public interface UserService {

   List<UserDto> getAllUsers();

   UserDto getUserById(Long id);

   UserDto createUser(UserDto newUser);

   UserDto updateUser(UserDto userDto, Long id);

   String deleteUser(Long id);

}
