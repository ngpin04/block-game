package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameStateTest {
    GameState g;

    @BeforeEach
    void RunBefore() {
        g = new GameState();
    }

    @Test
    void simpleMovingTest() {
        g.move(4, 7);
        assertTrue(g.isBlocked(4, 7));
        assertFalse(g.isBlocked(4, 8));
        g.move(4, 1);
        assertTrue(g.isBlocked(4, 1));
        assertFalse(g.isBlocked(4, 0));
    }

    @Test
    void simplePlacingBlockTest() {
        g.placeBlock(2, 3);
        assertTrue(g.isBlocked(2, 3));
        g.placeBlock(0, 0);
        assertTrue(g.isBlocked(0, 0));
        assertFalse(g.isBlocked(0, 1));
    }
}
