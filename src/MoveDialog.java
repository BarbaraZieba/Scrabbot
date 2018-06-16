import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MoveDialog extends JFrame {
    private int i;
    private int j;
    private Game game;
    private static MoveDialog instance = null;
    private JTextField jTextField = new JTextField();
    private JLabel jLabel = new JLabel();

    /**
     * Has to be called before using this class.
     */
    public static void setGame(Game game) {
        if (instance == null)
            instance = new MoveDialog();
        instance.game = game;
    }

    private MoveDialog() throws HeadlessException {
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

        jTextField.setPreferredSize(new Dimension(100, 20));
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.currentPlayerMove(jTextField.getText(), i, j, verticalButton.isSelected()))
                    setVisible(false);
            }
        };
        jTextField.addActionListener(actionListener);
        jButton.addActionListener(actionListener);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));

        setLayout(new FlowLayout());
        add(jLabel);
        add(jTextField);
        add(radioPanel);
        add(jButton);
        add(cancelButton);
        setSize(250, 150);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public static void call(int i, int j, Component caller) {
        instance.setLocationRelativeTo(caller);
        instance.i = i;
        instance.j = j;
        instance.setVisible(true);
        instance.jTextField.setText("");
        instance.jLabel.setText("Word to be placed at column " + i + ", row " + j + ":");
        instance.jTextField.requestFocus();
    }

    public static MoveDialog getInstance() {
        if (instance == null)
            instance = new MoveDialog();
        return instance;
    }
}
