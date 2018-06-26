import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewLettersDialog extends JFrame {
    private static NewLettersDialog instance = null;
    private JButton jButton;
    private TilePanel tilePanel;
    private Game game;

    private NewLettersDialog(Game game) {
        super("Your tiles");
        this.game = game;
        tilePanel = new TilePanel(game);

        jButton = new JButton("Ok");
        jButton.addActionListener(e -> setVisible(false));
        setLayout(new FlowLayout());
        add(new JLabel("Your tiles are now: "));
        add(tilePanel);
        add(jButton);

        setSize(350, 175);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                super.windowDeactivated(e);
                game.callNextPlayer();
                setVisible(false);
            }
        });
    }

    /**
     * Has to be called before using this class.
     */
    public static void setGame(Game game) {
        instance = new NewLettersDialog(game);
    }

    public static void call(Component caller, Player player) {
        if (instance.game.checkGameOver()) return;
        instance.setVisible(true);
        instance.setLocationRelativeTo(caller);
        instance.tilePanel.setPlayer(player);
        instance.jButton.requestFocus();
    }
}
