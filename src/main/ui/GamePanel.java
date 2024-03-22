package ui;

import model.GameState;

import javax.swing.*;

public class GamePanel extends JPanel {
    private GameState game;

    GamePanel(GameState game) {
        this.game = game;
    }
}
