package ru.job4j.early;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }

        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }

        if (!passwordMatchRegExp(password, "[A-Z]", false)) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }

        if (!passwordMatchRegExp(password, "[a-z]", false)) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }

        if (!passwordMatchRegExp(password, "[0-9]", false)) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }

        if (!passwordMatchRegExp(password, "[!@#$%^&*()_]", false)) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }

        if (passwordMatchRegExp(password, "(qwerty|admin|12345|password|user)", true)) {
            throw new IllegalArgumentException("Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
        }

        return password;
    }

    public static boolean passwordMatchRegExp(String password, String regExp, boolean registerSensitive) {
        Pattern pattern = Pattern.compile(regExp);
        if (registerSensitive) {
            pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
        }
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public static void main(String[] args) {
        try {
            System.out.println(PasswordValidator.validate("Ln2$mrTY12"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
