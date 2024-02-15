package model;

public class GameState {
    private static final int WIDTH = 9;
    private static final int HEIGHT = 9;
    private final Player p1;
    private final Player p2;
    private final Boolean[][] blocked;
    private int currentTurn;

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



    // EFFECTS: draw the outline of the board
    public void outline() {
        System.out.print(" ");
        for (int j = 0; j < WIDTH; j++) {
            System.out.print("+==");
        }
        System.out.println("+");
    }

    // EFFECTS: clear console screen
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // EFFECTS: mark the column number for the board;
    public void numbering() {
        for (int i = 0; i < WIDTH; i++) {
            System.out.print("  ");
            System.out.print(i);
        }
        System.out.println();
    }

    // EFFECTS: draw a row for the board
    public void drawRow(int i) {
        System.out.print(i);
        for (int j = 0; j < WIDTH; j++) {
            System.out.print("|");
            if (!blocked[i][j]) {
                System.out.print("  ");
            } else {
                if (p1.getX() == i && p1.getY() == j) {
                    System.out.print("P1");
                } else if (p2.getX() == i && p2.getY() == j) {
                    System.out.print("P2");
                } else {
                    System.out.print("XX");
                }
            }
        }
        System.out.println("|");
    }

    // EFFECTS: draw the game state
    public void draw() {
        clearScreen();
        numbering();

        for (int i = 0; i <= HEIGHT; i++) {
            outline();

            if (i == HEIGHT) {
                break;
            }

            drawRow(i);
        }
    }
}
