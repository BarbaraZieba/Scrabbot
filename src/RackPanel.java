import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class RackPanel extends JPanel {
    public static class TilePanel extends JPanel {
        Game game;
        int offset = 10;
        int size = 32;

        public TilePanel(Game game) {
            this.game = game;
            setPreferredSize(new Dimension(7*size+8*offset, 50));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(0, 50, 0));
            g.fillRect(0, 0, getWidth(), getHeight());
            int x = offset;
            int y = offset;
            g.setFont(new Font("Arial", Font.BOLD, 16));
            ArrayList<Character> rack = game.getCurrentplayer().getTiles();
            if (rack == null)
                return;
            for (Character c : rack) {
                Tile.paintTile(g, c, x, y);
                x += size + offset;
            }
        }
    }

    public RackPanel(Game game) {
        super();
        setLayout(new FlowLayout());
        JButton jButton = new JButton("Pass");
        jButton.addActionListener(e->game.currentPlayerPass());
        jButton.setPreferredSize(new Dimension(128,32));
        add(jButton);
        TilePanel tilePanel = new TilePanel(game);
        add(tilePanel);
        jButton = new JButton("Exchange Tiles");
        add(jButton);
        jButton.setPreferredSize(new Dimension(128,32));
        jButton.addActionListener(e -> ExchangeDialog.call(this));
        setBackground(new Color(165, 165, 165));
    }

}
