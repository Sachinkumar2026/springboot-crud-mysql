package com.example.CRUD.service;

import com.example.CRUD.DTO.UserDTO;
import com.example.CRUD.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    Page<UserDTO> getAllUsers(int page, int size,String sortBy,String direction);
    UserDTO getUserById(Long Id);
    void deleteUser(Long Id);
    UserDTO updateUser(Long id, UserDTO dto);
    List<UserDTO> searchUserByName(String name);

    //    List<UserDTO> getAllUsers();
    //    User createUser(User user);
    //    List<User> getAllUsers();
}
