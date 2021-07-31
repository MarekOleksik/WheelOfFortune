package pl.marekjava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Test class, where I test methods in Player class
public class PlayerTest {
    @Test
    public void isNameValidPrint() {
        Player marek = new Player("Marek");
        assertTrue(marek.toString().equals("[<Marek>]"));
    }

    @Test
    public void throwExceptionWhenNameIsEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Player marek = new Player("");
        });
    }

    @Test
    public void throwExceptionWhenNameIsOneSpace() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Player marek = new Player(" ");
        });
    }

    @Test
    public void throwExceptionWhenNameIsManySpaces() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Player marek = new Player("      ");
        });
    }

    @Test
    public void throwExceptionWhenNameIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Player marek = new Player(null);
        });
    }
}