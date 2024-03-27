package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;

// Represent the Save or Quit button
public class SavePanel extends JPanel implements ActionListener {
    private final GameAppUI gui;

    // EFFECTS: create game button
    SavePanel(GameAppUI gui) {
        this.gui = gui;

        JButton saveGameButton = new JButton("Save and Quit");
        saveGameButton.setVerticalTextPosition(AbstractButton.CENTER);
        saveGameButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        saveGameButton.setActionCommand("Save game");

        JButton quitGameButton = new JButton("Quit");
        quitGameButton.setVerticalTextPosition(AbstractButton.CENTER);
        quitGameButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        quitGameButton.setActionCommand("Quit game");

        saveGameButton.addActionListener(this);
        quitGameButton.addActionListener(this);
        add(saveGameButton);
        add(quitGameButton);
    }

    // MODIFIES: this
    // EFFECTS: save game and quit
    public void actionPerformed(ActionEvent e) {
        if ("Save game".equals(e.getActionCommand())) {
            gui.saveGameState();
        }
        exit(0);
    }
}
