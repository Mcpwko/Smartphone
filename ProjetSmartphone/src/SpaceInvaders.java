import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class SpaceInvaders extends JPanel implements ActionListener,KeyListener {
    private JPanelWithBackground game = new JPanelWithBackground ( "images\\spacewallpaper.jpg" );
    private JPanel menu = new JPanel (  );
    private CardLayout cards = new CardLayout (  );
    private JLabel nameSpace = new JLabel ( "SPACE" );
    private JLabel nameInvaders = new JLabel ( "INVADERS" );
    private ButtonWithIcon playbutton = new ButtonWithIcon ( "images\\startbutton.png" );
    private JLabel score = new JLabel ( "SCORE : " );
    private Timer timer;
    //private Player player;
    private final int DELAY = 10;



    public SpaceInvaders () throws IOException {
        setLayout (cards);
        add(menu,"1");
        add(game,"2");

        menu.setBackground ( Color.BLACK );
        menu.setLayout ( new FlowLayout(FlowLayout.CENTER) );
        menu.add(nameSpace);
        nameSpace.setFont(new Font("Showcard Gothic", Font.BOLD,90));
        nameSpace.setForeground ( Color.green );
        menu.add(nameInvaders);
        nameInvaders.setFont(new Font ( "Showcard Gothic", Font.BOLD,90));
        nameInvaders.setForeground ( Color.WHITE );
        menu.add(playbutton);
        playbutton.setBorderPainted ( true );
        playbutton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
        playbutton.addActionListener ( this );

        score.setFont(new Font("Showcard Gothic", Font.BOLD,20));
        game.setLayout ( null );
        score.setBounds ( 0,5,100,30 );
        score.setForeground ( Color.WHITE );
        game.add(score);


    }
    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File ("menuSound.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource ();

        if(obj==playbutton){
            playSound ();
            cards.show ( this,"2" );

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

/*class Player {

    private int dx;
    private int dy;
    private int x = 40;
    private int y = 60;
    private int w;
    private int h;
    private Image image;

    public Player() {

        loadImage();
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon("images/player.png");
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    public void move() {

        x += dx;
        y += dy;
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }

    public int getWidth() {

        return w;
    }

    public int getHeight() {

        return h;
    }

    public Image getImage() {

        return image;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}*/

class Board extends JPanel implements ActionListener {

    private Timer timer;
    //private Player player;
    private final int DELAY = 10;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        //player = new Player();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        //g2d.drawImage( player.getImage(), player.getX(),
          //      player.getY(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        step();
    }

    private void step() {

       // player.move();

       // repaint( player.getX()-1, player.getY()-1,
        //        player.getWidth()+2, player.getHeight()+2);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
         //   player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
         //   player.keyPressed(e);
        }
    }
}


class MovingSpriteEx extends JFrame {

    public MovingSpriteEx() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setTitle("Moving sprite");
        setSize(400, 300);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            MovingSpriteEx ex = new MovingSpriteEx();
            ex.setVisible(true);
        });
    }
}
