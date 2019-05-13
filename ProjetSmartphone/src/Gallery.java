import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Gallery extends JPanel implements ActionListener {
    private ButtonWithIcon addphoto = new ButtonWithIcon("images//addphoto.png");
    private ButtonWithIcon deletephoto = new ButtonWithIcon("images//deletephoto.png");
    private JPanel southpanel1 = new JPanel();
    private JPanel panelPictures = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private JPanel panel1 = new JPanel();
    private GridLayout gridLayout = new GridLayout(0,4,2,2);
    private JScrollPane scrollPane = new JScrollPane(panelPictures);
    private JFileChooser fileChooser = new JFileChooser();



    public Gallery(){
        setBackground(Color.cyan);
        setLayout(cardLayout);
        add(panel1,"1");
        panel1.setBackground(Color.red);
        panel1.setLayout(new BorderLayout());
        panel1.add(southpanel1,BorderLayout.SOUTH);
        southpanel1.add(addphoto);
        addphoto.addActionListener(this);
        southpanel1.add(deletephoto);
        scrollPane.setViewportView(panelPictures);
        panel1.add(scrollPane);
        panelPictures.setLayout(gridLayout);
        panelPictures.add(new JLabel("AWKWARD"));
        //panelPictures.add(new JLabel("123"));
        panelPictures.add(new JLabel("BANAN"));
        for(int i=0;i<2; i++)
        panelPictures.add(new JLabel("JSAISPAS"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addphoto){
            int returnVal = fileChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                revalidate();
                //This is where a real application would open the file.
                /*log.append("Opening: " + file.getName() + "." + newline);
            } else {
                log.append("Open command cancelled by user." + newline);*/
            }
        }
    }
}
