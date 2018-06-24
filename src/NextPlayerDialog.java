import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NextPlayerDialog extends JFrame {
    private static NextPlayerDialog instance = null;
    private JButton jButton;

    private NextPlayerDialog(Game game) throws HeadlessException {
        super("Next player");
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        };

        jButton = new JButton("Ok");
        jButton.addActionListener(actionListener);
        setLayout(new FlowLayout());
        add(new JLabel("It's " + game.getCurrentplayer().name +"'s turn!"));
        add(jButton);
        setSize(350, 100);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                super.windowDeactivated(e);
                setVisible(false);
            }
        });
    }

    /**
     * Has to be called before using this class.
     */
    public static void setGame(Game game) {
        instance = new NextPlayerDialog(game);
    }

    public static void call() {
        instance.setVisible(true);
    }
}
