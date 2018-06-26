import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ExchangeDialog extends JFrame {
    private static ExchangeDialog instance = null;
    private JTextField jTextField;
    private Game game;

    private ExchangeDialog(Game game) {
        super("Exchange tiles");
        this.game = game;

        TilePanel tilePanel = new TilePanel(game);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tiles = jTextField.getText().toUpperCase();
                if (tiles.length() > game.getBag().remainingTiles())
                    JOptionPane.showMessageDialog(new JFrame(),
                            "There are only " + game.getBag().remainingTiles() + " tiles in the bag.");
                else {
                    ArrayList<Tile> toTake = new ArrayList<>();
                    for (char c : tiles.toCharArray())
                        toTake.add(new Tile(c, -1, -1));
                    ArrayList<Character> taken = game.getCurrentplayer().takeTiles(toTake);
                    if (taken != null) {
                        game.addmove(new Move(game.getCurrentplayer(), null, 0, "-- EXCHANGE --", taken));
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(),
                                "The tiles you inserted are not in your rack.");
                    }
                }
            }
        };

        JButton jButton = new JButton("Exchange");
        jButton.addActionListener(actionListener);

        jTextField = new JTextField();
        jTextField.addActionListener(actionListener);
        jTextField.setPreferredSize(new Dimension(100, 20));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            setVisible(false);
        });

        setLayout(new FlowLayout());
        add(new JLabel("Your current tiles: "));
        add(tilePanel);
        add(jTextField);
        add(jButton);
        add(cancelButton);

        setSize(350, 175);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    /**
     * Has to be called before using this class.
     */
    public static void setGame(Game game) {
        instance = new ExchangeDialog(game);
    }

    public static void call(Component caller) {
        if (instance == null || instance.game.checkGameOver()) return;
        instance.setLocationRelativeTo(caller);
        instance.setVisible(true);
        instance.jTextField.setText("");
        instance.jTextField.requestFocus();
    }
}

