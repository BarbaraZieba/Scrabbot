import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;

public class GameWindow extends JFrame {

    private BoardSwing boardSwing;
    private final int height;
    private final int width;
    public Game game;
    public GameWindow(ArrayList<Player> players) throws IOException {
        super("Scrabbot");
        this.game = new Game(players);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardSwing = new BoardSwing();
        add(boardSwing, BorderLayout.CENTER);

        add(new PlayerPanel(game, players.toArray()), BorderLayout.WEST);

        add(new HistoryPanel(game), BorderLayout.EAST);

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
        setResizable(true);
    }
}
