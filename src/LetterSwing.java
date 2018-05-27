import javax.swing.*;
import java.awt.*;

public class LetterSwing extends JPanel {

    private int type;
    private String message;
    private String points;

    public LetterSwing() {
        super();
        this.type = 0;
    }

    public void setLetter(Character letter, int points) {
        this.type = 1;
        this.message = letter.toString();
        this.points = String.valueOf(points);
    }

    public void setBonus(String bonus) {
        this.type = 2;
        points = bonus;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getSize().width;
        int height = getSize().height;
        if (type == 0) {
            g.setColor(new Color(0, 50, 0));
            g.fillRect(0, 0, width, height);
        } else if (type == 1) {
            int fontsize = 20;
            g.setColor(new Color(220, 200, 100));
            g.fillRect(0, 0, width, height);

            g.setColor(Color.BLACK);

            g.setFont(new Font("TimesRoman", Font.PLAIN, fontsize));
            g.drawString(this.message, width / 2, height / 2);

            g.setFont(new Font("TimesRoman", Font.PLAIN, fontsize/2));
            g.drawString(this.points, width - fontsize / 2, height - fontsize / 4);
        } else if (type == 2) {
            if (points.equals("slowna2")) {
                g.setColor(new Color(225, 120, 120));
                this.message = "DWS";
            }
            if (points.equals("slowna3")) {
                g.setColor(new Color(225, 50, 50));
                this.message = "TWS";
            }
            if (points.equals("literowa2")) {
                g.setColor(new Color(100, 100, 225));
                this.message = "DLS";
            }
            if (points.equals("literowa3")) {
                g.setColor(new Color(50, 50, 225));
                this.message = "TLS";
            }
            g.fillRect(0, 0, width, height);
            int fontsize = 15;
            g.setFont(new Font("TimesRoman", Font.PLAIN, fontsize));
            g.setColor(Color.BLACK);
            g.drawString(this.message, 0, height / 2);
        }
    }
}
