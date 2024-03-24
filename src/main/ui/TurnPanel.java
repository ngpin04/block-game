package ui;

import model.GameState;

import javax.swing.*;
import java.awt.*;

public class TurnPanel extends JPanel {
    private static final String TXT = "The current player turn is: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private final GameState game;

    private final JLabel turn;

    // Constructs a score panel
    // effects: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public TurnPanel(GameState g) {
        game = g;
        setBackground(new Color(180, 180, 180));
        turn = new JLabel(TXT + game.getPlayerTurn());
        turn.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(turn);
        add(Box.createHorizontalStrut(10));
    }

    // Updates the score panel
    // modifies: this
    // effects:  updates number of invaders shot and number of missiles
    //           remaining to reflect current state of game
    public void update() {
        turn.setText(TXT + game.getPlayerTurn());
        repaint();
    }
}