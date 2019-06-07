import Contact.ContactPanel;
import PongGame.BoardPong;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


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
    //private Batterie batteryPercent = new Batterie();
    private CardLayout cardLayout = new CardLayout();
    private JPanelWithBackground center = new JPanelWithBackground("images//wallpaper.jpg");
    private JPanel panelcont = new JPanel();
    private Gallery galleryApp = new Gallery();
    private Settings settingsApp = new Settings();
    private ContactPanel contactPanel = new ContactPanel ();

    private BoardPong pongGame = new BoardPong();
    private SpaceInvaders spaceInvadersGame = new SpaceInvaders ();
    private ButtonWithIcon contact = new ButtonWithIcon("images//contact.png");
    private ButtonWithIcon home = new ButtonWithIcon("images//home.png");
    private ButtonWithIcon screenshot = new ButtonWithIcon ( "images//screenshot.png");
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
        panelcont.add( contactPanel,"3");
        panelcont.add(settingsApp,"4");
        panelcont.add( spaceInvadersGame,"5");
        panelcont.add(pongGame,"6");
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
        pong.addActionListener(this);
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
        //northeast.add(batteryPercent,BorderLayout.CENTER);
        //batteryPercent.setForeground(Color.WHITE);
        clock.setForeground(Color.white);
        /**
         * Insert the south panel
         */
        this.add(south, BorderLayout.SOUTH);
        south.setLayout(new BorderLayout ());
        //south.add(southcenter,BorderLayout.CENTER);
        //southcenter.setBackground(Color.BLACK);
        south.setBackground(Color.BLACK);

        south.add(screenshot,BorderLayout.WEST);
        south.add ( home ,BorderLayout.CENTER);
        south.add(previous,BorderLayout.EAST);
        //southcenter.add(home); /**add the button home to the south panel*/
        //southcenter.add(previous);
        screenshot.addActionListener ( new ScreenshotListener () );
        home.addActionListener(this);
        previous.addActionListener(new PreviousListener());
        //south.setBackground(Color.WHITE); /**The color of the south panel*/

    }

    public ButtonWithIcon getPrevious() {
        return previous;
    }


    public ButtonWithIcon getHome() {
        return home;
    }



    class PreviousListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int temp = 0;

            if(galleryApp.isVisible()) {

                Component c[] = galleryApp.getPanelcont().getComponents();
                int i = 0;
                int j = c.length;
                while (i < j) {
                    if (c[i].isVisible()) {
                        temp = i + 1;
                        System.out.println("" + (i + 1));
                        break;
                    } else
                        i++;
                }
                if (temp == 2) {
                    galleryApp.getCardLayout().show(galleryApp.getPanelcont(), "1");
                } else {
                    cardLayout.first(panelcont);
                }
            }else{



                if(contactPanel.isVisible()){


                    Component c[] = contactPanel.getPanelcontent().getComponents();
                    int i = 0;
                    int j = c.length;
                    while (i < j) {
                        if (c[i].isVisible()) {
                            temp = i + 1;
                            System.out.println("" + (i + 1));
                            break;
                        } else
                            i++;
                    }

                    if(temp==2 || temp==3){
                        contactPanel.getCards().show(contactPanel.getPanelcontent(),"1");
                        contactPanel.resetNewContact();
                        contactPanel.getInformation ().removeAll ();
                        //contactPanel.getInformation ().revalidate ();
                    } else{
                        cardLayout.first(panelcont);
                    }
                }else{



                    if(pongGame.isVisible ()){

                        cardLayout.first ( panelcont );

                    }
                }
            }
        }
    }

    class ScreenshotListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                screenshot();
            } catch (AWTException e1) {
                e1.printStackTrace ();
            } catch (IOException e1) {
                e1.printStackTrace ();
            }
        }
    }



    public void screenshot() throws AWTException, IOException {
        /*try{
            Thread.sleep(1000);
            Toolkit tk = Toolkit.getDefaultToolkit(); //Toolkit class                         returns the default toolkit
            Dimension d = tk.getScreenSize();

//Dimension class object stores width & height of the toolkit screen
// toolkit.getScreenSize() determines the size of the screen

            Rectangle rec = new Rectangle(520, 7,494,755);
//Creates a Rectangle with screen dimensions,

            Robot ro = new Robot(); //to capture the screen image
            BufferedImage img = ro.createScreenCapture(rec);


            int nbImages = galleryApp.getPanelPictures ().getComponentCount ();
            File f = new File("Screenshot"); // File class is used to write the above generated buffered image to a file
            ImageIO.write(img, "1.jpg", f);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }*/
        /** METHODE NUMERO 2
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(image, "png", new File("Screenshot/screenshot.png"));*/

        Rectangle rect = this.getBounds (  );

        int nombreImages = galleryApp.getPanelPictures ().getComponentCount ();
        String format = "png";
        String fileName = nombreImages+"." + format;
        String fileNewName = nombreImages+"." + "jpg";

        BufferedImage captureImage = new BufferedImage ( rect.width,rect.height,BufferedImage.TYPE_INT_ARGB );
        this.paint(captureImage.getGraphics ());
        ImageIO.write(captureImage,format,new File("Gallery/" + fileNewName));
        System.out.println("ScreenSHOT EFFECTUE !" + fileName);
        galleryApp.addNewScreenshot ( fileNewName );



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
                    /*if(obj == previous){
                        cardLayout.previous(panelcont);
                    }
                    else{*/
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




