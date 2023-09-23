package com.kiu.lims.service;

import com.kiu.lims.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;




public interface UserService {
    void addUser(User user);

    List<User> getUsers();

    User getUser(Integer id);
    User getUser(String email);

    void updateUser(Integer id, User user);

    void deleteUser(Integer id);
}
