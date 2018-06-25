import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewLettersDialog extends JFrame {
    private static NewLettersDialog instance = null;
    private JButton jButton;
    private RackPanel.TilePanel tilePanel;
    private Game game;

    private NewLettersDialog(Game game) throws HeadlessException {
        super("Your tiles");
        this.game = game;
        tilePanel = new RackPanel.TilePanel(game);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //NextPlayerDialog.call(game.gameWindow);
                setVisible(false);
            }
        };

        jButton = new JButton("Ok");
        jButton.addActionListener(actionListener);
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
                //NextPlayerDialog.call(game.gameWindow);
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
        if (instance.game.checkGameOver())
            return;
        instance.setLocationRelativeTo(caller);
        instance.tilePanel.setPlayer(player);
    }
}
