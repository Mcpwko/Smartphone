import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class HomeScreen extends JFrame {
    private Panel pan = new Panel();
    private JPanel south = new JPanel();
    private JPanel north = new JPanel();
    private Button home = new Button();
    private Battery1 battery = new Battery1();
    private Time clock = new Time();

    public HomeScreen(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400, 600);
        setResizable(false); /** Lock the size of the window*/
        this.setContentPane(pan);
        /**
        *Main panel
         */
        pan.setLayout(new BorderLayout());
        /**
         * Insert the north panel
         */
        pan.add(north, BorderLayout.NORTH);
        north.add(clock,BorderLayout.CENTER);
        north.add(battery,BorderLayout.EAST);
        clock.setForeground(Color.white);
        north.setBackground(Color.BLACK);
        /**
         * Insert the south panel
         */
        pan.add(south, BorderLayout.SOUTH);
        south.add(home, BorderLayout.CENTER); /**add the button home to the south panel*/
        south.setBackground(Color.WHITE); /**The color of the south panel*/

        setLocationRelativeTo(null);
    }
}

/**
 * Panel for the background
 */
class Panel extends JPanel{
    public Panel(){

    }
}

/**
 * Button home
 */
class Button extends JButton{
   private Image img;

    public Button() {
       // img = Toolkit.getDefaultToolkit().getImage("home.png");

    }
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(img, 100, 100, this);
    }

    }


class Time extends JLabel implements ActionListener {
    private DateFormat Display = new SimpleDateFormat("HH:mm");
    private Timer Tick = new Timer(1000, this);

    public Time()
    {
        Tick.start();
    }

    public void actionPerformed(ActionEvent event)
    {
        setText(Display.format(new Date()));
    }
}

class Battery1 extends JLabel{

    Battery1(){
        ImageIcon imageIcon = new ImageIcon( "batteryfull.png" );
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(5, 5,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
    }

}

