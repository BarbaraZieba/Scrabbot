import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;

public class GameWindow extends JFrame {

    private BoardSwing boardSwing;
    private PlayerPanel playerPanel;
    private HistoryPanel historyPanel;
    private RackPanel rackPanel;
    private final int height;
    private final int width;
    private Game game;

    public GameWindow(ArrayList<Player> players) throws IOException {
        super("Scrabbot");
        this.game = new Game(this, players);
        MoveDialog.setGame(game);
        ExchangeDialog.setGame(game);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardSwing = new BoardSwing(game);
        add(boardSwing, BorderLayout.CENTER);

        playerPanel = new PlayerPanel(game);
        add(playerPanel, BorderLayout.WEST);

        historyPanel = new HistoryPanel(game);
        add(historyPanel, BorderLayout.EAST);

        rackPanel = new RackPanel(game);
        add(rackPanel, BorderLayout.SOUTH);

        setVisible(true);
        setResizable(true);
        pack();

        // well at this point that's legacy code
        height = getHeight();
        width = getWidth();
        addComponentListener(new ComponentAdapter() {
            //this doesnt really works
            public void componentResized(ComponentEvent arg0) {
                Rectangle b = arg0.getComponent().getBounds();
                arg0.getComponent().setBounds(b.x, b.y, b.width, (b.width * height) / width);

            }
        });
    }

    public void repaintChildren() {
        boardSwing.repaint();
        playerPanel.repaint();
        historyPanel.repaint();
        rackPanel.repaint();
    }

    public HistoryPanel getHistoryPanel() {
        return historyPanel;
    }
}
