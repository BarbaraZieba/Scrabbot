import javax.swing.*;
import java.awt.*;

public class BoardSwing extends JPanel {
    private LetterSwing[][] tiles;

    public BoardSwing() {
        tiles = new LetterSwing[15][15];
        setLayout(new GridLayout(15, 15, 1, 1));
        for (int j = 0; j < 15; j++)
            for (int i = 0; i < 15; i++) {
                tiles[i][j] = new LetterSwing();
                add(tiles[i][j]);
            }
        setPreferredSize(new Dimension(600, 600));
    }

    public void setLetter(char letter, int row, int column) {
        tiles[row][column].setLetter(letter, 2);
    }

    public void setBonus(String bonus, int row, int column) {
        tiles[row][column].setBonus(bonus);
    }
}
