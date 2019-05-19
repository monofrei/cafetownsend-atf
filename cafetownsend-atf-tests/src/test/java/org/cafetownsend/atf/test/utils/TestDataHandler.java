package org.cafetownsend.atf.test.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.cafetownsend.atf.models.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

@Component
public class TestDataHandler {

    @Value("${data.files.path}")
    private String dataPath;

    public Employee getAdminUser() {
        Gson gson = new GsonBuilder().create();
        String usersDataFile = dataPath + File.separator + "users.json";
        try {
            Employee[] employees = gson.fromJson(new FileReader(usersDataFile), Employee[].class);
            return Arrays.stream(employees)
                    .filter(u -> u.getRole().toLowerCase().equals("admin"))
                    .findAny()
                    .orElseThrow(() -> new RuntimeException("No employees with admin roles defined in file[" + usersDataFile + "]"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to read users data from [" + usersDataFile + "]", e);
        }
    }
}
