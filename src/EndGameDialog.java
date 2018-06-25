import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

public class EndGameDialog extends JFrame {
    static EndGameDialog instance;
    JLabel winnerLabel;
    JLabel playerLabels[];

    public EndGameDialog(Game game) {
        super("Game Over");
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        JPanel winnerPanel = new JPanel();
        winnerPanel.setBackground(new Color(0, 50, 0));
        winnerLabel = new JLabel();
        winnerLabel.setForeground(new Color(220, 200, 100));
        winnerPanel.add(winnerLabel);
        add(winnerPanel, BorderLayout.NORTH);
        //  winnerLabel.setPreferredSize(new Dimension(300,30));

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(0, 1));
        add(playerPanel, BorderLayout.CENTER);
        playerLabels = new JLabel[game.getPlayers().size()];
        for (int i = 0; i < playerLabels.length; i++) {
            playerLabels[i] = new JLabel("", SwingConstants.CENTER);
            playerPanel.add(playerLabels[i]);
            playerLabels[i].setPreferredSize(new Dimension(300, 30));
        }

        JButton jButton = new JButton("OK");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jButton);
        jButton.addActionListener(e -> setVisible(false));
        add(buttonPanel, BorderLayout.SOUTH);

        // setSize(350, 175);
        pack();
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public static void call(Game game) {
        if (instance == null)
            instance = new EndGameDialog(game);
        Player[] players = new Player[game.getPlayers().size()];
        for (int i = 0; i < players.length; i++)
            players[i] = game.getPlayers().get(i);
        Arrays.sort(players, (o1, o2) -> o2.score.compareTo(o1.score));
        for (int i = 0; i < players.length; i++)
            instance.playerLabels[i].setText(players[i].toString());
        instance.winnerLabel.setText("Congratulations " + players[0].name + ", you won the game!");
        instance.setVisible(true);
    }
}
