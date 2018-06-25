import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NextPlayerDialog extends JFrame {
    private static NextPlayerDialog instance = null;
    private JButton jButton;
    private JLabel jLabel;
    private Game game;

    private NextPlayerDialog(Game game) throws HeadlessException {
        super("Next player");
        this.game = game;

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                game.gameWindow.getRackPanel().getTilePanel().setShouldPaintTiles(true);
                game.gameWindow.getRackPanel().repaint();
            }
        };

        jLabel = new JLabel();
        jButton = new JButton("Ok");
        jButton.addActionListener(actionListener);

        setLayout(new FlowLayout());
        add(jLabel);
        add(jButton);
        setSize(350, 100);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                super.windowDeactivated(e);
                setVisible(false);
                game.gameWindow.getRackPanel().getTilePanel().setShouldPaintTiles(true);
                game.gameWindow.getRackPanel().repaint();
            }
        });
    }

    /**
     * Has to be called before using this class.
     */
    public static void setGame(Game game) {
        instance = new NextPlayerDialog(game);
    }

    public static void call(Component caller) {
        instance.setLocationRelativeTo(caller);
        instance.setVisible(true);
        instance.jLabel.setText("It's " + instance.game.getCurrentplayer().name + "'s turn!");
    }
}
