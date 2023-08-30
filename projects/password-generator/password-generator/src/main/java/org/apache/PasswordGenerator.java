package org.apache;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordGenerator {
    public static void main(String[] args) {
        int length = 12; // Password length
        boolean useLetters = true; // Include letters
        boolean useNumbers = true; // Include numbers
        boolean useSpecialChars = true; // Include special characters

        String generatedPassword = RandomStringUtils.random(length);
        System.out.println("Generated Password: " + generatedPassword);

        // Define custom character set
        String customCharacterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$";

        // Generate a random password using the custom character set
        String randomPassword = RandomStringUtils.random(length, customCharacterSet);
        System.out.println("Random Password: " + randomPassword);
    }
    }
