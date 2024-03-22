package ui;

import model.GameState;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GameAppUI extends JFrame {
    private static final int WIDTH = 9;
    private static final int HEIGHT = 9;
    private static final String JSON_STORE = "./data/game.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private GameState game;
    private GamePanel gp;

    public GameAppUI(Boolean newGame) {
        super("Undecided game name");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);

        game = new GameState();
        gp = new GamePanel(game);
        add(gp);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        if (!newGame) {
            loadGameState();
        }
    }

    private class MouseHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
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
