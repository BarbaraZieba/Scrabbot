import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class RackPanel extends JPanel {
    TilePanel tilePanel;

    public RackPanel(Game game) {
        super();
        setLayout(new FlowLayout());
        JButton jButton = new JButton("Pass");
        jButton.addActionListener(e -> game.currentPlayerPass());
        jButton.setPreferredSize(new Dimension(128, 32));
        add(jButton);
        this.tilePanel = new TilePanel(game);
        add(tilePanel);
        jButton = new JButton("Exchange Tiles");
        add(jButton);
        jButton.setPreferredSize(new Dimension(128, 32));
        jButton.addActionListener(e -> ExchangeDialog.call(this));
        setBackground(new Color(165, 165, 165));
    }

    public TilePanel getTilePanel() {
        return tilePanel;
    }
}
