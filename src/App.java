import javax.swing.*;

public class App {
    public static void main(String[] args) {
        int windowWidth = 800;
        int windowHeight = 600;

        JFrame frame = new JFrame("Snake");
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}