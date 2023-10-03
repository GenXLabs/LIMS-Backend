package com.kiu.lims.service;
import com.kiu.lims.model.UserDTO;

import com.kiu.lims.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;




public interface UserService {
    void addUser(UserDTO userDTO);

    List<UserDTO> getUsers();

    UserDTO getUser(Long userId);
    UserDTO getUser(String email);

    void updateUser(Long userId, UserDTO userDTO);

    void deleteUser(Long userId);
    void updatePassword(Long userId, UserDTO userDTO);
}