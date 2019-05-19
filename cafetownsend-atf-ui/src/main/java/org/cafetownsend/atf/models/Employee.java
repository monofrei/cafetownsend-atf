package org.cafetownsend.atf.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employee {

    private String firstName;
    private String lastName;
    private LocalDate startDate;
    private String email;
    private String role;

    private String username;
    private String password;

    public void setStartDate(String stringValue) {
        this.startDate = LocalDate.parse(stringValue);
    }
}
