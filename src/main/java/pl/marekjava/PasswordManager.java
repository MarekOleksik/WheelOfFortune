package pl.marekjava;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordManager {
    private List<String> passwords = new ArrayList<>();

    public PasswordManager() {
        passwords.add("Apetyt rośnie w miarę jedzenia");
        passwords.add("Co dwie głowy, to nie jedna");
        passwords.add("Cwiczenie czyni mistrza");
        passwords.add("Darowanemu koniowi w zęby się nie zagląda");
        passwords.add("Diabeł tkwi w szczegółach");
        passwords.add("Elektryka prąd nie tyka");
    }

    public String getRandomPassword() {
        Random r = new Random();
        int randomItem = r.nextInt(passwords.size());
        String randomPassword = passwords.get(randomItem);
        return randomPassword;
    }
}
