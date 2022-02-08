package com.springboot.users.service.impl;

import com.springboot.users.dto.UserDto;
import com.springboot.users.entity.User;
import com.springboot.users.repository.UserRepository;
import com.springboot.users.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    // Injection de la clase repositorio para usar con la db
    private final UserRepository repository;

    // Inicializacion de variables
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        //Obtener todos los usuarios
        List<User> users = repository.findAll();

        //Mapear entidad usuario a dto
        List<UserDto> usersList = users.stream()
                .map(this::mapToDto).collect(Collectors.toList());

        // retornar lista de usuarios
        return usersList;
    }

    @Override
    public UserDto getUserById(Long id) {
        //Traer usuario por id
         User user = repository.findById(id).get();

         // Convertir entidad usuario a dto
        UserDto userDto = mapToDto(user);

        //retornar usuario
        return userDto;
    }

    @Override
    public UserDto createUser(UserDto newUser) {
        //Mapeo de dto a entidad
        User user = mapToEntity(newUser);

        // Guardar en la base de datos el nuevo usuario
        User nUser = repository.save(user);

        // Retorno del nuevo usuario
        return mapToDto(nUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        // Consultar usuario por id
        User user = repository.findById(id).get();

        // Actualizando nuevos datos del usuario
        user.setId(id);
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        // Actualizar usuario
        User updatedUser = repository.save(user);

        // Retornar usuarioDto actualizado
        return mapToDto(updatedUser);
    }

    @Override
    public String deleteUser(Long id) {
        // Consultar usuario por id
        User user = repository.findById(id).get();

        repository.delete(user);

        return "User deleted successfully";
    }

    private UserDto mapToDto(User user){
        // Mapeo de entidad usuario a dto
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastname(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }

    private User mapToEntity(UserDto userDto){
        // Mapeo de entidad usuario a dto
        User user = new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getLastname(),
                userDto.getEmail(),
                userDto.getPassword());
        return user;
    }
}
