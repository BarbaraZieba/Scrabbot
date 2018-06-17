import javax.swing.*;
import java.awt.*;

public class HistoryPanel extends JPanel {
    private DefaultListModel<Move> listModel;

    public HistoryPanel(Game game) {
        JList<Move> historyList = new JList<>();
        listModel = new DefaultListModel<>();

        historyList.setModel(listModel);
        historyList.setBackground(new Color(0, 50, 0));
        historyList.setForeground(new Color(220, 200, 100));

        JButton revertButton = new JButton("Revert last move");
        revertButton.addActionListener(e -> game.deletelastmove());

        setLayout(new BorderLayout());
        add(new JLabel("Move history"), BorderLayout.NORTH);
        add(historyList, BorderLayout.CENTER);
        add(revertButton, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(150, 600));
    }

    public DefaultListModel<Move> getListModel() {
        return listModel;
    }
}
