import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameWindow extends JFrame {

    private BoardPanel boardSwing;
    private PlayerPanel playerPanel;
    private HistoryPanel historyPanel;
    private RackPanel rackPanel;

    public GameWindow(ArrayList<Player> players) {
        super("Scrabbot");
        Game game = new Game(this, players);
        MoveDialog.setGame(game);
        ExchangeDialog.setGame(game);
        NewLettersDialog.setGame(game);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardSwing = new BoardPanel(game);
        add(boardSwing, BorderLayout.CENTER);

        playerPanel = new PlayerPanel(game);
        add(playerPanel, BorderLayout.WEST);

        historyPanel = new HistoryPanel(game);
        add(historyPanel, BorderLayout.EAST);

        rackPanel = new RackPanel(game);
        add(rackPanel, BorderLayout.SOUTH);

        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        rackPanel.getTilePanel().setShouldPaintTiles(false);
        game.callNextPlayer();
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

    public RackPanel getRackPanel() {
        return rackPanel;
    }
}
