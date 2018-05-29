import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardSwing extends JPanel {
    private CellSwing[][] tiles;
    public Board board;

    public BoardSwing() {
        tiles = new CellSwing[15][15];
        board = new Board(new ArrayList<Player>(List.of(new Player("basia"))));
        setBackground(new Color(125, 125, 125));
        setLayout(new GridLayout(15, 15, 3, 3));
        for (int j = 0; j < 15; j++)
            for (int i = 0; i < 15; i++) {
                tiles[i][j] = new CellSwing(i,j, board);
                add(tiles[i][j]);
            }
        setPreferredSize(new Dimension(600, 600));
        for (Bonus b: board.bonuses) {
            tiles[b.row][b.column].setBonus(b.ordinalnumber);
        }
        //test wyswietlania literek
        board.board[0][0] = 'B';
        board.board[0][1] = 'A';
        board.board[0][2] = 'S';
        board.board[0][3] = 'I';
        board.board[0][4] = 'A';
    }
}
