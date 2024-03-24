package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;
import persistence.Writable;

// represents a block in a game state
public class Block implements Writable {
    private static final int WIDTH = 9;
    private static final int HEIGHT = 9;
    private int xpos;
    private int ypos;

    // EFFECTS: create an instance of a block at coordinate (x, y)
    public Block(int x, int y) {
        xpos = x;
        ypos = y;
    }

    public int getX() {
        return xpos;
    }

    public int getY() {
        return ypos;
    }

    // MODIFIES: this
    // EFFECTS: change position to a random adjacent square or stay the same
    public void jump(GameState g) {
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        for (int dir = 0; dir < 4; dir++) {
            int u = xpos + dx[dir];
            int v = ypos + dy[dir];
            if (0 <= u && u < HEIGHT && 0 <= v && v < WIDTH && !g.isBlocked(u, v)) {
                xs.add(u);
                ys.add(v);
            }
        }

        xs.add(xpos);
        ys.add(ypos);
        Random rand = new Random();
        int chosenIndex = rand.nextInt(xs.size());
        xpos = xs.get(chosenIndex);
        ypos = ys.get(chosenIndex);
    }

    @Override
    // EFFECTS: return the block object as JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("xpos", xpos);
        json.put("ypos", ypos);
        return json;
    }
}
