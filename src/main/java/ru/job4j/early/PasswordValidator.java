package ru.job4j.early;

public class PasswordValidator {
    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }

        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }

        if (!containsUppercaseLetter(password)) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }

        if (!containsLowercaseLetter(password)) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }

        if (!containsDigit(password)) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }

        if (!containsSpecialSymbol(password)) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }

        if (containsForbiddenWords(password)) {
            throw new IllegalArgumentException("Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
        }

        return password;
    }

    public static boolean containsUppercaseLetter(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsLowercaseLetter(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsDigit(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsSpecialSymbol(String password) {
        String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
        for (int i = 0; i < password.length(); i++) {
            if (specialChars.contains(String.valueOf(password.charAt(i)))) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsForbiddenWords(String password) {
        String[] forbiddenWords = new String[] {"qwerty", "12345", "password", "admin", "user"};
        for (String forbiddenWord: forbiddenWords) {
            if (password.toLowerCase().contains(forbiddenWord)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        try {
            System.out.println(PasswordValidator.validate("Ln2$mrTY12"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
