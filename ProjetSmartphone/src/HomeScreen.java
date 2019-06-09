import Contact.ContactPanel;
import PongGame.BoardPong;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <p> Classe qui contient les éléments concernant le background du panel</p>
 * @version 2.0
 * @author Mickaël Puglisi
 * @author Kevin Coppey
 */
public class HomeScreen extends JPanel implements ActionListener{
    /**
     * Panneau sud
     */
    private JPanel south = new JPanel();
    /**
     * Panneau nord
     */
    private JPanel north = new JPanel();
    /**
     * Panneau nord gauche
     */
    private JPanel northeast = new JPanel();
    /**
     * Panneau nord droite
     */
    private JPanel northwest = new JPanel();
    /**
     * Label nom du réseau
     */
    private JLabel reseau = new JLabel ("Salt");
    /**
     * <p> Objet Batteryl qui crée la batterie</p>
     * @see Battery1
     */
    private Battery1 battery = new Battery1();
    /**
     * <p> Objet TimeLabel qui crée l'horloge</p>
     * @see TimeLabel
     */
    private TimeLabel clock = new TimeLabel();
    /**
     * <p> Objet LabelWithIconNorth qui crée une icône wifi</p>
     * @see LabelWithIconNorth
     */
    private LabelWithIconNorth wifi = new LabelWithIconNorth (  );
    /**
     * <p> Objet LabelWithIconNorth qui crée une icône bluetooth</p>
     * @see LabelWithIconNorth
     */
    private LabelWithIconNorth bluetooth = new LabelWithIconNorth ( );
    /**
     * <p> Objet LabelWithIconNorth qui crée une icône avion</p>
     * @see LabelWithIconNorth
     */
    private LabelWithIconNorth modeAvion = new LabelWithIconNorth (  );
    /**
     * <p> Objet Batterie qui créer le pourcentage de la batterie </p>
     * @see Batterie
     */
    private Batterie batteryPercent = new Batterie();
    /**
     * <p> Création du cardLayout permettant de bouger entrer les différents panels</p>
     */
    private CardLayout cardLayout = new CardLayout();
    /**
     * <p> Panel qui gère le fond d'écran du smartphone</p>
     * @see JPanelWithBackground
     */
    private JPanelWithBackground center = new JPanelWithBackground("images//wallpaper.jpg");
    /**
     *  <p> Panneau principal du centre</p>
     */
    private JPanel panelcont = new JPanel();
    /**
     * <p> Objet Gallery qui contient tous ses éléments </p>
     * @see Gallery
     */
    private Gallery galleryApp = new Gallery();
    /**
     * <p> Objet Settings qui contient tous ses éléments </p>
     * @see Settings
     */
    private Settings settingsApp = new Settings();
    /**
     * <p> Objet ContactPanel qui contient tous ses éléments </p>
     * @see ContactPanel
     */
    private ContactPanel contactPanel = new ContactPanel ();

    /**
     * <p> Objet BoardPong qui contient tous les éléments de l'application Pong </p>
     * @see BoardPong
     */
    private BoardPong pongGame = new BoardPong();
    /**
     * <p> Icône de l'application Contacts</p>
     * @see ButtonWithIcon
     */
    private ButtonWithIcon contact = new ButtonWithIcon("images//contact.png");
    /**
     * <p> Icône pour le bouton home du smartphone</p>
     * @see ButtonWithIcon
     */
    private ButtonWithIcon home = new ButtonWithIcon("images//home.png");
    /**
     * <p> Icône pour le bouton servant à prendre des screenshot</p>
     * @see ButtonWithIcon
     */
    private ButtonWithIcon screenshot = new ButtonWithIcon ( "images//screenshot.png");
    /**
     * <p> Icône pour le bouton previous</p>
     * @see ButtonWithIcon
     */
    private ButtonWithIcon previous = new ButtonWithIcon("images//previous.png");
    /**
     * <p> Icône pour l'application Gallery</p>
     * @see ButtonWithIcon
     */
    private ButtonWithIcon gallery = new ButtonWithIcon("images//gallery.png");
    /**
     * <p> Icône pour l'application Pong</p>
     * @see ButtonWithIcon
     */
    private ButtonWithIcon pong = new ButtonWithIcon ( "images//pong.png" );
    /**
     * <p> Icône pour le bouton éteindre</p>
     * @see ButtonWithIcon
     */
    private ButtonWithIcon shutdown = new ButtonWithIcon ( "images//shutdown.png" );
    /**
     * <p> Icône pour le bouton settings</p>
     * @see ButtonWithIcon
     */
    private ButtonWithIcon settings = new ButtonWithIcon ( "images//settings.png" );

    /**
     * <p> Police d'écrite pour des éléments du panel</p>
     * @see Font
     */
    private Font font = new Font("Arial", Font.BOLD,13);
    /**
     * <p> Texte présent sous le bouton éteindre</p>
     */
    private JLabel shutdownText = new JLabel("Shutdown");
    /**
     * <p> Texte présent sous le bouton settings</p>
     */
    private JLabel settingsText = new JLabel("Settings");
    /**
     * Texte présent sous le bouton Pong
     */
    private JLabel pongText = new JLabel("Pong Game");
    /**
     * Texte présent sous le bouton Gallery
     */
    private JLabel galleryText = new JLabel("Gallery");
    /**
     * Texte présent sous le bouton contact
     */
    private JLabel contactText = new JLabel("Contact");


    /**
     * @throws IOException
     * <p> Constructeur qui place les éléments dans l'application</p>
     *
     */
    public HomeScreen() throws IOException {
        this.setLayout(new BorderLayout());

        this.add(north, BorderLayout.NORTH);
        this.setBackground(Color.BLACK);
        panelcont.setLayout(cardLayout);
        add(panelcont,BorderLayout.CENTER);
        panelcont.add(center,"1");
        panelcont.add(galleryApp,"2");
        panelcont.add( contactPanel,"3");
        panelcont.add(settingsApp,"4");
        panelcont.add(pongGame,"5");
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
        reseau.setForeground(Color.WHITE);
        northeast.setLayout(new FlowLayout (FlowLayout.RIGHT));
        north.add(northeast,BorderLayout.EAST);
        north.add(northwest,BorderLayout.WEST);
        northwest.setLayout ( new FlowLayout ( FlowLayout.LEFT,16,0 ) );
        northeast.setBackground(Color.BLACK);
        northwest.setBackground ( Color.BLACK );
        northwest.add(wifi);
        northwest.add(bluetooth);
        northwest.add(modeAvion);
        northwest.add(clock);

        settingsApp.gettBBluetooth ().addItemListener ( new BluetoothListener () );
        settingsApp.gettBAvionMode ().addItemListener ( new AvionmodeListener () );
        settingsApp.gettBWifi ().addItemListener ( new WifiListener () );

        northeast.add(battery);
        northeast.add(batteryPercent);
        northeast.add(reseau);

        batteryPercent.setForeground(Color.WHITE);
        clock.setForeground(Color.white);

        this.add(south, BorderLayout.SOUTH);
        south.setLayout(new BorderLayout ());
        south.setBackground(Color.BLACK);

        south.add(screenshot,BorderLayout.WEST);
        south.add ( home ,BorderLayout.CENTER);
        south.add(previous,BorderLayout.EAST);
        screenshot.addActionListener ( new ScreenshotListener () );
        home.addActionListener(new HomeListener ());
        previous.addActionListener(new PreviousListener());

    }


    /**
     * <p> Classe qui gère l'action d'affichage de l'icône bluetooth</p>
     *
     */
    class BluetoothListener implements ItemListener{

        /**
         * @param e
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            int state = e.getStateChange();
            ImageIcon ii = new ImageIcon ( "images//bluetooth1.png" );
            if (state == ItemEvent.SELECTED) {
                bluetooth.setIcon (ii  );
                modeAvion.setIcon ( null );
                System.out.println("Selected"); // show your message here
            } else {
                bluetooth.setIcon ( null );
                System.out.println("Deselected"); // remove your message
            }
        }
    }

    /**
     * <p> Classe qui gère l'action d'affichage de l'icône avion</p>
     */
    class AvionmodeListener implements ItemListener{

        /**
         * @param e
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            int state = e.getStateChange();
            ImageIcon ii = new ImageIcon ( "images//airplane.png" );
            if (state == ItemEvent.SELECTED) {
                modeAvion.setIcon (ii  );
                wifi.setIcon ( null );
                bluetooth.setIcon ( null );
                System.out.println("Selected"); // show your message here
            } else {
                modeAvion.setIcon ( null );
                System.out.println("Deselected"); // remove your message
            }
        }
    }

    /**
     * <p> Classe qui gère l'action d'affichage de l'cône wifi</p>
     */
    class WifiListener implements ItemListener{

        /**
         * @param e
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            int state = e.getStateChange();
            ImageIcon ii = new ImageIcon ( "images//wifi1.png" );
            if (state == ItemEvent.SELECTED) {
                wifi.setIcon (ii  );
                modeAvion.setIcon ( null );
                System.out.println("Selected"); // show your message here
            } else {
                wifi.setIcon ( null );
                System.out.println("Deselected"); // remove your message
            }
        }
    }


    /**
     * <p> Classe qui contient les paramètres des icônes utilisées</p>
     */
    class LabelWithIconNorth extends JButton{
        /**
         * <p> Constructeur de la classe du même nom qui gère les paramètres des icônes </p>
         */
        public LabelWithIconNorth(){

            setMinimumSize ( new Dimension ( 50,20 ) );
            setPreferredSize ( new Dimension ( 52,20 ) );

            setContentAreaFilled(false);
            setBorderPainted(false);

        }
    }


    /**
     * <p> Classe qui gère les actions du bouton previous</p>
     */
    class PreviousListener implements ActionListener{
        /**
         * @param e
         */
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
                    } else{
                        cardLayout.first(panelcont);
                    }
                }else{



                    if(pongGame.isVisible ()){

                        pongGame.getGame ().setPlayMode (true) ;
                        pongGame.getGame ().setMenu (false);
                        pongGame.getGame ().setGame (false);
                        pongGame.getGame ().setPause (true);
                        pongGame.getGame ().setstartDirection (false);
                        pongGame.getGame ().setSpace (false);

                        cardLayout.first ( panelcont );

                    }else{
                        cardLayout.first ( panelcont );
                    }
                }
            }
        }
    }

    /**
     * <p> Classe qui gère la prise de screenshot par le bouton correspondant </p>
     */
    class ScreenshotListener implements ActionListener{

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                screenshot();
            } catch (IOException e1) {
                e1.printStackTrace ();
            }
        }
    }


    /**
     * @throws IOException
     */
    public void screenshot() throws IOException {


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


    /**
     * <p> Classe qui gère l'action du bouton home pour revenir au panel principal</p>
     */
    class HomeListener implements ActionListener{

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if(pongGame.isVisible ()){
                pongGame.getGame ().setPlayMode (true) ;
                pongGame.getGame ().setMenu (false);
                pongGame.getGame ().setGame (false);
                pongGame.getGame ().setPause (true);
                pongGame.getGame ().setstartDirection (false);
                pongGame.getGame ().setSpace (false);
                cardLayout.first(panelcont);

            }else
                cardLayout.first(panelcont);
        }
    }


    /**
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == gallery) {
            cardLayout.show(panelcont,"2");
        }else {
            if(obj == contact){
                cardLayout.show(panelcont,"3");
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

                        if(obj==pong){
                            cardLayout.show(panelcont,"5");
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

    /**
     * @param icon
     * @throws IOException
     */
    public ButtonWithIcon(String icon) throws IOException {
        Image img = ImageIO.read(new File (icon));
        ImageIcon ii = new ImageIcon(img);
        setIcon ( ii );
        setContentAreaFilled(false);
        setBorderPainted(false);

    }
}


/**
 * <p> Classe qui gère le bon affichage de l'heure du système</p>
 */
class TimeLabel extends JLabel implements ActionListener {
    /**
     * <p> Objet dateFormat qui gère le format de l'heure </p>
     */
    private DateFormat Display = new SimpleDateFormat("HH:mm");
    /**
     * <p> Objet Timer qui gère le temps de changement entre les unités de temps </p>
     */
    private Timer Tick = new Timer(1000, this);

    /**
     * <p> Constructeur TimeLabel qui donne le début de fonctionnemnt de l'horologe </p>
     */
    public TimeLabel() {
        Tick.start();
    }

    /**
     * @param event
     */
    public void actionPerformed(ActionEvent event)
    {
        setText(Display.format(new Date()));
    }
}

/**
 * <p> Classe qui gère la date du système</p>
 */
class DateLabel extends JLabel implements ActionListener{
    /**
     * <p> Objet de type de DateFormat qui gère le format de la date</p>
     */
    private DateFormat Display = new SimpleDateFormat("EEEE d MMMM yyyy");
    /**
     * <p> Objet de type Timer qui gère le temps de changement entre les unités de temps</p>
     */
    private Timer Tick = new Timer(1000,this);

    /**
     * <p> Constructeur DateLabel qui donne le début de fonctionnment de la date</p>
     */
    public DateLabel(){
        Tick.start();
    }

    /**
     * @param event
     */
    public void actionPerformed(ActionEvent event){
        setText(Display.format(new Date()));
    }
}


/**
 * <p> Classe qui gère les paramètres d'image de la batterie</p>
 */
class Battery1 extends Batterie implements ActionListener{

    /**
     * <p> Objet de type Timer qui explique le changement de pourcentage pour afficher la bonne image</p>
     */
    private Timer Tick = new Timer(1000, this);
    /**
     * <p> Objet de type String qui affiche une image de batterie pleine</p>
     */
    private final String batteryfull = "images//batteryfull.png";
    /**
     * <p> Objet de type String qui affiche une image de batterie en train de charger</p>
     */
    private final String batteryCharging = "images//batteryCharging.png";
    /**
     * <p> Objet de type String qui affiche une image de batterie à plus de 80% </p>
     */
    private final String battery80 = "images//battery80.png";
    /**
     * <p> Objet de type String qui affiche une image de batterie entre 60% et 80%</p>
     */
    private final String battery60 = "images//battery60.png";
    /**
     * <p> Objet de type String qui affiche une image de batterie entre 40% et 60% </p>
     */
    private final String battery40 = "images//battery40.png";
    /**
     * <p> Objet de type String qui affiche une image de batterie entre 0% et 20%</p>
     */
    private final String battery20 = "images//battery20.png";

    /**
     * <p> Constructeur Batteryl qui donne le début du fonctionnement de la batterie </p>
     */
    Battery1(){
        Tick.start();
    }

    /**
     * @param event
     */
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

/**
 * <p> Classe qui gère les paramètres de la batterie </p>
 */
class Batterie extends JLabel implements ActionListener {
    /**
     * <p> Objet de type Timer qui explique le changement de pourcentage</p>
     */
    private Timer Tick = new Timer(1000, this);

    /**
     * <p> Constructeur Batterie qui donne le début du fonctionnement de la batterie </p>
     */
    public Batterie(){
        Tick.start();
    }

    /**
     * @param event
     */
    public void actionPerformed(ActionEvent event)
    {
        Kernel32.SYSTEM_POWER_STATUS bs = new Kernel32.SYSTEM_POWER_STATUS();
        int status = Kernel32.INSTANCE.GetSystemPowerStatus(bs);
        setText(bs.toString()+" %");
    }
}




