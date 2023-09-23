package com.kiu.lims.service.serviceImplementation;

import com.kiu.lims.model.User;
import com.kiu.lims.repository.UserRepository;
import com.kiu.lims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void addUser(User user) {

        userRepository.save(user);

    }

    @Override
    public List<User> getUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUser(Integer id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid user id"+id));
        return user;
    }
    @Override
    public User getUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for email: " + email);
        }
        return user;
    }

    @Override
    public void updateUser(Integer id, User user) {
        userRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid user id"+id));
        user.setId(id);

        userRepository.save(user);

    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid user id"+id));

        userRepository.delete(user);
    }
}
