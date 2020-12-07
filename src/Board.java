import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

    Board() {
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
        if (in_game) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

    public void gameOver(Graphics g) {
        String msg = new String("Game Over!");
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);
        g.setColor(Color.BLACK);
        g.setFont(small);
        g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2, HEIGHT / 2);
    }

    public void checkApple() {
        if (x[0] == apple_x && y[0] == apple_y) {
            dots++;
            locateApple();
        }
    }

    public void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[z - 1];
            y[z] = y[z -1];
        }
        if (left)
            x[0] -= DOT_SIZE;
        else if (right)
            x[0] += DOT_SIZE;
        else if (up)
            y[0] -= DOT_SIZE;
        else if (down)
            y[0] += DOT_SIZE;
    }

    public void checkCollision() {
        for (int z = dots; z > 3; z--) {
            if (x[0] == x[z] && y[0] == y[z])
                in_game = false;
        }
        if (y[0] > HEIGHT || y[0] < 0 || x[0] > WIDTH || x[0] < 0)
            in_game = false;
    }

    public void locateApple() {
        int r = (int) (Math.random() * RAND_POS);
        apple_x = r * DOT_SIZE;
        r = (int) (Math.random() * RAND_POS);
        apple_y = r * DOT_SIZE;
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_UP && !down) {
                up = true;
                left = false;
                right = false;
            } else if (key == KeyEvent.VK_DOWN && !up) {
                down = true;
                left = false;
                right = false;
            } else if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            } else if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }
        }
    }
}