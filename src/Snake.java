import javax.swing.*;

class Snake extends JFrame {
    public Snake() {
        add(new Board());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Snake");
        setResizable(false);
    }

    public static void main(String[] args) {
        new Snake();
    }
}
