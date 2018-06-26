import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TilePanel extends JPanel {
    private Game game;
    private final int offset = 10;
    private final int size = 32;
    private Player player;
    private boolean shouldPaintTiles;


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
            ArrayList<Character> rack = player == null ? game.getCurrentplayer().getTiles() : player.getTiles();
            if (rack != null)
                for (Character c : rack) {
                    Tile.paintTile(g, c, x, y);
                    x += size + offset;
                }
        }
    }
}