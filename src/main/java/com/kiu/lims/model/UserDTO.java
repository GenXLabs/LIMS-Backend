package com.kiu.lims.model;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;


    // Constructors, getters, and setters
}