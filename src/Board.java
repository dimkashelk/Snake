import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {
    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private int apple_x;
    private int apple_y;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean in_game = true;
    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    private Board() {
        addKeyListener(new TAdapter());
        setBackground(Color.WHITE);
        ImageIcon iid = new ImageIcon(this.getClass().getResource("red.png"));
        ball = iid.getImage();
        ImageIcon iia = new ImageIcon(this.getClass().getResource("red.png"));
        apple = iia.getImage();
        ImageIcon iih = new ImageIcon(this.getClass().getResource("green.png"));
        head = iih.getImage();
        setFocusable(true);
        initGame();
    }


    private void initGame() {
        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        locateApple();
        timer = new Timer(DELAY, this);
        timer.start();

    }

    public void paint(Graphics g) {
        super.paint(g);
        if (in_game) {
            g.drawImage(apple, apple_x, apple_y, this);
            for (int i = 0; i < dots; i++) {
                if (i == 0)
                    g.drawImage(head, x[i], y[i], this);
                else
                    g.drawImage(ball, x[i], y[i], this);
            }
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        } else {
            gameOver(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
