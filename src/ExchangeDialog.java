import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

public class ExchangeDialog extends JFrame {
    private static ExchangeDialog instance = null;
    private JTextField jTextField;
    private JButton jButton;
    private boolean exchanged;
    private RackPanel.TilePanel tilePanel;
    private Game game;

    private ExchangeDialog(Game game) throws HeadlessException {
        super("Exchange tiles");
        this.exchanged = false;
        this.game = game;

        tilePanel = new RackPanel.TilePanel(game);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (exchanged) {
                    setVisible(false);
                    exchanged = false;
                } else {
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
                            exchanged = true;
                            jButton.setText("Confirm");

                            game.gameWindow.repaintChildren();
                            tilePanel.repaint();
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "The tiles you inserted are not in your rack.");
                        }
                    }
                }
            }
        };

        jButton = new JButton("Exchange");
        jButton.addActionListener(actionListener);

        jTextField = new JTextField();
        jTextField.addActionListener(actionListener);
        jTextField.setPreferredSize(new Dimension(100, 20));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            if (exchanged)
                JOptionPane.showMessageDialog(new JFrame(), "You can't cancel the exchange now.");
            exchanged = false;
            setVisible(false);
        });

        setLayout(new FlowLayout());
        add(new JLabel("Your current tiles: "));
        add(tilePanel);
        add(jTextField);
        add(jButton);
        add(cancelButton);

        setSize(350, 200);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                super.windowDeactivated(e);
                exchanged = false;
                setVisible(false);
            }
        });
    }

    /**
     * Has to be called before using this class.
     */
    public static void setGame(Game game) {
        instance = new ExchangeDialog(game);
    }

    public static void call(Component caller) {
        if(instance.game.checkGameOver())
            return;
        if (instance.isVisible())
            return;
        instance.setLocationRelativeTo(caller);
        instance.setVisible(true);
        instance.jTextField.setText("");
        instance.jTextField.requestFocus();
    }
}

