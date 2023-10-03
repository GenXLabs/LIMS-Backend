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

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUser(id);
        return ResponseEntity.ok(userDTO);
    }
    @GetMapping("/getEmail")
    public UserDTO getUser(@RequestParam String email){
        return userService.getUser(email);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        if (isPasswordUpdate(userDTO)) {
            if (!isNewPasswordConfirmed(userDTO)) {
                // Return a bad request response if the new password and confirmation don't match
                return ResponseEntity.badRequest().build();
            }

            // Handle password update separately
            userService.updatePassword(id, userDTO);
        } else {
            // Handle updates to other fields
            userService.updateUser(id, userDTO);
        }

        return ResponseEntity.noContent().build();
    }

    private boolean isPasswordUpdate(UserDTO userDTO) {
        // Check if the newPassword field is provided (indicating a password update)
        return userDTO.getNewPassword() != null && !userDTO.getNewPassword().isEmpty();
    }

    private boolean isNewPasswordConfirmed(UserDTO userDTO) {
        // Check if the new password and confirmation match
        return userDTO.getNewPassword() != null &&
                userDTO.getConfirmNewPassword() != null &&
                userDTO.getNewPassword().equals(userDTO.getConfirmNewPassword());
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}