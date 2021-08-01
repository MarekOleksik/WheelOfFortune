package pl.marekjava;

import java.util.*;

public class PasswordManager {
    private List<String> passwords = new ArrayList<>();
    private Map<String, Boolean> usedPasswords = new HashMap<>();

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
        int randomItem = r.nextInt(passwords.size());
        String randomPassword = passwords.get(randomItem);

        int counter = 0;
        while (usedPasswords.get(randomPassword) == true) {
            randomItem = r.nextInt(passwords.size());
            randomPassword = passwords.get(randomItem);
            counter++;
            if (counter > passwords.size()) throw new IllegalStateException("Brak unikalnego hasła");
        }
        usedPasswords.put(randomPassword, true);

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
}
