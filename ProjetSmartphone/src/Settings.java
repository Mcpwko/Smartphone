import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class Settings extends JPanel {
    private  JLabel titre = new JLabel("SETTINGS");

    public Settings(){
        setBackground(Color.BLACK);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        titre.setFont(new Font("Arial", Font.BOLD,25));
        titre.setForeground(Color.white);
        add(titre);
    }
}

class ToggleButton extends JToggleButton{
    ImageIcon selectedIcon = new ImageIcon("BANANA");
    ImageIcon deselectedIcon = new ImageIcon("BANANASPLIT");


    public ToggleButton(){
        //this.setSelectedIcon();
        //this.setDisabledSelectedIcon();

    }
}

