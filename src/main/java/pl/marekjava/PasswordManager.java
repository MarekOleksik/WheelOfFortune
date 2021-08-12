package pl.marekjava;


import javax.json.*;
import java.io.InputStream;
import java.util.*;

public class PasswordManager {
    private List<String> passwords = new ArrayList<>();
    private Map<String, Boolean> usedPasswords = new HashMap<>();
    private String currentPassword;
    private List<Character> correctGuesses = new ArrayList<>();

    public PasswordManager() {

        App obj = new App();
        InputStream inputStream = obj.getClass()
                .getClassLoader()
                .getResourceAsStream("passwords.json");
        JsonReader jsonReader = Json.createReader(inputStream);
        JsonObject passJson = jsonReader.readObject();
        JsonArray passwordsJson = passJson.getJsonArray("passwords");
        for (JsonValue j : passwordsJson) {
            String password = j.toString().replace("\"", "");
            passwords.add(password);
        }

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
        correctGuesses.clear();
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
            correctGuesses.add(letter);
        }
        return counter;
    }

    protected void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
        correctGuesses.clear();
    }

    public boolean guessPassword(String password) {
        return password.equalsIgnoreCase(currentPassword);
    }

    public String getObscuredPassword() {
        StringBuilder obscuredPassword = new StringBuilder(currentPassword);
        for (int i = 0; i < currentPassword.length(); i++) {
            char currentChar = currentPassword.toLowerCase().charAt(i);

            if (Character.isLetter(currentChar)) {
                obscuredPassword.replace(i, i + 1, "-");
            }
            for (Character c : correctGuesses) {
                if (currentChar == c || currentChar == c + 32) {
                    obscuredPassword.replace(i, i + 1, String.valueOf(c));
                }
            }
        }
        return obscuredPassword.toString().toUpperCase();
    }

    public void setCorrectGuesses(List<Character> correctGuesses) {
        this.correctGuesses = correctGuesses;
    }

    public boolean checkPassword() {
        return currentPassword.equalsIgnoreCase(getObscuredPassword());
    }

    public List<Character> getCorrectGuesses() {
        return correctGuesses;
    }
}
