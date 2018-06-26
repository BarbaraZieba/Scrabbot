import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private CellButton[][] tiles;

    public BoardPanel(Game game){
        tiles = new CellButton[15][15];
        setBackground(new Color(125, 125, 125));
        setLayout(new GridLayout(15, 15, 3, 3));
        for (int j = 0; j < 15; j++)
            for (int i = 0; i < 15; i++) {
                tiles[i][j] = new CellButton(i, j, game, game.getBonusAt(i, j));
                add(tiles[i][j]);
            }
        setPreferredSize(new Dimension(600, 600));
    }
}
