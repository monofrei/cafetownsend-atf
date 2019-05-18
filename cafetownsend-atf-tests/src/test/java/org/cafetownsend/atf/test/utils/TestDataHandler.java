package org.cafetownsend.atf.test.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.cafetownsend.atf.models.User;
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

    public User getAdminUser() {
        Gson gson = new GsonBuilder().create();
        String usersDataFile = dataPath + File.separator + "users.json";
        try {
            User[] users = gson.fromJson(new FileReader(usersDataFile), User[].class);
            return Arrays.stream(users)
                    .filter(u -> u.getRole().toLowerCase().equals("admin"))
                    .findAny()
                    .orElseThrow(() -> new RuntimeException("No users with admin roles defined in file[" + usersDataFile + "]"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to read users data from [" + usersDataFile + "]", e);
        }
    }
}
