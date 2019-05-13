import net.miginfocom.swing.MigLayout;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Vector;

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
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelPictures.setLayout(new MigLayout());
        scrollPane.getVerticalScrollBar().setUnitIncrement(100);
        /*panelPictures.add(new JLabel("AWKWARD"));
        //panelPictures.add(new JLabel("123"));
        panelPictures.add(new JLabel("BANAN"));
        for(int i=0;i<200; i++)
        panelPictures.add(new JLabel("JSAISPAS"));*/



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addphoto){
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                File destination = new File("Gallery\\" + file.getName());
                try {
                    FileUtils.copyFile(file,destination);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                String path = file.getAbsolutePath();
                ImageIcon img = new ImageIcon(path);
                ButtonWithIcon button = new ButtonWithIcon(path);
                button.setMaximumSize(new Dimension(112,112));
                button.setMinimumSize(new Dimension(112,112));

                if((panelPictures.getComponentCount()+1)%4==0 && panelPictures.getComponentCount()!=0){
                    panelPictures.add(button,"wrap");
                }
                else{
                    panelPictures.add(button);
                }

                button.addActionListener(this);

                panelPictures.revalidate();
                System.out.println(panelPictures.getComponentCount());
            }
        }
    }

    public Vector listerRepertoire(File repertoire){

        String[] liste = repertoire.list();
        int taille = liste.length;
        Vector listefichiers = new Vector();

        for(int i=0;i<taille;i++){
            if(liste[i].endsWith(".jpg") || liste[i].endsWith(".png")){
                listefichiers.addElement(repertoire.getAbsoluteFile() + liste[i]);
            }
        }
        return listefichiers;
    }

    private void addImg(String chemin) {

        File a = new File(chemin); //listerRepertoire attend un File
        int taille = listerRepertoire(a).size(); // récupération de la taille du tableau
        JLabel[] TabImg = new JLabel[taille]; // création de mon tableau de JLabel qui contiendra les images
        //String[] tab = listerRepertoire(a); //récupération du tableau contenant les adresses des images

        for(int i=0;i<taille;i++){
            TabImg[i].setIcon(new ImageIcon((String)(listerRepertoire(a)).elementAt(i)));   //j'assigne une image à chaque JLabel
            panelPictures.add(TabImg[i]); //et je le rajoute à mon panneau pour l'afficher
        }

    }
}
