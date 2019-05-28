import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;




public class Main {
    public static void main(String[] args)  {

        Window window = new Window();


    }
}


class Window extends JFrame {
    //private SpaceInvaders spaceInvaders = new SpaceInvaders ();




    public Window() {


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(480, 800);
        this.setResizable(false); /** Lock the size of the window*/
        this.setLocationRelativeTo(null);
        //add(spaceInvaders);
        createBufferStrategy(2);


    }

}





/*class SpaceInvaders extends JPanel implements ActionListener {
    private CardLayout cards = new CardLayout ();
    private Board board = new Board ();
    private Menu menu;
    private Game game = new Game();
    private static int direction = 1;


    {
        try {
            menu = new Menu ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public SpaceInvaders(){
        setLayout ( cards );
        add(menu,"1");
        add(game,"2");
        setIgnoreRepaint(true);



        menu.getPlayButton ().addActionListener ( this );


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource ()==menu.getPlayButton ()) {
            cards.show ( this, "2" );
        }
    }

    public static int getDirection() {
        return direction;
    }

    public static void setDirection(int direction) {
        SpaceInvaders.direction = direction;
    }
}*/





class Board extends JPanel{
    private JLabel score = new JLabel ( "SCORE : " );
    private JLabel points = new JLabel ( "5" );
    private JLabelWithIcon life = new JLabelWithIcon("life.png");


    public Board(){
        setBackground ( Color.yellow);

        score.setFont ( new Font( "Showcard Gothic", Font.BOLD,20));
        add(score);

        points.setFont ( new Font( "Showcard Gothic", Font.BOLD,20));
        add(points);

        add(life);


    }
}






class Menu extends JPanel implements ActionListener {
    private JLabel nameSpace = new JLabel ( "SPACE" );
    private JLabel nameInvaders = new JLabel ( "INVADERS" );
    private ButtonWithIcon playbutton = new ButtonWithIcon ( "startbutton.png" );
    private Font titrefont = new Font ( "Showcard Gothic", Font.BOLD,90 );

    public Menu() throws IOException {
        setBackground ( Color.BLACK );
        add(nameSpace);
        add(nameInvaders);
        add(playbutton);

        nameSpace.setFont ( titrefont );
        nameInvaders.setFont ( titrefont );
        nameSpace.setForeground ( Color.green );
        nameInvaders.setForeground ( Color.WHITE );


    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    public ButtonWithIcon getPlayButton(){
        return playbutton;
    }
}















class ButtonWithIcon extends JButton{

    public ButtonWithIcon(String icon) throws IOException {
        Image img = ImageIO.read(new File (icon));
        ImageIcon ii = new ImageIcon(img);
        setIcon ( ii );
        setContentAreaFilled(false);
        setBorderPainted(false);

    }
}

class JLabelWithIcon extends JLabel{
    public JLabelWithIcon(String icon) {
        ImageIcon ii = new ImageIcon(icon);
        Image img = ii.getImage();


        setIcon(ii);
    }
}
