package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
//    private final int WIDTH = 9;
    private static final int HEIGHT = 9;
    Player p1;
    Player p2;

    @BeforeEach
    void runBefore() {
        p1 = new Player(true, 0);
        p2 = new Player(false, 5);
    }

    @Test
    void moveAndHasBlocksTest() {
        p1.move(0, 0);
        assertTrue(p1.win());
        p1.move(0, 1);
        assertFalse(p1.win());
        assertFalse(p1.hasBlocks());
        assertTrue(p2.hasBlocks());
        p2.move(0, HEIGHT - 1);
        assertTrue(p2.win());
        p1.move(2, 3);
        assertEquals(p1.getX(), 2);
        assertEquals(p1.getY(), 3);
    }
    //

}