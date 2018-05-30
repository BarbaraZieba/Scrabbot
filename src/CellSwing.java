import javax.swing.*;
import java.awt.*;

public class CellSwing extends JButton {
    public static final int Empty = 0;
    public static final int DoubleWord = 1;
    public static final int TripleWord = 2;
    public static final int DoubleLetter = 3;
    public static final int TripleLetter = 4;
    public static final int Center = 5;
    private int type;
    private Board board;
    private int i, j;

    public CellSwing(int i, int j, Board board) {
        this(i, j);
        this.board = board;
    }

    public CellSwing(int i, int j) {
        super();
        this.type = Empty;
        this.i = i;
        this.j = j;
        addActionListener(e -> {
            String word = JOptionPane.showInputDialog("Wprowadź słowo: ");
            board.placeWord(word, i, j, true);
        });
    }

    public void setBonus(int type) {
        this.type = type;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getSize().width;
        int height = getSize().height;
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
        if (c == null) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, width / 3));

            g.setColor(Color.BLACK);
            g.drawString(message, width / 6, height * 2 / 3);
        } else {
            g.setColor(new Color(220, 200, 100));
            int offset = 3;
            //g.fillRoundRect(offset, offset, width - 2 * offset, height - 2 * offset, 10*offset, 10*offset);
            g.fillRect(offset, offset, width - 2 * offset, height - 2 * offset);

            g.setFont(new Font("TimesRoman", Font.BOLD, width / 2));
            message = c.toString();
            g.setColor(Color.BLACK);
            g.drawString(message, width * 3 / 7, height * 2 / 3);
        }
    }
}

