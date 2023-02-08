package ru.job4j.early;

public class PasswordValidator {
    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }

        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }

        if (containsForbiddenWords(password)) {
            throw new IllegalArgumentException("Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
        }

        validatePasswordLetters(password);

        return password;
    }

    public static void validatePasswordLetters(String password) {
        boolean containsUppercaseLetter = false;
        boolean containsLowercaseLetter = false;
        boolean containsDigit = false;
        boolean containsSpecialSymbol = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                containsUppercaseLetter = true;
            }
            if (Character.isLowerCase(password.charAt(i))) {
                containsLowercaseLetter = true;
            }
            if (Character.isDigit(password.charAt(i))) {
                containsDigit = true;
            }
            if (!Character.isDigit(password.charAt(i)) && !Character.isLetter(password.charAt(i))) {
                containsSpecialSymbol = true;
            }

            if (containsUppercaseLetter && containsLowercaseLetter && containsDigit && containsSpecialSymbol) {
                return;
            }
        }

        if (!containsUppercaseLetter) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        if (!containsLowercaseLetter) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        if (!containsDigit) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        if (!containsSpecialSymbol) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }
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
