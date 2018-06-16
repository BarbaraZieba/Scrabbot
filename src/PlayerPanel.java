import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    public PlayerPanel(Object[] players) {
        setLayout(new GridLayout(players.length, 0));
        for(int i = 0; i<players.length;i++)
            add(new JLabel(players[i].toString()));
    }
}
