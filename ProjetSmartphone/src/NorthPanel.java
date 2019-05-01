import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel {
    private Time clock = new Time();
    private Batterie batteryPercent = new Batterie();
    private Battery1 battery = new Battery1();


    public NorthPanel(){
        //setLayout(null);
        setBackground(Color.BLACK);
        //setBounds(0,0,400,20);
        //batteryPercent.setForeground(Color.WHITE);
        //add(batteryPercent);
        //clock.setBounds ( 120,100,220,80 );
        add(clock);
        clock.setForeground(Color.WHITE);
        add(batteryPercent);
        batteryPercent.setForeground(Color.WHITE);
    }
}
