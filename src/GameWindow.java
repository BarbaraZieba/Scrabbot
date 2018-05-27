import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private BoardSwing boardSwing;

    public GameWindow() {
        super("Scrabbot");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardSwing = new BoardSwing();
        add(boardSwing, BorderLayout.CENTER);

        setVisible(true);
        pack();
        setResizable(false);

        //test dodawania literek na planszy
        boardSwing.setLetter('b', 6, 7);
        boardSwing.setLetter('a', 7, 7);
        boardSwing.setLetter('s', 8, 7);
        boardSwing.setLetter('i', 9, 7);
        boardSwing.setLetter('a', 10, 7);
        boardSwing.setBonus("slowna3", 0, 0);
        boardSwing.setBonus("slowna2", 1, 1);
        boardSwing.setBonus("literowa2", 6, 8);
        boardSwing.setBonus("literowa3", 5, 4);
    }
}
