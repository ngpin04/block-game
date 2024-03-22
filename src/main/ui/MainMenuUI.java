package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


import javax.swing.*;

public class MainMenuUI extends JPanel
                        implements ActionListener {
    protected JButton newGameButton;
    protected JButton loadGameButton;
    private GameAppUI gameAppUI;

    // MODIFIES: this
    // EFFECTS: start main menu UI
    public MainMenuUI() {
        newGameButton = new JButton("New Game");
        newGameButton.setVerticalTextPosition(AbstractButton.CENTER);
        newGameButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        newGameButton.setMnemonic(KeyEvent.VK_D);
        newGameButton.setActionCommand("new game");

        loadGameButton = new JButton("Load Game");
        loadGameButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        loadGameButton.setHorizontalTextPosition(AbstractButton.CENTER);
        loadGameButton.setMnemonic(KeyEvent.VK_M);
        loadGameButton.setActionCommand("load game");

        newGameButton.addActionListener(this);
        loadGameButton.addActionListener(this);

        newGameButton.setToolTipText("Create a new game.");
        loadGameButton.setToolTipText("Load a saved game");

        //Add Components to this container, using the default FlowLayout.
        add(newGameButton);
        add(loadGameButton);
    }

    // MODIFIES: this
    // EFFECTS: load game and change to the game GUI
    public void actionPerformed(ActionEvent e) {
        loadGameButton.setEnabled(false);
        newGameButton.setEnabled(false);

        if ("new game".equals(e.getActionCommand())) {
            gameAppUI = new GameAppUI(true);
        } else {
            gameAppUI = new GameAppUI(false);
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    // Citation: from Button Demo
    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        MainMenuUI newContentPane = new MainMenuUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: run game
    public void startGame() {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
