package persistence;

import org.junit.jupiter.api.BeforeEach;
import model.GameState;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    GameState game;

    @BeforeEach
    void runBefore() {
        game = new GameState();
    }

    @Test
    void testInvalidFile() {
        JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
        try {
            writer.open();
            fail("exception expected");
            writer.write(game);
            writer.close();
        } catch (FileNotFoundException e) {
            // expected
        }
    }

    @Test
    void testEmptyGame() {
        JsonWriter writer = new JsonWriter("./data/emptyGame.json");
        try {
            writer.open();
            writer.write(game);
            writer.close();

            JsonReader reader = new JsonReader("./data/emptyGame.json");
            try {
                GameState tmp = reader.read();
                assertTrue(tmp.isBlocked(4, 0));
                assertTrue(tmp.isBlocked(4, 8));
                assertEquals(1, tmp.getPlayerTurn());
            } catch (IOException ioe) {
                fail("unexpected exception");
            }
        } catch (FileNotFoundException e) {
            fail("unexpected exception");
        }
    }

    @Test
    void testGeneralGame() {
        JsonWriter writer = new JsonWriter("./data/testWriterGeneralGame.json");
        game.move(3, 3);
        game.move(5, 5);
        game.placeBlock(0, 0);
        game.placeBlock(8, 8);
        game.placeBlock(2 ,3);
        try {
            writer.open();
            writer.write(game);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGame.json");
            try {
                GameState tmp = reader.read();
                assertTrue(tmp.isBlocked(3, 3));
                assertTrue(tmp.isBlocked(5, 5));
                assertTrue(tmp.isBlocked(2, 3));
                assertTrue(tmp.isBlocked(0, 0));
                assertTrue(tmp.isBlocked(8, 8));
                assertFalse(tmp.isBlocked(3, 2));
                assertEquals(2, tmp.getPlayerTurn());
            } catch (IOException ioe) {
                fail("unexpected exception");
            }
        } catch (FileNotFoundException e) {
            fail("unexpected exception");
        }
    }
}
