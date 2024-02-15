package ui;

import model.GameState;

import java.util.Scanner;

import static java.lang.Math.abs;

public class GameApp {
    private static final int WIDTH = 9;
    private static final int HEIGHT = 9;

    private Scanner input;
    private final GameState game;

    public GameApp() {
        game = new GameState();
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

        game.draw();
        System.out.print("Player ");
        System.out.print(game.ended());
        System.out.println(" win!");
    }


    // MODIFIES: this
    // EFFECTS: play a turn
    public void playTurn() {
        game.draw();
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
                if (!game.currentPlayer().hasBlocks()) {
                    System.out.println("You have no blocks!");
                } else {
                    playBlock();
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

    // EFFECTS: ask the player to place a block
    public void playBlock() {
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
}
