package model;

import persistence.Writable;
import org.json.JSONObject;

// Represent a player
public class Player implements Writable {
    private static final int WIDTH = 9;
    private static final int HEIGHT = 9;

    private final Boolean isFirst;
    private int xpos;
    private int ypos;
    private int numBlocksLeft;

    // EFFECTS: create an instance of a player with numBlocks and
    // whether this is the first player
    public Player(Boolean isFirst, int numBlocks) {
        this.isFirst = isFirst;
        xpos = WIDTH / 2;
        if (isFirst) {
            ypos = HEIGHT - 1;
        } else {
            ypos = 0;
        }
        numBlocksLeft = numBlocks;
    }

    // REQUIRES: the cell (x, y) has to lie in the board
    // MODIFIES: this
    // EFFECTS: change the position of the player
    public void move(int x, int y) {
        xpos = x;
        ypos = y;
    }

    // EFFECTS: check if this player has a wall or not
    public Boolean hasBlocks() {
        return numBlocksLeft > 0;
    }

    // REQUIRES: hasWalls
    // MODIFIES: this
    // EFFECTS: decrease the number of wall
    public void decreaseNumBlocks() {
        numBlocksLeft--;
    }

    // EFFECTS: return true if this player has won
    public Boolean win() {
        if (isFirst) {
            return ypos == 0;
        } else {
            return ypos == HEIGHT - 1;
        }
    }

    public int getX() {
        return xpos;
    }

    public int getY() {
        return ypos;
    }

    @Override
    // Citation: JsonSerializationDemo project
    // EFFECTS: return the player object as JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("isFirst", isFirst);
        json.put("xpos", xpos);
        json.put("ypos", ypos);
        json.put("numBlocksLeft", numBlocksLeft);
        return json;
    }
}
