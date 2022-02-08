package com.springboot.users.controller;

import com.springboot.users.dto.UserDto;
import com.springboot.users.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDto> getUsersRestApi(){
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserByIdRestApi(@PathVariable(name = "id") Long id ){
        return service.getUserById(id);
    }

    @PostMapping
    public UserDto createNewUserRestApi(@RequestBody UserDto newUser){ return service.createUser(newUser); }

    @PutMapping("/{id}")
    public UserDto updateUserRestApi(@RequestBody UserDto user, @PathVariable(name = "id") Long id ){
        return service.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    public String getDeleteByIdRestApi(@PathVariable(name = "id") Long id ){ return service.deleteUser(id); }
}
