package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {
    GameState g;

    @BeforeEach
    void RunBefore() {
        g = new GameState();
    }

    @Test
    void simpleMovingTest() {
        assertEquals(g.currentPlayer(), g.getPlayer1());
        assertEquals(g.getPlayerTurn(), 1);
        g.move(4, 7);
        assertEquals(g.getPlayerTurn(), 2);
        assertEquals(g.currentPlayer(), g.getPlayer2());
        assertTrue(g.isBlocked(4, 7));
        assertFalse(g.isBlocked(4, 8));
        g.move(4, 1);
        assertEquals(g.getPlayerTurn(), 1);
        assertEquals(g.currentPlayer(), g.getPlayer1());
        assertTrue(g.isBlocked(4, 1));
        assertFalse(g.isBlocked(4, 0));
    }

    @Test
    void simplePlacingBlockTest() {
        assertFalse(g.hasBlock());
        g.placeBlock(2, 3);
        assertTrue(g.hasBlock());
        assertTrue(g.isBlocked(2, 3));
        g.placeBlock(0, 0);
        assertTrue(g.hasBlock());
        assertNull(g.getBlock(0, 1));
        assertNull(g.getBlock(0, 2));
        Block b = g.getBlock(0, 0);
        assertEquals(b.getX(), 0);
        assertEquals(b.getY(), 0);
        assertTrue(g.isBlocked(0, 0));
        assertFalse(g.isBlocked(0, 1));
    }

    @Test
    void endedTestFirst() {
        assertEquals(g.ended(), 0);
        g.move(5, 0);
        assertEquals(g.ended(), 1);
    }

    @Test
    void endedTestSecond() {
        assertEquals(g.ended(), 0);
        g.move(5, 1);
        g.move(5, 8);
        assertEquals(g.ended(), 2);
    }

    @Test
    void randomJumpTest() {
        g.placeBlock(2, 3);
        Block b = g.getBlock(2, 3);
        g.randomJump(2, 3);
        int u = b.getX();
        int v = b.getY();
        assertTrue(g.isBlocked(u, v));

    }
}
