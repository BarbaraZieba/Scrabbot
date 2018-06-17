import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    class PlayerRenderer extends JPanel {
        JLabel playerName;
        JLabel playerScore;
        Player player;
        Game game;

        public PlayerRenderer(Game game, Object player) {
            if (player instanceof Player) {
                this.player = (Player) player;
                this.game = game;
                playerName = new JLabel(this.player.getName());
                playerScore = new JLabel("Score: " + this.player.getScore());
                setLayout(new BorderLayout());
                add(playerName, BorderLayout.CENTER);
                add(playerScore, BorderLayout.SOUTH);
                setPreferredSize(new Dimension(150, 50));
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (game.getCurrentplayer() == player)
                setBackground(new Color(135, 190, 75));
            else setBackground(new Color(189, 189, 189));
            playerScore.setText(player.getScore().toString());
            super.paintComponent(g);
        }
    }

    public PlayerPanel(Game game) {
        setLayout(new FlowLayout());
        for (Player player :  game.getPlayers())
            add(new PlayerRenderer(game, player));
        setBackground(new Color(0, 50, 0));
        setPreferredSize(new Dimension(150, 600));
    }
}
