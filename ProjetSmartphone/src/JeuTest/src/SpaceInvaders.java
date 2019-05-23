import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class SpaceInvaders extends JFrame {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    BufferStrategy strategy;
    Container c = getContentPane();
    //static JTextArea names = new JTextArea(10, 50);
    //static JList<String> userList;

    private static int direction = 1;

    private static final String TITLE = "Space Invaders";

    public SpaceInvaders() {
        super(TITLE);
        final JPanel startPanel = new JPanel();
        startPanel.setBackground(Color.BLACK);
        JButton start = new JButton("Start");
        startPanel.add(start);
        this.add(startPanel);

        ImageIcon pic = new ImageIcon("alien.png");
        Image img = pic.getImage();
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setIgnoreRepaint(true);
        createBufferStrategy(2);

        start.addActionListener(new ActionListener () {

            @Override
            public void actionPerformed(ActionEvent e) {
                c.removeAll();
                JFrame frame = SpaceInvaders.this;
                c.add(Game.getGameCanvas(false));
                Game.getGameCanvas(false).grabFocus();
                frame.setContentPane(c);
                frame.repaint();
            }
        });

        strategy = getBufferStrategy();
        strategy.show();

        setIconImage(img);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new SpaceInvaders();
            }
        });
    }


}


