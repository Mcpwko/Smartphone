import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Panel for the background
 */
public class TemplateBoard extends JPanel implements ActionListener{
    private JPanel south = new JPanel();
    private JPanel north = new JPanel();
    private JPanel southcenter = new JPanel();
    private JPanel northeast = new JPanel();
    private Button home = new Button();
    private JLabel reseau = new JLabel ("WIFI");
    private Battery1 battery = new Battery1();
    private Time clock = new Time();
    private Batterie batteryPercent = new Batterie();
    private CardLayout cardLayout = new CardLayout();
    private JPanel center = new JPanel();
    private JPanel panelcont = new JPanel();
    private Button gallery = new Button("nom");


    public TemplateBoard(){
        this.setLayout(new BorderLayout());
        /**
         * Insert the north panel
         */
        this.add(north, BorderLayout.NORTH);
        this.setBackground(Color.BLACK);
        panelcont.setLayout(cardLayout);
        add(panelcont,BorderLayout.CENTER);
        panelcont.add(center,"1");
        center.setBackground(Color.WHITE);
        center.add(gallery);
        gallery.setBorderPainted(false);
        gallery.setBackground ( Color.white );
        north.setLayout (new BorderLayout());
        north.setBackground(Color.gray);
        north.add(reseau,BorderLayout.WEST);
        reseau.setForeground(Color.WHITE);
        northeast.setLayout(new BorderLayout());
        north.add(northeast,BorderLayout.EAST);
        northeast.setBackground(Color.gray);
        north.add(clock,BorderLayout.CENTER);
        clock.setHorizontalAlignment(JLabel.CENTER);
        northeast.add(battery,BorderLayout.EAST);
        northeast.add(batteryPercent,BorderLayout.CENTER);
        batteryPercent.setForeground(Color.WHITE);
        clock.setForeground(Color.white);
        /**
         * Insert the south panel
         */
        this.add(south, BorderLayout.SOUTH);
        south.setLayout(new BorderLayout());
        south.add(southcenter,BorderLayout.CENTER);
        southcenter.setBackground(Color.BLACK);
        home.setBackground(Color.BLACK);
        home.setBorderPainted(false);
        south.setBackground(Color.BLACK);
        southcenter.add(home, BorderLayout.CENTER); /**add the button home to the south panel*/
        home.addActionListener(this);
        south.setBackground(Color.WHITE); /**The color of the south panel*/

    }
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == home) {
            cardLayout.next(panelcont);
        }

    }

}

/**
 * Button home
 */
class Button extends JButton{
    private final String home = "home.png";
    private final String gallery = "gallery.png";

    public Button() {
        ImageIcon ii = new ImageIcon(home);
        setIcon ( ii );

    }

    public Button(String nom){
        ImageIcon ii = new ImageIcon(gallery);
        setIcon(ii);

    }

}


class Time extends JLabel implements ActionListener {
    private DateFormat Display = new SimpleDateFormat("HH:mm");
    private Timer Tick = new Timer(1000, this);

    public Time() {
        Tick.start();
    }

    public void actionPerformed(ActionEvent event)
    {
        setText(Display.format(new Date()));
    }
}

class Battery1 extends Batterie implements ActionListener{

    private final String batteryfull = "batteryfull.png";
    private final String batteryCharging = "batteryCharging.png";
    private final String battery80 = "battery80.png";
    private final String battery60 = "battery60.png";
    private final String battery40 = "battery40.png";
    private final String battery20 = "battery20.png";
    private Timer Tick = new Timer(1000, this);

    Battery1(){
        ImageIcon ii = new ImageIcon(batteryfull);
        setIcon ( ii );
        Tick.start();

    }
    public void actionPerformed(ActionEvent event) {

    }
}

class Batterie extends JLabel implements ActionListener {
    Battery.SYSTEM_POWER_STATUS bs = new Battery.SYSTEM_POWER_STATUS();
    int status = Battery.INSTANCE.GetSystemPowerStatus(bs);
    private Timer Tick = new Timer(1000, this);

    public Batterie(){
        Tick.start();
    }

    public void actionPerformed(ActionEvent event)
    {
        Battery.SYSTEM_POWER_STATUS bs = new Battery.SYSTEM_POWER_STATUS();
        int status = Battery.INSTANCE.GetSystemPowerStatus(bs);
        setText(bs.toString());
    }
}



