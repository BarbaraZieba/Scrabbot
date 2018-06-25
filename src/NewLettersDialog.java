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

    private NewLettersDialog(Game game) throws HeadlessException {
        super("New tiles");
        tilePanel = new RackPanel.TilePanel(game);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NextPlayerDialog.call(game.gameWindow);
            }
        };

        jButton = new JButton("Ok");
        jButton.addActionListener(actionListener);
        setLayout(new FlowLayout());
        add(new JLabel("Your new tiles: "));
        add(tilePanel);
        add(jButton);
        setSize(350, 200);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                super.windowDeactivated(e);
                NextPlayerDialog.call(game.gameWindow);
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
        instance.setLocationRelativeTo(caller);
        instance.setVisible(true);
        instance.tilePanel.setPlayer(player);
    }
}
