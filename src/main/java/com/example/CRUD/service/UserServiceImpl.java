package com.example.CRUD.service;

import com.example.CRUD.DTO.UserDTO;
import com.example.CRUD.entity.User;
import com.example.CRUD.exception.ResourceNotFoundException;
import com.example.CRUD.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }

    private User mapToEntity(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        User user = mapToEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saved = userRepository.save(user);
        user.setRole("USER");
        return mapToDTO(saved);
    }

    @Override
    public Page<UserDTO> getAllUsers(int page, int size,String sortBy,String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

       Page<User> userPage = userRepository.findAll(
               PageRequest.of(page,size,sort)
       );
        return userPage.map(this::mapToDTO);
//        return userRepository.findAll()
//                .stream()
//                .map(this::mapToDTO)
//                .toList();
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return mapToDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto){
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not Found"));
        existingUser.setName(dto.getName());
        existingUser.setEmail(dto.getEmail());
        User updatedUser = userRepository.save(existingUser);

        return mapToDTO(updatedUser);
    }

    public List<UserDTO> searchUserByName(String name){
        return userRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
