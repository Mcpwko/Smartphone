import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    private Player player = new Player ("images\\player.png");



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
        player.setBounds ( 192,620,95,95 );
        game.add(player);

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
        int key = e.getKeyCode();

        if(key==KeyEvent.VK_LEFT){
            player.setBounds(this.getX ()-5,620,95,95);
            game.repaint (  );
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

/*class LabelnWithIcon extends JButton{

    public LabelnWithIcon(String icon) {
        ImageIcon ii = new ImageIcon(icon);
        setIcon ( ii );
        setContentAreaFilled(false);
        setBorderPainted(false);

    }

    public LabelnWithIcon() {

    }
}*/

class Player extends JLabel implements KeyListener {

    public Player(String icon){
        ImageIcon ii = new ImageIcon(icon);
        setIcon ( ii );



    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key==KeyEvent.VK_LEFT){
            setBounds(this.getX ()-5,620,95,95);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
