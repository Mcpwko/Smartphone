import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * L'application Settings qui permet de modifier certains paramètres du Homescreen
 * @author Mickaël
 * @version 1.0
 */
public class Settings extends JPanel {
    /**
     * Le titre de l'application
     */
    private  JLabel titre = new JLabel("SETTINGS");
    /**
     *le label pour l'option Bluetooth
     */
    private JLabel bluetooth = new JLabel ( "Bluetooth" );

    /**
     * Le label pour l'option mode Avion
     */
    private JLabel modeAvion = new JLabel ( "Mode Avion" );

    /**
     * Le label pour le Wifi
     */
    private JLabel wifi = new JLabel ( "Wifi" );

    /**
     * La police d'écriture pour les labels sur le panel Settings
     */
    private Font font = new Font ( "Arial",Font.BOLD,20 );

    /**
     * Le bouton ON / OFF du wifi
     */
    private JToggleButton tBWifi = new JToggleButton ();

    /**
     * Le bouton ON / OFF du mode Avion
     */
    private JToggleButton tBAvionMode = new JToggleButton ();
    /**
     * Le bouton ON / OFF du Bluetooth
     */
    private JToggleButton tBBluetooth = new JToggleButton();

    /**
     * L'image qui servira à montrer que le bouton est déselectionné
     */
    ImageIcon deselected = new ImageIcon ( "./images/TOGGLEOFF.png" );
    /**
     * L'image qui servira à montrer que le bouton est sélectionné
     */
    ImageIcon selected = new ImageIcon ( "./images/TOGGLEON.png" );


    /**
     *
     */
    public Settings(){
        setBackground(Color.BLACK);
        setLayout(new MigLayout ( "","","[]50[]" ) );

        titre.setFont(new Font("Arial", Font.BOLD,25));
        titre.setForeground(Color.white);
        add(titre,"wrap");

        bluetooth.setFont (font);
        bluetooth.setForeground ( Color.WHITE );
        add(bluetooth);

        tBBluetooth.setContentAreaFilled(false);
        tBBluetooth.setBorderPainted(false);
        tBBluetooth.setIcon ( deselected );
        add( tBBluetooth ,"wrap");
        tBBluetooth.addItemListener(bluetoothListener);

        wifi.setFont (font);
        wifi.setForeground ( Color.WHITE );
        add(wifi);

        tBWifi.setContentAreaFilled(false);
        tBWifi.setBorderPainted(false);
        tBWifi.setIcon ( deselected );
        add(tBWifi,"wrap");
        tBWifi.addItemListener(wifiListener);

        modeAvion.setFont (font);
        modeAvion.setForeground ( Color.WHITE );
        add(modeAvion);

        tBAvionMode.setContentAreaFilled(false);
        tBAvionMode.setBorderPainted(false);
        tBAvionMode.setIcon ( deselected );
        add( tBAvionMode );
        tBAvionMode.addItemListener(avionModeListener);


    }

    /**
     * @return retourne le bouton mode Avion afin de l'utiliser dans Homescreen
     */
    public JToggleButton gettBAvionMode() {
        return tBAvionMode;
    }

    /**
     * @return retourne le bouton Bluetooth afin de l'utiliser dans Homescreen
     */
    public JToggleButton gettBBluetooth() {
        return tBBluetooth;
    }

    /**
     * @return retourne le bouton Wifi afin de l'utiliser dans Homescreen
     */
    public JToggleButton gettBWifi() {
        return tBWifi;
    }


    /**
     *listener qui va changer l'icone du bouton bluetooth en fonction de s'il est séléctionné ou non
     *
     */
    ItemListener bluetoothListener = new ItemListener() {
        /**
         * @param itemEvent
         *                  Le bouton bluetooth
         */
        public void itemStateChanged(ItemEvent itemEvent) {
            int state = itemEvent.getStateChange();
            if (state == ItemEvent.SELECTED) {
                tBBluetooth.setIcon (selected  );
                tBAvionMode.setIcon ( deselected );
                tBAvionMode.setSelected ( false );
                System.out.println("Selected"); // show your message here
            } else {
                tBBluetooth.setIcon ( deselected );
                System.out.println("Deselected"); // remove your message
            }
        }
    };


    /**
     * listener qui va changer l'icone du bouton wifi en fonction de s'il est séléctionné ou non
     */
    ItemListener wifiListener = new ItemListener() {
        /**
         * @param itemEvent
         *                  Le bouton wifi
         */
        public void itemStateChanged(ItemEvent itemEvent) {
            int state = itemEvent.getStateChange();
            if (state == ItemEvent.SELECTED) {
                tBWifi.setIcon (selected  );
                tBAvionMode.setIcon ( deselected );
                tBAvionMode.setSelected ( false );
                System.out.println("Selected"); // show your message here
            } else {
                tBWifi.setIcon ( deselected );
                System.out.println("Deselected"); // remove your message
            }
        }
    };


    /**
     * listener qui va changer l'icone du bouton modeavion en fonction de s'il est séléctionné ou non
     */
    ItemListener avionModeListener = new ItemListener() {
        /**
         * @param itemEvent
         *                  le bouton modeavion
         */
        public void itemStateChanged(ItemEvent itemEvent) {
            int state = itemEvent.getStateChange();
            if (state == ItemEvent.SELECTED) {
                tBAvionMode.setIcon (selected  );
                tBWifi.setIcon ( deselected );
                tBBluetooth.setIcon ( deselected );
                tBWifi.setSelected ( false );
                tBBluetooth.setSelected ( false );

                System.out.println("Selected"); // show your message here
            } else {
                tBAvionMode.setIcon ( deselected );
                System.out.println("Deselected"); // remove your message
            }
        }
    };
}







