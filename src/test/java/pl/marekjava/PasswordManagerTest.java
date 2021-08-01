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
        list.add("Koło fortuny");
        pm.setPasswords(list);
        String randomPassword = pm.getRandomPassword();
        assertTrue(randomPassword.equals("Koło fortuny"));
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
}
