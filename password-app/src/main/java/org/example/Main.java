package org.example;

import org.example.PasswordGenerator;

public class Main {
    public static void main(String[] args) {
        String password = PasswordGenerator.generatePassword(12);
        System.out.println("Згенерований пароль: " + password);
    }
}
