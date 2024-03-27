package ui;

import model.GameState;

import javax.swing.*;
import java.awt.*;

// Reflect the player turn or if the game has finished
public class TurnPanel extends JPanel {
    private static final String TURN = "The current player turn is: ";
    private static final String END = "The winner is player ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private final GameState game;

    private final JLabel turn;

    // Citation: SpaceInvaders
    // effects: sets the background colour and draws the initial labels;
    public TurnPanel(GameState g) {
        game = g;
        setBackground(new Color(180, 180, 180));
        turn = new JLabel(TURN + game.getPlayerTurn());
        turn.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(turn);
        add(Box.createHorizontalStrut(10));
    }

    // modifies: this
    // effects: update the turn panel to reflect the current turn,
    // if the game has ended, reflect the player that won instead
    public void update() {
        if (game.ended() == 0) {
            turn.setText(TURN + game.getPlayerTurn());
        } else {
            turn.setText(END + game.ended());
        }
        repaint();
    }
}