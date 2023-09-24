package com.kiu.lims.service;
import com.kiu.lims.model.UserDTO;

import com.kiu.lims.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;




public interface UserService {
    void addUser(UserDTO userDTO);

    List<UserDTO> getUsers();

    UserDTO getUser(Integer id);
    UserDTO getUser(String email);

    void updateUser(Integer id, UserDTO userDTO);

    void deleteUser(Integer id);
}