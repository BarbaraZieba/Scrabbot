import javax.swing.*;
import java.awt.*;

public class CellButton extends JButton {
    private Bonus type;
    private Board board;
    private int i, j;

    public CellButton(int i, int j, Board board, Bonus type) {
        super();
        this.type = type;
        this.board = board;
        this.i = i;
        this.j = j;
        addActionListener(e -> MoveDialog.call(i, j, this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        String message = "";
        switch (type) {
            case DoubleWord:
                g.setColor(new Color(225, 120, 120));
                message = "DWS";
                break;
            case TripleWord:
                g.setColor(new Color(225, 50, 50));
                message = "TWS";
                break;
            case DoubleLetter:
                g.setColor(new Color(117, 181, 225));
                message = "DLS";
                break;
            case TripleLetter:
                g.setColor(new Color(39, 113, 191));
                message = "TLS";
                break;
            case Center:
                g.setColor(new Color(225, 120, 120));
                break;
            default:
                g.setColor(new Color(0, 50, 0));
                break;
        }
        g.fillRect(0, 0, width, height);
        Character c = board.board[i][j];
        if (c != null) c = Character.toUpperCase(c);
        if (c == null) {
            g.setFont(new Font("Arial", Font.PLAIN, width / 3));
            g.setColor(Color.BLACK);
            g.drawString(message, width / 6, height * 2 / 3);
        } else {
            Tile.paintTile(g, c, 3, 3);
        }
    }
}

