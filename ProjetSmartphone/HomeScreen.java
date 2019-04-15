import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.awt.event.*;
import java.text.*;


public class HomeScreen extends JFrame {
    private Panel pan = new Panel();
    private JPanel south = new JPanel();
    private JPanel north = new JPanel();
    private Button home = new Button();
    private Time clock = new Time();

    public HomeScreen(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400, 600);
        setResizable(false); // Lock the size of the window
        this.setContentPane(pan);
        //Main panel
        pan.setLayout(new BorderLayout());
        //Insert the north panel
        pan.add(north, BorderLayout.NORTH);
        north.add(clock,BorderLayout.CENTER);
        clock.setForeground(Color.white);
        north.setBackground(Color.BLACK);
        //Insert the south panel
        pan.add(south, BorderLayout.SOUTH);
        south.add(home, BorderLayout.CENTER); // add the button home to the south panel
        south.setBackground(Color.BLACK); // The color of the south panel

        setLocationRelativeTo(null);
    }
}

class Panel extends JPanel{
    public Panel(){

    }
}


class Button extends JButton{
    public Button(){

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

class Battery extends JLabel{

    Battery(){

    }
}