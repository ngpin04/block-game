package ui;

import model.GameState;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.abs;

// Represent the GamePanel where the game is played
public class GamePanel extends JPanel {
    private static final int N = 9;
    private static final int SIZE = 80;

    private final GameState game;
    private final GameButton[][] gameButtons;
    private int prevX;
    private int prevY;

    TurnPanel tp;

    // EFFECTS: create a game panel with GameState and TurnPanel
    public GamePanel(GameState game, TurnPanel tp) {
        super(new GridLayout(N, N));
        this.tp = tp;
        this.game = game;
        this.setPreferredSize(new Dimension(N * SIZE, N * SIZE));
        gameButtons = new GameButton[N][N];
        prevX = prevY = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                gameButtons[i][j] = new GameButton(i, j);
                gameButtons[i][j].addActionListener(new ButtonHandler());
                this.add(gameButtons[i][j]);
            }
        }

        drawGame();
    }

    private static class GameButton extends JButton {
        // EFFECTS: create a game button at cell (i, j)
        public GameButton(int i, int j) {
            super("");
            this.setOpaque(true);
            this.setBorderPainted(false);
            if (((i + j) & 1) == 0) {
                this.setBackground(Color.gray);
            }
        }
    }

    private class ButtonHandler implements ActionListener {
        // MODIFIES: this
        // EFFECTS: record the button presses
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N;j++) {
                    if (source == gameButtons[i][j]) {
                        if (prevX == -1) {
                            prevX = i;
                            prevY = j;
                        } else if (game.ended() == 0 && !moveGame(prevX, prevY, i, j)) {
                            prevX = i;
                            prevY = j;
                        } else {
                            prevX = prevY = -1;
                        }
                        drawGame();
                        return;
                    }
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: play a move from (i, j) to (u, v), return true if playable
    // and vice versa
    private Boolean moveGame(int i, int j, int u, int v) {
        if (!game.isBlocked(i, j)) {
            if (i == u && j == v) {
                game.placeBlock(i, j);
                return true;
            }
        } else {
            Player p = game.getPlayer1();
            if (game.getPlayerTurn() == 2) {
                p = game.getPlayer2();
            }

            if (p.getX() == i && p.getY() == j) {
                if (!game.isBlocked(u, v) && abs(i - u) + abs(j - v) == 1) {
                    game.move(u, v);
                    return true;
                }
            } else {
                if (i == u && j == v) {
                    game.randomJump(i, j);
                    return true;
                }
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: draw the game
    public void drawGame() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (game.isBlocked(i, j)) {
                    gameButtons[i][j].setText("XX");
                } else {
                    gameButtons[i][j].setText("");
                }
            }
        }

        Player p1 = game.getPlayer1();
        Player p2 = game.getPlayer2();

        gameButtons[p1.getX()][p1.getY()].setText("P1");
        gameButtons[p2.getX()][p2.getY()].setText("P2");
        tp.update();
    }
}
