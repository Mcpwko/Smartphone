import javax.swing.*;
import java.awt.*;

public class Lockscreen extends JPanel{
    private JSlider slide = new JSlider();

    public Lockscreen(){
        this.setLayout(new GridLayout(5,1));
        this.add(slide);

    }

}