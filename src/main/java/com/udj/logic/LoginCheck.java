package com.udj.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoginCheck {

    private static final String CREDENTIALS_FILE_PATH = "/data/LoginCredentials.txt";

    public static User validateLogin(String username, String password) {
        try (InputStream is = LoginCheck.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            
            if (is == null) {
                System.err.println("Error: Resource file not found in classpath: " + CREDENTIALS_FILE_PATH);
                return null;
            }
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String fileUsername = parts[0].trim();
                    String filePassword = parts[1].trim();
                    String role = parts[2].trim();

                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        switch (role) {
                            case "STUDENT" -> {
                                return new Student(username, password);
                            }
                            case "TEACHER" -> {
                                return new Teacher(username, password);
                            }
                            case "ADMIN" -> {
                                return new Admin(username, password);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; 
    }
}