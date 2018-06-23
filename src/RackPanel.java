import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class RackPanel extends JPanel {
    private class TilePanel extends JPanel {
        Game game;

        public TilePanel(Game game) {
            this.game = game;
            setPreferredSize(new Dimension(304, 50));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(0, 50, 0));
            g.fillRect(0, 0, getWidth(), getHeight());
            int offset = 10;
            int size = 30;
            int x = offset;
            int y = offset;
            g.setFont(new Font("Arial", Font.BOLD, 16));
            ArrayList<Character> rack = game.getCurrentplayer().getTiles();
            if (rack == null)
                return;
            for (Character c : rack) {
                g.setColor(new Color(220, 200, 100));
                g.fillRect(x, y, 32, 32);
                g.setColor(Color.BLACK);
                g.drawString(c.toString(), x + 13, y + 21);
                x += 32 + offset;
            }
        }
    }

    private Game game;

    public RackPanel(Game game) {
        super();
        this.game = game;
        setLayout(new FlowLayout());
        TilePanel tilePanel = new TilePanel(game);
        add(tilePanel);
        JButton jButton = new JButton("Exchange Tiles");
        add(jButton);
        jButton.addActionListener(e -> ExchangeDialog.call());
        setBackground(new Color(165, 165, 165));
    }


}