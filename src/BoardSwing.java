import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardSwing extends JPanel {
    private CellSwing[][] tiles;

    public BoardSwing(Game game) throws IOException {
        tiles = new CellSwing[15][15];
        Board board = game.getBoard();
        setBackground(new Color(125, 125, 125));
        setLayout(new GridLayout(15, 15, 3, 3));
        for (int j = 0; j < 15; j++)
            for (int i = 0; i < 15; i++) {
                tiles[i][j] = new CellSwing(i, j, board);
                add(tiles[i][j]);
            }
        setPreferredSize(new Dimension(600, 600));
        for (Bonus b : board.bonuses) {
            tiles[b.row][b.column].setBonus(b.type);
        }
    }
}
