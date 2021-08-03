package pl.marekjava;

import java.util.*;

public class PasswordManager {
    private List<String> passwords = new ArrayList<>();
    private Map<String, Boolean> usedPasswords = new HashMap<>();
    private String currentPassword;
    private List<Character> corectGuesses = new ArrayList<>();

    public PasswordManager() {
        passwords.add("Apetyt rośnie w miarę jedzenia");
        passwords.add("Co dwie głowy, to nie jedna");
        passwords.add("Cwiczenie czyni mistrza");
        passwords.add("Darowanemu koniowi w zęby się nie zagląda");
        passwords.add("Diabeł tkwi w szczegółach");
        passwords.add("Elektryka prąd nie tyka");

        for (int i = 0; i < passwords.size(); i++) {
            usedPasswords.put(passwords.get(i), false);
        }
    }

    public String getRandomPassword() {
        Random r = new Random();
        int randomItem;
        try {
            randomItem = r.nextInt(passwords.size());
        } catch (Exception e) {
            throw new IllegalStateException("Lista haseł nie może być pusta");
        }
        String randomPassword = passwords.get(randomItem);

        int counter = 0;
        while (usedPasswords.get(randomPassword)) {
            randomItem = r.nextInt(passwords.size());
            randomPassword = passwords.get(randomItem);
            counter++;
            if (counter > passwords.size()) throw new IllegalStateException("Brak unikalnego hasła");
        }
        usedPasswords.put(randomPassword, true);
        currentPassword = randomPassword;
        corectGuesses.clear();
        return randomPassword;
    }

    public void setPasswords(List<String> passwords) {
        this.passwords = passwords;
        usedPasswords.clear();
        for (int i = 0; i < passwords.size(); i++) {
            usedPasswords.put(passwords.get(i), false);
        }
    }

    public int getSizeOfPasswords() {
        return passwords.size();
    }

    public int guessLetter(char letter) {
        int counter = 0;
        char[] lettersOnPassword = currentPassword.toLowerCase().toCharArray();
        if (letter <= 90) letter += 32;
        for (char c : lettersOnPassword) {
            if (c == letter) {
                counter++;
            }
        }
        if (currentPassword.toLowerCase().contains(String.valueOf(letter))) {
            corectGuesses.add(letter);
        }
        return counter;
    }

    protected void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
        corectGuesses.clear();
    }

    public boolean guessPassword(String password) {
        return password.equalsIgnoreCase(currentPassword);
    }

    public String getObscuredPassword() {
        StringBuilder obscuredPassword = new StringBuilder(currentPassword);
        for (int i = 0; i < currentPassword.length(); i++) {
            char currentChar = currentPassword.toLowerCase().charAt(i);

            if (currentChar >= 65) {
                obscuredPassword.replace(i, i + 1, "-");
            }
            for (Character c : corectGuesses) {
                if (currentChar == c) {
                    obscuredPassword.replace(i, i + 1, String.valueOf(c));
                }
            }
        }
        return obscuredPassword.toString().toUpperCase();
    }

    public void setCorrectGuesses(List<Character> correctGuesses) {
        this.corectGuesses = correctGuesses;
    }
}
