import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ExchangeDialog extends JFrame {
    private static ExchangeDialog instance = null;
    private JTextField jTextField;
    private JLabel jLabel;
    private Game game;
    private boolean exchanged;

    private ExchangeDialog() throws HeadlessException {
        super("Exchange tiles");

        JButton jButton = new JButton("OK");


        jTextField = new JTextField();
        jTextField.setPreferredSize(new Dimension(100, 20));
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tiles = jTextField.getText().toUpperCase();
                if (tiles.length() > game.getBag().remainingTiles())
                    JOptionPane.showMessageDialog(new JFrame(),
                            "There are only " + game.getBag().remainingTiles() + " tiles in the bag.");
                for(char c : tiles.toCharArray())
                    game.getCurrentplayer().take(c);
                game.getCurrentplayer().draw(game.getBag());
                for(char c : tiles.toCharArray())
                    game.getBag().addtobag(c,1);
                game.gameWindow.repaintChildren();
            }
        };
        jTextField.addActionListener(actionListener);
        jButton.addActionListener(actionListener);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));

        setLayout(new FlowLayout());
        jLabel = new JLabel("Your current tiles: ");
        add(jLabel);
        add(jTextField);
        add(jButton);
        add(cancelButton);
        setSize(250, 100);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    /**
     * Has to be called before using this class.
     */
    public static void setGame(Game game) {
        if (instance == null)
            instance = new ExchangeDialog();
        instance.game = game;
    }

    public static void call() {
        instance.exchanged = false;
        instance.setVisible(true);
        instance.jTextField.setText("");
        instance.jTextField.requestFocus();
    }
}
