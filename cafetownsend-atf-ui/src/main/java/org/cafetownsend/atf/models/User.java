package org.cafetownsend.atf.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {

    private String firstName;
    private String lastName;
    private LocalDate startDate;
    private String mail;
    private String role;

    private String username;
    private String password;
}
