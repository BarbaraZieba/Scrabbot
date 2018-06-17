import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class NewGameDialog extends JFrame {

    public NewGameDialog() throws HeadlessException {
        super("Starting a Scrabble Game");
        JTextField[] nameFields = new JTextField[4];
        JLabel[] labels = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            labels[i] = new JLabel("Player " + (i+1) + ":");
            nameFields[i] = new JTextField("");
        }

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            ArrayList<Player> players = new ArrayList<Player>();
            for (int i = 0; i < 4; i++) {
                String s = nameFields[i].getText();
                if (!s.equals(""))
                    players.add(new Player(s));
            }
            if (players.size() > 0) {
                try {
                    new GameWindow(players);
                    setVisible(false);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "There has to be at least 1 player.");
            }
        });
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(0,2));
        for (int i = 0; i < 4; i++) {
            playerPanel.add(labels[i]);
            playerPanel.add(nameFields[i]);
        }
        setLayout(new BorderLayout());
        add(playerPanel, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
        setSize(250, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
}
