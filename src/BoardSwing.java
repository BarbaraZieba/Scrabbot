import javax.swing.*;
import java.awt.*;

public class BoardSwing extends JPanel {
    private CellSwing[][] tiles;

    public BoardSwing() {
        tiles = new CellSwing[15][15];
        setBackground(new Color(125, 125, 125));
        setLayout(new GridLayout(15, 15, 3, 3));
        for (int j = 0; j < 15; j++)
            for (int i = 0; i < 15; i++) {
                tiles[i][j] = new CellSwing(i,j);
                add(tiles[i][j]);
            }
        setPreferredSize(new Dimension(600, 600));
    }

    public void setBonus(int bonusType, int row, int column) {
        tiles[row][column].setBonus(bonusType);
    }
}
