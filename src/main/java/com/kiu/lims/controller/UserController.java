package com.kiu.lims.controller;
import com.kiu.lims.model.UserDTO;

import com.kiu.lims.entity.User;
import com.kiu.lims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lims/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);

        return "successfully add user";
    }

    @GetMapping()
    public List<UserDTO> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/get")
    public UserDTO getUser(@RequestParam Long id){
        return userService.getUser(id);
    }

    @GetMapping("/getEmail")
    public UserDTO getUser(@RequestParam String email){
        return userService.getUser(email);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        userService.updateUser(id, userDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}