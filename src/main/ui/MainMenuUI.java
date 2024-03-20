package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


import javax.swing.*;

public class MainMenuUI extends JPanel
                        implements ActionListener {
    protected JButton newGameButton;
    protected JButton loadGameButton;

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

    public void actionPerformed(ActionEvent e) {
        if ("new game".equals(e.getActionCommand())) {
            loadGameButton.setEnabled(true);
            newGameButton.setEnabled(false);
        } else {
            loadGameButton.setEnabled(false);
            newGameButton.setEnabled(true);
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
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
