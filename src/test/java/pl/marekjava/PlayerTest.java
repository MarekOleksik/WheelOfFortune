package pl.marekjava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
