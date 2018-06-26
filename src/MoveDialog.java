import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoveDialog extends JFrame {
    private int i;
    private int j;
    private Game game;
    private static MoveDialog instance = null;
    private JTextField jTextField;
    private JLabel jLabel;

    /**
     * Has to be called before using this class.
     */
    public static void setGame(Game game) {
        if (instance == null)
            instance = new MoveDialog();
        instance.game = game;
    }

    private MoveDialog() {
        super();

        JButton jButton = new JButton("OK");
        JRadioButton horizontalButton = new JRadioButton("horizontal");
        JRadioButton verticalButton = new JRadioButton("vertical");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(horizontalButton);
        buttonGroup.add(verticalButton);
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(horizontalButton);
        radioPanel.add(verticalButton);
        horizontalButton.setSelected(true);

        jTextField = new JTextField();
        jTextField.setPreferredSize(new Dimension(150, 20));
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = jTextField.getText().toUpperCase();
                switch (game.currentPlayerMove(word, i, j, verticalButton.isSelected())) {
                    case Game.SUCCESS:
                        setVisible(false);
                        break;
                    case Game.INVALID_WORD:
                        JOptionPane.showMessageDialog(instance, word + " is not in the dictionary.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    case Game.MISPLACED_WORD:
                        JOptionPane.showMessageDialog(instance, word + " can't be placed here.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    case Game.WRONG_TILES:
                        JOptionPane.showMessageDialog(instance, word + " can't be constructed using your tiles.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        };
        jTextField.addActionListener(actionListener);
        jButton.addActionListener(actionListener);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));

        setLayout(new FlowLayout());
        jLabel = new JLabel();
        add(jLabel);
        add(jTextField);
        add(radioPanel);
        add(jButton);
        add(cancelButton);
        setSize(300, 150);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public static void call(int i, int j, Component caller) {
        if (instance.game.checkGameOver())
            return;
        instance.setLocationRelativeTo(caller);
        instance.i = i;
        instance.j = j;
        instance.setVisible(true);
        instance.jTextField.setText("");
        instance.jLabel.setText("Word to be placed at column " + i + ", row " + j + ":");
        instance.jTextField.requestFocus();
    }
}
