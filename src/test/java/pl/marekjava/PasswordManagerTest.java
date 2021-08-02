package pl.marekjava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordManagerTest {
    private PasswordManager pm = new PasswordManager();

    @Test
    public void isGenerateRandomPassword() {
        List<String> list = new ArrayList<>();
        String firstPassword = "koło fortuny";
        String secondPassword = "ala ma kota";
        list.add(firstPassword);
        list.add(secondPassword);
        pm.setPasswords(list);
        String firstRandomPassword = pm.getRandomPassword();
        String secondRandom = pm.getRandomPassword();
        if (firstRandomPassword.equals(firstPassword)) {
            assertTrue(secondRandom.equals(secondPassword));
        } else if (firstRandomPassword.equals(secondPassword)) {
            assertTrue(secondRandom.equals(firstPassword));
        }
    }

    @Test
    public void throwExceptionWhenIsNoUniquePasswords() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            for (int i = 0; i <= pm.getSizeOfPasswords(); i++) {
                pm.getRandomPassword();
            }
        });
    }

    @Test
    public void throwExceptionWhenPasswordsListIsEmpty() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            List<String> list = new ArrayList<>();
            pm.setPasswords(list);
            pm.getRandomPassword();
        });
    }

    @Test
    public void isNoGuessLettersInPassword() {
        pm.setCurrentPassword("Elektryka prąd nie tyka");
        int result = pm.guessLetter('b');
        assertTrue(result == 0);
    }

    @Test
    public void isMoreGuessLettersInPassword() {
        pm.setCurrentPassword("Elektryka prąd nie tyka");
        int result = pm.guessLetter('k');
        assertTrue(result == 3);
    }

    @Test
    public void isMoreGuessUpperLettersInPassword() {
        pm.setCurrentPassword("Elektryka prąd nie tyka");
        int result = pm.guessLetter('E');
        assertTrue(result == 3);
    }

    @Test
    public void isNotGuessPassword() {
        pm.setCurrentPassword("Elektryka prąd nie tyka");
        assertFalse(pm.guessPassword("Elektryka prąd tyka"));
    }

    @Test
    public void isGuessPassword() {
        pm.setCurrentPassword("Elektryka prąd nie tyka");
        assertTrue(pm.guessPassword("Elektryka prąd nie tyka"));
    }

}
