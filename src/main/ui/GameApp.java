package ui;

import model.GameState;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Math.abs;

public class GameApp {
    private static final int WIDTH = 9;
    private static final int HEIGHT = 9;
    private static final String JSON_STORE = "./data/game.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Scanner input;
    private GameState game;

    public GameApp() {
        game = new GameState();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: run the game
    public void runGame() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        while (game.ended() == 0) {
            playTurn();
        }

        draw();
        System.out.print("Player ");
        System.out.print(game.ended());
        System.out.println(" win!");
    }


    // MODIFIES: this
    // EFFECTS: play a turn
    public void playTurn() {
        draw();
        System.out.print("Player ");
        System.out.print(game.getPlayerTurn());
        System.out.println(" turn!");

        String cmd;
        while (true) {
            displayTurnChoice();
            cmd = input.next();
            if (cmd.equals("1")) {
                playMove();
            } else if (cmd.equals("2")) {
                if (!playBlock()) {
                    continue;
                }
            } else if (cmd.equals("3")) {
                if (!moveBlock()) {
                    continue;
                }
            } else {
                continue;
            }
            break;
        }
    }

    // EFFECTS: display menu of options for a turn
    public void displayTurnChoice() {
        System.out.println("1. Move your piece");
        System.out.println("2. Place a block");
        System.out.println("3. Randomly displace a block");
    }

    // EFFECTS: get the coordinate from input
    public int getCoordinate() {
        int res;
        String inp;
        while (true) {
            inp = input.next();
            if (inp.length() != 1) {
                System.out.println("Invalid number!\n");
                continue;
            } else {
                try {
                    res = Integer.parseInt(inp);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number!\n");
                    continue;
                }
            }
            break;
        }
        return res;
    }

    // EFFECTS: check if the coordinate is inside the board
    public Boolean inBoard(int x, int y) {
        return x < HEIGHT && y < WIDTH;
    }

    // EFFECTS: ask the player to place a block, return true
    // if a block is placed and vice versa;
    public Boolean playBlock() {
        if (!game.currentPlayer().hasBlocks()) {
            System.out.println("You have no blocks!");
            return false;
        }
        int row;
        int col;
        while (true) {
            System.out.print("Please input your row number: ");
            row = getCoordinate();
            System.out.print("Please input your column number: ");
            col = getCoordinate();
            if (!inBoard(row, col)) {
                System.out.println("Invalid row and column number!");
                continue;
            }

            if (game.isBlocked(row, col)) {
                System.out.println("This cell is invalid");
                continue;
            }

            game.placeBlock(row, col);
            break;
        }
        return true;
    }

    // EFFECTS: ask the player to move
    public void playMove() {
        int row;
        int col;
        while (true) {
            System.out.print("Please input your row number: ");
            row = getCoordinate();
            System.out.print("Please input your column number: ");
            col = getCoordinate();
            if (!inBoard(row, col)) {
                System.out.println("Invalid row and column number!");
                continue;
            }

            if (game.isBlocked(row, col)) {
                System.out.println("This cell is invalid");
                continue;
            }

            if (abs(game.currentPlayer().getX() - row)
                    + abs(game.currentPlayer().getY() - col) > 1) {
                System.out.println("You are moving too far!!!");
                continue;
            }

            game.move(row, col);
            break;
        }
    }

    // EFFECTS: randomly move a chosen block, return true
    // if a block is moved and vice versa
    public Boolean moveBlock() {
        if (!game.hasBlock()) {
            System.out.println("There isn't any blocks yet!");
            return false;
        }
        int row;
        int col;
        while (true) {
            System.out.print("Please input your row number: ");
            row = getCoordinate();
            System.out.print("Please input your column number: ");
            col = getCoordinate();
            if (!inBoard(row, col)) {
                System.out.println("Invalid row and column number!");
                continue;
            }

            if (game.getBlock(row, col) == null) {
                System.out.println("This cell isn't a block");
                continue;
            }

            game.randomJump(row, col);
            break;
        }
        return true;
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
            if (!game.isBlocked(i, j)) {
                System.out.print("  ");
            } else {
                Player p1 = game.getPlayer1();
                Player p2 = game.getPlayer2();
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

    // Citation: JsonSerializationDemo project
    // EFFECTS: saves the game state to file
    private void saveGameState() {
        try {
            jsonWriter.open();
            jsonWriter.write(game);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadGameState() {
        try {
            game = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
