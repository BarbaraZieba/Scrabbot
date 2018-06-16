import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class HistoryPanel extends JPanel {

    public HistoryPanel(Game game) {
        JList<Move> historyList = new JList<>();
        DefaultListModel<Move> model = new DefaultListModel<>();

        historyList.setModel(model);

        JButton randomButton = new JButton("Add move to history");
        randomButton.addActionListener(e -> {
            Random random = new Random();
            Move m = new Move(game.getCurrentplayer(), null, random.nextInt(100), "jakieś słowo");
            //these two lines are doing the job
            game.board.history.add(m);
            model.addElement(m);
        });

        setLayout(new BorderLayout());
        add(new JLabel("Move history"), BorderLayout.NORTH);
        add(historyList, BorderLayout.CENTER);
        add(randomButton, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(150, 600));
    }
}
