package persistence;

import model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    GameState game;

    @BeforeEach
    void runBefore() {
        game = new GameState();
    }

    @Test
    void testInvalidFile() {
        JsonReader reader = new JsonReader("./data/zzzzz.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected;
        }
    }

    @Test
    void testEmptyGame() {
        JsonReader reader = new JsonReader("./data/emptyGame.json");
        try {
            GameState g = reader.read();
            assertTrue(g.isBlocked(4, 0));
            assertTrue(g.isBlocked(4, 8));
            assertFalse(g.isBlocked(4, 4));
            assertEquals(1, g.getPlayerTurn());
        } catch (IOException ioe) {
            fail("unexpected exception");
        }
    }

    @Test
    void testGeneralGame() {
        JsonReader reader = new JsonReader("./data/generalGame.json");
        try {
            GameState g = reader.read();
            assertTrue(g.isBlocked(5, 5));
            assertTrue(g.isBlocked(3, 3));
            assertFalse(g.isBlocked(2, 4));
            assertEquals(2, g.getPlayerTurn());
        } catch (IOException ioe) {
            fail("unexpected exception");
        }
    }

}
