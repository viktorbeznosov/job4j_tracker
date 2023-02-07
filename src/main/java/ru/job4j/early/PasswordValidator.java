package ru.job4j.early;

public class PasswordValidator {
    private enum PasswordError {
        PASSWORD_NOT_CONTAINS_UPPERCASE_LETTER,
        PASSWORD_NOT_CONTAINS_LOWERCASE_LETTER,
        PASSWORD_NOT_CONTAINS_DIGIT,
        PASSWORD_NOT_CONTAINS_SPECIAL_SYMBOL,
        PASSWORD_IS_CORRECT
    }

    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }

        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }

        if (validatePasswordLetters(password).equals(PasswordError.PASSWORD_NOT_CONTAINS_UPPERCASE_LETTER)) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }

        if (validatePasswordLetters(password).equals(PasswordError.PASSWORD_NOT_CONTAINS_LOWERCASE_LETTER)) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }

        if (validatePasswordLetters(password).equals(PasswordError.PASSWORD_NOT_CONTAINS_DIGIT)) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }

        if (validatePasswordLetters(password).equals(PasswordError.PASSWORD_NOT_CONTAINS_SPECIAL_SYMBOL)) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }

        if (containsForbiddenWords(password)) {
            throw new IllegalArgumentException("Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
        }

        return password;
    }

    public static PasswordError validatePasswordLetters(String password) {
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
        }

        if (!containsUppercaseLetter) {
            return PasswordError.PASSWORD_NOT_CONTAINS_UPPERCASE_LETTER;
        }
        if (!containsLowercaseLetter) {
            return PasswordError.PASSWORD_NOT_CONTAINS_LOWERCASE_LETTER;
        }
        if (!containsDigit) {
            return PasswordError.PASSWORD_NOT_CONTAINS_DIGIT;
        }
        if (!containsSpecialSymbol) {
            return PasswordError.PASSWORD_NOT_CONTAINS_SPECIAL_SYMBOL;
        }

        return PasswordError.PASSWORD_IS_CORRECT;
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
