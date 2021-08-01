package pl.marekjava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordManagerTest {
    @Test
    public void isGenerateRandomPassword() {
        PasswordManager pm = new PasswordManager();
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
            PasswordManager pm = new PasswordManager();
            for (int i = 0; i <= pm.getSizeOfPasswords(); i++) {
                pm.getRandomPassword();
            }
        });
    }

    @Test
    public void throwExceptionWhenPasswordsListIsEmpty() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            PasswordManager pm = new PasswordManager();
            List<String> list = new ArrayList<>();
            pm.setPasswords(list);
            pm.getRandomPassword();
        });
    }
}
