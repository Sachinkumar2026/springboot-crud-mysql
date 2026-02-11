package com.example.CRUD.controller;

import com.example.CRUD.DTO.UserDTO;
import com.example.CRUD.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class userController {
    private final UserService userService;
    public userController(UserService userService){
        this.userService = userService;
    }

//    @PostMapping
//    public User createUser(@RequestBody User user){
//        return userService.createUser(user);
//    }
//    @PostMapping
//    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
//        return userService.createUser(userDTO);
//    }
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO savedUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping
    public Page<UserDTO> getAllUsers(@RequestParam(defaultValue = "0")int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "id") String sortBy,
                                     @RequestParam(defaultValue = "asc") String direction){
        return userService.getAllUsers(page, size,sortBy,direction);
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @Valid @RequestBody UserDTO userDTO){
        UserDTO updatedUser = userService.updateUser(id,userDTO);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
