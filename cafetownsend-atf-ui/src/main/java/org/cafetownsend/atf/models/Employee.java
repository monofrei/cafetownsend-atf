package org.cafetownsend.atf.models;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class Employee {

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private LocalDate startDate;

    @NonNull
    private String email;

    @EqualsAndHashCode.Exclude
    private String role;

    @EqualsAndHashCode.Exclude
    private String username;

    @EqualsAndHashCode.Exclude
    private String password;

    public void setStartDate(String stringValue) {
        this.startDate = LocalDate.parse(stringValue);
    }
}
