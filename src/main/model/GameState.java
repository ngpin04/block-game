package model;

import java.util.ArrayList;

public class GameState {
    private static final int WIDTH = 9;
    private static final int HEIGHT = 9;
    private final Player p1;
    private final Player p2;
    private final Boolean[][] blocked;
    private int currentTurn;
    private final ArrayList<Block> blockList;

    public GameState() {
        p1 = new Player(true, 10);
        p2 = new Player(false, 10);
        blocked = new Boolean [HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                blocked[i][j] = false;
            }
        }
        currentTurn = 1;
        blocked[p1.getX()][p1.getY()] = true;
        blocked[p2.getX()][p2.getY()] = true;
        blockList = new ArrayList<>();
    }

    // REQUIRES: (x, y) lies in the board and
    //      blocked[x][y] is false and the current player has a block
    // MODIFIES: this
    // EFFECTS: player place a block at (x, y)
    public void placeBlock(int x, int y) {
        blocked[x][y] = true;
        if (currentTurn % 2 == 1) {
            p1.decreaseNumBlocks();
        } else {
            p2.decreaseNumBlocks();
        }
        currentTurn += 1;
        blockList.add(new Block(x, y));
    }

    public Boolean isBlocked(int x, int y) {
        return blocked[x][y];
    }

    // EFFECTS: return the current player in turn
    public Player currentPlayer() {
        if (currentTurn % 2 == 0) {
            return p2;
        } else {
            return p1;
        }
    }

    // REQUIRES: (x, y) is in the board and
    //      blocked[x][y] is false and the current
    // player can move to (x, y)
    // MODIFIES: this
    // EFFECTS: this player moves to (x, y);
    public void move(int x, int y) {
        blocked[x][y] = true;
        Player p = currentPlayer();
        blocked[p.getX()][p.getY()] = false;
        p.move(x, y);
        currentTurn += 1;
    }

    // EFFECTS: return 0 the game is not ended, otherwise
    // return the player number that win;
    public int ended() {
        if (p1.win()) {
            return 1;
        } else if (p2.win()) {
            return 2;
        } else {
            return 0;
        }
    }

    // EFFECTS: return 1 if it is p1's turn
    public int getPlayerTurn() {
        if (currentTurn % 2 == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    public Player getPlayer1() {
        return p1;
    }

    public Player getPlayer2() {
        return p2;
    }

    // EFFECTS: get the block at (x, y) or null if there isn't a
    // block at (x, y)
    public Block getBlock(int x, int y) {
        for (Block b : blockList) {
            if (b.getX() == x && b.getY() == y) {
                return b;
            }
        }
        return null;
    }

    // REQUIRES: there is a block at (x, y)
    // MODIFIES: this
    // EFFECTS: make the block at (x, y) do a random jump
    public void randomJump(int x, int y) {
        blocked[x][y] = false;
        Block b = getBlock(x, y);
        b.jump(this);
        blocked[b.getX()][b.getY()] = true;
    }

    public Boolean hasBlock() {
        return !blockList.isEmpty();
    }
}
