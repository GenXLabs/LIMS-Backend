package com.kiu.lims.model;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private String newPassword;
    private String confirmNewPassword;


    // Constructors, getters, and setters
}
