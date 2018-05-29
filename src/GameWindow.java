import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameWindow extends JFrame {

    private BoardSwing boardSwing;
    private final int height;
    private final int width;
    public GameWindow() {
        super("Scrabbot");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardSwing = new BoardSwing();
        add(boardSwing, BorderLayout.CENTER);

        setVisible(true);
        pack();

        height = getHeight();
        width = getWidth();

        addComponentListener(new ComponentAdapter() {
           //this doesnt really works
            public void componentResized(ComponentEvent arg0) {
                Rectangle b = arg0.getComponent().getBounds();
                arg0.getComponent().setBounds(b.x, b.y, b.width, (b.width*height)/width);

            }
        });
        setResizable(false);

        //test dodawania bonusow na planszy
       // boardSwing.setBonus(CellSwing.TripleWord, 0, 0);
       // boardSwing.setBonus(CellSwing.DoubleWord, 1, 1);
       // boardSwing.setBonus(CellSwing.DoubleLetter, 6, 8);
       // boardSwing.setBonus(CellSwing.TripleLetter, 5, 4);
       // boardSwing.setBonus(CellSwing.Center, 7, 7);
    }
}
