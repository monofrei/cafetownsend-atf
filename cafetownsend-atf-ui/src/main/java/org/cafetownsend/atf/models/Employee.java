package org.cafetownsend.atf.models;

import lombok.*;

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
    private String startDate;

    @NonNull
    private String email;

    @EqualsAndHashCode.Exclude
    private String role;

    @EqualsAndHashCode.Exclude
    private String username;

    @EqualsAndHashCode.Exclude
    private String password;
}
