import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel {
    private Time clock = new Time();
    private Batterie batteryPercent = new Batterie();
    private Battery1 battery = new Battery1();


    public NorthPanel(){
        setLayout(null);
        setBackground(Color.BLACK);
        //setBounds(0,0,400,20);
        batteryPercent.setForeground(Color.WHITE);
        add(batteryPercent);
    }
}
