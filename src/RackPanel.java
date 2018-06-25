import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class RackPanel extends JPanel {
    public static class TilePanel extends JPanel {
        Game game;
        int offset = 10;
        int size = 32;
        Player player;
        boolean shouldPaintTiles;


        public TilePanel(Game game) {
            this.game = game;
            this.shouldPaintTiles = true;
            setPreferredSize(new Dimension(7 * size + 8 * offset, 50));
        }

        public void setPlayer(Player player) {
            this.player = player;
        }

        public void setShouldPaintTiles(boolean shouldPaintTiles) {
            this.shouldPaintTiles = shouldPaintTiles;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(0, 50, 0));
            g.fillRect(0, 0, getWidth(), getHeight());

            if (shouldPaintTiles) {
                int x = offset;
                int y = offset;
                g.setFont(new Font("Arial", Font.BOLD, 16));
                ArrayList<Character> rack;
                if (player == null)
                    rack = game.getCurrentplayer().getTiles();
                else rack = player.getTiles();
                if (rack == null)
                    return;
                for (Character c : rack) {
                    Tile.paintTile(g, c, x, y);
                    x += size + offset;
                }
            }
        }
    }

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
