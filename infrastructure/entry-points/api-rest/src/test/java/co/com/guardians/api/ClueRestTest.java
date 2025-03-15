package co.com.guardians.api;

import co.com.guardians.api.clue.ClueRest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClueRestTest {

    ClueRest clueRest = new ClueRest();

    @Test
    void apiRestTest() {
        var response = clueRest.commandName();
        assertEquals("Hello World", response);
    }
}
