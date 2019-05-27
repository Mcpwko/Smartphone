import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import PongGame.*;

/**
 * Panel for the background
 */
public class HomeScreen extends JPanel implements ActionListener{
    private JPanel south = new JPanel();
    private JPanel north = new JPanel();
    private JPanel southcenter = new JPanel();
    private JPanel northeast = new JPanel();
    private JLabel reseau = new JLabel ("Salt");
    private Battery1 battery = new Battery1();
    private TimeLabel clock = new TimeLabel();
    private Batterie batteryPercent = new Batterie();
    private CardLayout cardLayout = new CardLayout();
    private JPanelWithBackground center = new JPanelWithBackground("images//wallpaper.jpg");
    private JPanel panelcont = new JPanel();
    private Gallery galleryApp = new Gallery();
    private Settings settingsApp = new Settings();
    private JPanel contactApp = new JPanel();

    public BoardPongGame pongGame = new BoardPongGame();
    private SpaceInvaders spaceInvadersGame = new SpaceInvaders ();
    private ButtonWithIcon contact = new ButtonWithIcon("images//contact.png");
    private ButtonWithIcon home = new ButtonWithIcon("images//home.png");
    private ButtonWithIcon previous = new ButtonWithIcon("images//previous.png");
    private ButtonWithIcon gallery = new ButtonWithIcon("images//gallery.png");
    private ButtonWithIcon pong = new ButtonWithIcon ( "images//pong.png" );
    private ButtonWithIcon spaceinvaders = new ButtonWithIcon ( "images//spaceinvaders.png" );
    private ButtonWithIcon shutdown = new ButtonWithIcon ( "images//shutdown.png" );
    private ButtonWithIcon settings = new ButtonWithIcon ( "images//settings.png" );
    private Font font = new Font("Arial", Font.BOLD,13);
    private JLabel shutdownText = new JLabel("Shutdown");
    private JLabel settingsText = new JLabel("Settings");
    private JLabel pongText = new JLabel("Pong Game");
    private JLabel spaceText = new JLabel("Space Invaders");
    private JLabel galleryText = new JLabel("Gallery");
    private JLabel contactText = new JLabel("Contact");



    public HomeScreen() throws IOException {
        this.setLayout(new BorderLayout());
        /**
         * Insert the north panel
         */
        this.add(north, BorderLayout.NORTH);
        this.setBackground(Color.BLACK);
        panelcont.setLayout(cardLayout);
        add(panelcont,BorderLayout.CENTER);
        panelcont.add(center,"1");
        panelcont.add(galleryApp,"2");
        panelcont.add(contactApp,"3");
        panelcont.add(settingsApp,"4");
        panelcont.add( spaceInvadersGame,"5");
        panelcont.add(pongGame,"6");
        //panelcont.add(ponggame,"6");
        center.setBackground(Color.BLACK);
        center.setLayout ( null );

        gallery.setBounds(20,30,95,95);
        center.add(gallery);
        gallery.addActionListener(this);
        galleryText.setBounds(42,115,50,20);
        galleryText.setFont(font);
        galleryText.setForeground(Color.white);
        center.add(galleryText);

        contact.setBounds(135,30,95,95);
        center.add(contact);
        contact.addActionListener(this);
        contactText.setBounds(156,115,55,20);
        contactText.setFont(font);
        contactText.setForeground(Color.WHITE);
        center.add(contactText);

        pong.setBounds ( 250,30,95,95 );
        center.add(pong);
        //pong.addActionListener(this);
        pongText.setBounds(260,115,95,20);
        pongText.setFont(font);
        pongText.setForeground(Color.WHITE);
        center.add(pongText);


        spaceinvaders.setBounds ( 365,30,95,95 );
        center.add(spaceinvaders);
        spaceinvaders.addActionListener ( this );
        spaceText.setBounds(367,115,105,20);
        spaceText.setFont(font);
        spaceText.setForeground(Color.WHITE);
        center.add(spaceText);

        shutdown.setBounds ( 360,600,95,95 );
        center.add(shutdown);
        shutdown.addActionListener(this);
        shutdownText.setBounds(374,692,70,20);
        shutdownText.setFont(font);
        shutdownText.setForeground(Color.WHITE);
        center.add(shutdownText);

        settings.setBounds ( 20,600,95,95 );
        center.add(settings);
        settings.addActionListener(this);
        settingsText.setBounds(38,692,55,20);
        settingsText.setFont(font);
        settingsText.setForeground(Color.WHITE);
        center.add(settingsText);

        north.setLayout (new BorderLayout());
        north.setBackground(Color.BLACK);
        north.setPreferredSize(new Dimension(480,30));
        north.add(reseau,BorderLayout.WEST);
        reseau.setForeground(Color.WHITE);
        northeast.setLayout(new BorderLayout());
        north.add(northeast,BorderLayout.EAST);
        northeast.setBackground(Color.BLACK);
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
        south.setBackground(Color.BLACK);

        southcenter.add(home); /**add the button home to the south panel*/
        southcenter.add(previous);
        home.addActionListener(this);
        previous.addActionListener(this);
        south.setBackground(Color.WHITE); /**The color of the south panel*/

    }
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == gallery) {
            cardLayout.show(panelcont,"2");
        }else {

            if (obj == home) {
                cardLayout.first(panelcont);
            }
            else{
                if(obj == contact){
                    cardLayout.show(panelcont,"3");
                }
                else{
                    if(obj == previous){
                        cardLayout.previous(panelcont);
                    }
                    else{
                        if(obj==shutdown){
                            System.exit(0);
                        }
                        else{
                            if(obj==settings){
                                cardLayout.show(panelcont,"4");
                            }
                            else{
                                if(obj==spaceinvaders){
                                    cardLayout.show ( panelcont,"5" );
                                }
                                else{
                                    if(obj==pong){
                                        cardLayout.show(panelcont,"6");
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }


    }

}

/**
 * Button avec une icône
 */
class ButtonWithIcon extends JButton{

    public ButtonWithIcon(String icon) throws IOException {
        Image img = ImageIO.read(new File (icon));
        ImageIcon ii = new ImageIcon(img);
        //ImageIcon ii = new ImageIcon(icon);
        setIcon ( ii );
        setContentAreaFilled(false);
        setBorderPainted(false);

    }
}


class TimeLabel extends JLabel implements ActionListener {
    private DateFormat Display = new SimpleDateFormat("HH:mm");
    private Timer Tick = new Timer(1000, this);

    public TimeLabel() {
        Tick.start();
    }

    public void actionPerformed(ActionEvent event)
    {
        setText(Display.format(new Date()));
    }
}

class DateLabel extends JLabel implements ActionListener{
    private DateFormat Display = new SimpleDateFormat("EEEE d MMMM yyyy");
    private Timer Tick = new Timer(1000,this);

    public DateLabel(){
        Tick.start();
    }

    public void actionPerformed(ActionEvent event){
        setText(Display.format(new Date()));
    }
}



class Battery1 extends Batterie implements ActionListener{

    private Timer Tick = new Timer(1000, this);
    private final String batteryfull = "images//batteryfull.png";
    private final String batteryCharging = "images//batteryCharging.png";
    private final String battery80 = "images//battery80.png";
    private final String battery60 = "images//battery60.png";
    private final String battery40 = "images//battery40.png";
    private final String battery20 = "images//battery20.png";

    Battery1(){
        Tick.start();
    }

    public void actionPerformed(ActionEvent event) {
        Kernel32.SYSTEM_POWER_STATUS bs = new Kernel32.SYSTEM_POWER_STATUS();
        int status = Kernel32.INSTANCE.GetSystemPowerStatus(bs);
        int statusAC = Integer.valueOf(bs.getACLineStatusString());

        if(statusAC==1) {
            ImageIcon ii = new ImageIcon(batteryCharging);
            setIcon(ii);
        }
        else {
            if (Integer.valueOf(bs.toString()) >= 80) {
                ImageIcon ii = new ImageIcon(batteryfull);
                setIcon(ii);
            } else {
                if (Integer.valueOf(bs.toString()) >= 60) {
                    ImageIcon ii = new ImageIcon(battery80);
                    setIcon(ii);
                } else {
                    if (Integer.valueOf(bs.toString()) >= 40) {
                        ImageIcon ii = new ImageIcon(battery60);
                        setIcon(ii);
                    } else {
                        if (Integer.valueOf(bs.toString()) >= 20) {
                            ImageIcon ii = new ImageIcon(battery40);
                            setIcon(ii);
                        } else {
                            if (Integer.valueOf(bs.toString()) < 20) {
                                ImageIcon ii = new ImageIcon(battery20);
                                setIcon(ii);
                            }
                        }
                    }
                }
            }
        }

    }
}

class Batterie extends JLabel implements ActionListener {
    private Timer Tick = new Timer(1000, this);

    public Batterie(){
        Tick.start();
    }

    public void actionPerformed(ActionEvent event)
    {
        Kernel32.SYSTEM_POWER_STATUS bs = new Kernel32.SYSTEM_POWER_STATUS();
        int status = Kernel32.INSTANCE.GetSystemPowerStatus(bs);
        setText(bs.toString()+" %");
    }
}




