package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlockTest {
    GameState g;


    @BeforeEach
    void RunBefore() {
        g = new GameState();
    }

   Boolean inboard(Block a) {
        return 0 <= a.getX() && a.getX() < 9 && 0 <= a.getY() && a.getY() < 9;
   }

    @Test
    void jumpingTest() {
        g.placeBlock(0, 0);
        g.placeBlock(0, 1);
        g.placeBlock(8, 8);
        g.placeBlock(3, 4);
        Block a = g.getBlock(0, 0);
        Block b = g.getBlock(8, 8);
        Block c = g.getBlock(3, 4);
        a.jump(g);
        b.jump(g);
        c.jump(g);
        assertTrue(inboard(a));
        assertTrue(inboard(b));
        assertTrue(inboard(c));
    }
}
