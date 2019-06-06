package Contact;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.Naming;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class ContactPanel extends JPanel implements ActionListener, FocusListener {

    //Création des panneaux de base


    private JPanel panelcontent = new JPanel();
    private JPanel contactlist = new JPanel ();
    private JPanel information = new JPanel();
    private JPanel contactnew = new JPanel();

    //Création du CardLayout pour naviguer entre les panels

    private CardLayout cards = new CardLayout();

    //Création des panneaux du panneau de la liste de contact
    private JPanel northpanel = new JPanel();
    private JPanel northpanel2 = new JPanel();
    private JPanel centerpanel = new JPanel();
    private JPanel listdecontact = new JPanel();
    private JLabel label = new JLabel("Contacts");
    private ButtonWithIcon buttonplus = new ButtonWithIcon("src\\Contact\\addcontact1.png");
    private Font font = new Font("Arial",Font.BOLD,30);
    private Font font2 = new Font("Arial",Font.BOLD,15);
    private Border border = BorderFactory.createLineBorder(Color.WHITE, 1);



    private JButton listContactButton[] = new JButton[10000000];


    //Création des élément du panneau d'ajout de contact

    private JPanel panelCenter = new JPanel();
    private JLabel nouveaucontact = new JLabel("Nouveau Contact");
    private ButtonWithIcon photo = new ButtonWithIcon("src\\Contact\\photo.png");
    private JTextField prenom = new JTextField("Prénom");
    private JTextField nom = new JTextField("Nom");
    private JTextField emailtext = new JTextField("E-mail");
    private JTextField phonenumber = new JTextField("Téléphone privé");
    private JTextField phonenumber2 = new JTextField("Téléphone professionnel");
    private JTextField phonenumber3 = new JTextField("Téléphone domicile");
    private JTextField adress = new JTextField("Adresse");
    private LabelWithIcon privatephone = new LabelWithIcon("src\\Contact\\smartphone.png");
    private LabelWithIcon telprof2 = new LabelWithIcon("src\\Contact\\telprof.png");
    private LabelWithIcon teldom2 = new LabelWithIcon("src\\Contact\\factory.png");
    private LabelWithIcon home = new LabelWithIcon("src\\Contact\\home.png");
    private LabelWithIcon adress2 = new LabelWithIcon("src\\Contact\\mail.png");
    private LabelWithIcon location = new LabelWithIcon("src\\Contact\\location.png");
    private JLabel blankLabel = new JLabel();
    private ButtonWithIcon save = new ButtonWithIcon("src\\Contact\\save.png");
    private JFileChooser fileChooser = new JFileChooser();
    private Font fontadd = new Font("arial",Font.TRUETYPE_FONT,30);
    private Dimension dimensionadd = new Dimension(300,50);
    private Dimension dimensionadd2 = new Dimension(420,50);

    //Création panneau information
    private JTextField identite;
    private JTextField name;
    private JTextField firstname;
    private JTextField email;
    private JTextField telprive;
    private JTextField telprof;
    private JTextField teldom;
    private JTextField address;
    private ButtonWithIcon editbutton = new ButtonWithIcon("src\\Contact\\editbutton.png");
    private ButtonWithIcon saveedit = new ButtonWithIcon("src\\Contact\\save.png");
    private ButtonWithIcon deletebutton = new ButtonWithIcon("src\\Contact\\deletebutton.png");

    public ContactPanel() throws IOException {

        setLayout ( new BorderLayout (  ) );



        //Paramétrage des panels

        add(panelcontent,BorderLayout.CENTER);
        panelcontent.setLayout(cards);
        panelcontent.add(contactlist,"1");
        panelcontent.add(information,"2");
        panelcontent.add(contactnew,"3");

        //Paramétrage de la liste de contact

        contactlist.setLayout(new BorderLayout());
        contactlist.add(northpanel,BorderLayout.NORTH);
        northpanel.setLayout(new BorderLayout());
        JScrollPane listing = new JScrollPane ( centerpanel );


        listdecontact.setLayout(new GridLayout(0,1));
        //contactlist.add(centerpanel, BorderLayout.CENTER);
        centerpanel.add(listdecontact,BorderLayout.CENTER);
        contactlist.add(listing,BorderLayout.CENTER);
        listing.setHorizontalScrollBarPolicy ( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        listing.setBackground ( Color.BLACK );
        listing.setBorder((BorderFactory.createLineBorder(Color.BLACK, 1)) );
        listing.getVerticalScrollBar().setUnitIncrement(100);

        label.setFont(font);
        blankLabel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


        northpanel.setBackground(Color.BLACK);
        centerpanel.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);

        northpanel.add(northpanel2.add(label), BorderLayout.WEST);
        northpanel.add(northpanel2.add(buttonplus), BorderLayout.EAST);

        buttonplus.addActionListener(this);


        //Paramètrage de la page d'ajout de contact

        contactnew.setLayout(new BorderLayout());
        contactnew.add(panelCenter,BorderLayout.CENTER);
        contactnew.add(nouveaucontact,BorderLayout.NORTH);
        contactnew.setBackground(Color.BLACK);

        nouveaucontact.setFont(font);

        contactnew.add(save,BorderLayout.SOUTH);

        save.addActionListener(this);

        nouveaucontact.setBorder(BorderFactory.createEmptyBorder(20, 20, 35, 20));
        nouveaucontact.setForeground(Color.white);


        panelCenter.setLayout(new MigLayout( "", "", "[]20[]"));
        panelCenter.setBackground(Color.BLACK);
        panelCenter.add("cell 0 0 2 2",photo);
        photo.addActionListener(new AddphotoListener());
        prenom.setPreferredSize(dimensionadd);
        prenom.setFont(fontadd);
        panelCenter.add("cell 3 0 2 1",prenom);
        nom.setPreferredSize(dimensionadd);
        nom.setFont(fontadd);
        panelCenter.add("cell 3 1 2 1", nom);


        panelCenter.add("cell 0 3 1 1", adress2);
        panelCenter.add("cell 0 4 1 1",privatephone);
        panelCenter.add("cell 0 5 1 1",teldom2 );
        panelCenter.add("cell 0 6 1 1 ", home);
        panelCenter.add("cell 0 7 1 1",location);

        emailtext.setPreferredSize(dimensionadd2);
        emailtext.setFont(fontadd);
        panelCenter.add("cell 1 3 3 1", emailtext);
        phonenumber.setPreferredSize(dimensionadd2);
        phonenumber.setFont(fontadd);
        panelCenter.add("cell 1 4 3 1",phonenumber);
        phonenumber2.setPreferredSize(dimensionadd2);
        phonenumber2.setFont(fontadd);
        panelCenter.add("cell 1 5 3 1",phonenumber2);
        phonenumber3.setPreferredSize(dimensionadd2);
        phonenumber3.setFont(fontadd);
        panelCenter.add("cell 1 6 3 1",phonenumber3);
        adress.setPreferredSize(dimensionadd2);
        adress.setFont(fontadd);
        panelCenter.add("cell 1 7 3 1",adress);


        prenom.setMinimumSize(new Dimension(150,40));
        prenom.setForeground(Color.GRAY);
        prenom.addFocusListener(this);

        nom.setMinimumSize(new Dimension(150,40));
        nom.setForeground(Color.GRAY);
        nom.addFocusListener(this);

        emailtext.setMinimumSize(new Dimension(150,40));
        emailtext.setForeground(Color.GRAY);
        emailtext.addFocusListener(this);

        phonenumber.setMinimumSize(new Dimension(150,40));
        phonenumber.setForeground(Color.GRAY);
        phonenumber.addFocusListener(this);

        phonenumber2.setMinimumSize(new Dimension(150,40));
        phonenumber2.setForeground(Color.GRAY);
        phonenumber2.addFocusListener(this);

        phonenumber3.setMinimumSize(new Dimension(150,40));
        phonenumber3.setForeground(Color.GRAY);
        phonenumber3.addFocusListener(this);

        adress.setMinimumSize(new Dimension(150,40));
        adress.setForeground(Color.GRAY);
        adress.addFocusListener(this);

        //Paramétrage de la liste d'information

        information.setBackground(Color.BLACK);


        // initialiser la liste des contacts
        initContactList();






    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == buttonplus)
            cards.show(panelcontent,"3");
        else
        if(source == save) {

            if (validateEmail(emailtext.getText())) {


                File monRepertoire = new File("src\\Contact\\contact");
                File[] f = monRepertoire.listFiles();
                String newContact = prenom.getText() + nom.getText() + ".txt";
                boolean twice = false;

                for (int i = 0; i < f.length; i++) {
                    System.out.println(f[i].getName());
                    System.out.println(newContact);


                    if (newContact.equals(f[i].getName())) {
                        twice = true;
                        break;
                    } else
                        twice = false;
                    System.out.println("JE NAI PAS DOUBLE");
                }

                if (twice == true) {

                    JOptionPane pane = new JOptionPane();

                    int answer = JOptionPane.showConfirmDialog(null, "This contact already exists, Do you want to overwrite it ?",
                            "Choice", JOptionPane.YES_NO_OPTION);

                    if (answer == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "the contact has been overwritten");


                        FileWriter monFichier = null;
                        BufferedWriter tampon = null;
                        String[] data = new String[7];

                        // Entrer les données dans le tableau

                        data[0] = prenom.getText();
                        data[1] = nom.getText();
                        data[2] = emailtext.getText();
                        data[3] = phonenumber.getText();
                        data[4] = phonenumber2.getText();
                        data[5] = phonenumber3.getText();
                        data[6] = adress.getText();


                        try {
                            File file = new File("src\\Contact\\contact\\" + prenom.getText() + nom.getText() + ".txt");
                            monFichier = new FileWriter(file);
                            tampon = new BufferedWriter(monFichier);

                            File repertoire = new File("chemin_du_dossier");
                            File[] listeFilePath = repertoire.listFiles();
                /*for (i = 0, num = 0; i < listeFilePath.length ; i++)
                {
                }*/

                            for (int i = 0; i < data.length; i++) {
                                tampon.write((data[i]) + "\r\n");
                            }

                        } catch (IOException exception) {
                            exception.printStackTrace();
                        } finally {
                            try {
                                tampon.flush();
                                tampon.close();
                                monFichier.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }





                        /*
                    try {
                        BufferedReader in = new BufferedReader(new FileReader("E:\\Semestre 2\\Programmation\\Contact\\contact_data"));
                        String str;

                        while ((str = in.readLine()) != null) {
                            System.out.println(str);
                        }
                        System.out.println(str);
                    } catch (IOException e2) {
                    }
                    }
                    */
                        cards.show(panelcontent, "1");
                        prenom.setText("Prénom");
                        nom.setText("Nom");
                        emailtext.setText("E-mail");
                        phonenumber.setText("Téléphone privé");
                        phonenumber2.setText("Téléphone professionnel");
                        phonenumber3.setText("Téléphone domicile");
                        adress.setText("Adresse");
                    } else if (answer == JOptionPane.NO_OPTION) {
                        System.out.println("Non");
                        prenom.setText("Prénom");
                        nom.setText("Nom");
                        emailtext.setText("E-mail");
                        phonenumber.setText("Téléphone privé");
                        phonenumber2.setText("Téléphone professionnel");
                        phonenumber3.setText("Téléphone domicile");
                        adress.setText("Adresse");
                        cards.show(panelcontent, "1");
                    }


            } else {

                System.out.println(monRepertoire.length());
                int numberFiles = (int) monRepertoire.length();
                listContactButton[numberFiles] = new JButton(nom.getText() + " " + prenom.getText());
                listContactButton[numberFiles].setActionCommand(listdecontact.getComponentCount() + "");
                listContactButton[numberFiles].addActionListener(this);

                listContactButton[numberFiles].setContentAreaFilled(false);
                listContactButton[numberFiles].setOpaque(true);
                listContactButton[numberFiles].setPreferredSize(new Dimension(500, 20));
                listContactButton[numberFiles].setBorder(border);
                listdecontact.add(listContactButton[numberFiles]);
                listdecontact.add("cell 1 3", blankLabel);
                blankLabel.setBackground(Color.BLACK);
                blankLabel.setOpaque(true);
                listContactButton[numberFiles].setFont(font2);
                listContactButton[numberFiles].setBackground(Color.BLACK);
                listContactButton[numberFiles].setForeground(Color.WHITE);


                FileWriter monFichier = null;
                BufferedWriter tampon = null;
                String[] data = new String[7];

                // Entrer les données dans le tableau

                data[0] = prenom.getText();
                data[1] = nom.getText();
                data[2] = emailtext.getText();
                data[3] = phonenumber.getText();
                data[4] = phonenumber2.getText();
                data[5] = phonenumber3.getText();
                data[6] = adress.getText();


                try {
                    File file = new File("src\\Contact\\contact\\" + prenom.getText() + nom.getText() + ".txt");
                    monFichier = new FileWriter(file);
                    tampon = new BufferedWriter(monFichier);

                    File repertoire = new File("chemin_du_dossier");
                    File[] listeFilePath = repertoire.listFiles();
                /*for (i = 0, num = 0; i < listeFilePath.length ; i++)
                {
                }*/

                    for (int i = 0; i < data.length; i++) {
                        tampon.write((data[i]) + "\r\n");
                    }

                } catch (IOException exception) {
                    exception.printStackTrace();
                } finally {
                    try {
                        tampon.flush();
                        tampon.close();
                        monFichier.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }


                prenom.setText("Prénom");
                nom.setText("Nom");
                emailtext.setText("E-mail");
                phonenumber.setText("Téléphone privé");
                phonenumber2.setText("Téléphone professionnel");
                phonenumber3.setText("Téléphone domicile");
                adress.setText("Adresse");
                cards.show(panelcontent, "1");
            }

        }else {
                emailtext.setForeground(Color.RED);

            }



        }else{

            panelcontent.add(information,(e.getActionCommand()+4)+"");
            cards.show(panelcontent,""+(e.getActionCommand()+4));


        }


    }

    public ButtonWithIcon getButton(){
        return save;
    }



    class AddphotoListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            File dir = new File("Gallery\\");
            fileChooser.setCurrentDirectory(dir);
            int returnVal = fileChooser.showOpenDialog (null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                //contactnew.remove(photo);


                File file = fileChooser.getSelectedFile ();
                String chemin = file.getPath();
                LabelNewContact photo2 = new LabelNewContact(chemin);
                panelCenter.remove(photo);
                panelCenter.add("cell 0 0 2 2",photo2);
                panelCenter.revalidate();
                panelCenter.repaint();

                /*getButton();


                //File destination = new File ( "src\\Contact\\ImageContact\\" +prenom.getText() + ".jpg" );
                //try {
                  //  FileUtils.copyFile ( file, destination );
                //} catch (IOException e1) {
                  //  e1.printStackTrace ();
                //}
                String path = file.getAbsolutePath ();
                ImageIcon img = new ImageIcon ( path );
                ButtonWithIcon button = null;*/

            }
        }

        /*public boolean validateEmail() {
            if(prenom.equals("Prénom")){

            }
        }*/


    }

    class LabelNewContact extends JLabel{

        public LabelNewContact(String icon){
            ImageIcon ii = new ImageIcon(icon);
            ImageIcon ii2 = getScaledImage(ii,100,100);
            setIcon(ii2);

        }

        private ImageIcon getScaledImage(ImageIcon srcImg, int w, int h){
            Image img = srcImg.getImage();
            BufferedImage resizedImg = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resizedImg.createGraphics();

            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(img,0,0,w,h,null);
            g2.dispose();

            return new ImageIcon(resizedImg);
        }
    }


    @Override
    public void focusGained(FocusEvent e) {
        Object source = e.getSource();
        if(source == prenom)
            prenom.setText("");
        else
        if(source == nom)
            nom.setText("");
        else
        if(source == emailtext )
            emailtext.setText("");
        else
        if(source == phonenumber)
            phonenumber.setText("");
        else
        if (source == phonenumber2)
            phonenumber2.setText("");
        else
        if(source == phonenumber3)
            phonenumber3.setText("");
        else
        if(source == adress)
            adress.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    class ButtonWithIcon extends JButton{

        public ButtonWithIcon(String icon) {
            ImageIcon ii = new ImageIcon(icon);
            setIcon ( ii );
            setContentAreaFilled(false);
            setBorderPainted(false);

        }
    }

    class LabelWithIcon extends JLabel {


        public LabelWithIcon(String icon) {
            ImageIcon ii = new ImageIcon(icon);
            setIcon(ii);
        }
    }
    public Object[] ReadInfo() {

        FileReader monFichier = null;
        BufferedReader tampon = null;
        String tab[] = new String[7];

        try {
            monFichier = new FileReader("src\\Contact\\contact");
            tampon = new BufferedReader(monFichier);
            File file = new File("src\\Contact\\contact") ;
            String extension = ".txt";

            File[] liste = file.listFiles(new FileFilter() {
                                              public boolean accept(File f) {
                                                  return f.getName().endsWith(extension);
                                              }

                                          }
            );


            while (true) {
                // Lit une ligne de test.txt
                String ligne = tampon.readLine();
                // Vérifie la fin de fichier
                if (ligne == null)
                    break;
                System.out.println(ligne);

                for(int i = 0;i<tab.length;i++) {
                    tab[i] = tampon.readLine();
                }

            } // Fin du while
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                tampon.close();
                monFichier.close();
            } catch(IOException exception1) {
                exception1.printStackTrace();
            }
        }

        return tab;
    }

    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }

    public void initContactList() throws IOException {
        File monRepertoire=new File("src\\Contact\\contact");
        File[] f = monRepertoire.listFiles();


        System.out.println(f.length);

        for(int i = 0; i<f.length ;i++){
            BufferedReader Buff = null;
            try {
                Buff = new BufferedReader(new FileReader(f[i]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println(f[i].getName());


            String prenom = Buff.readLine();
            String nom = Buff.readLine();

            listContactButton[i] = new JButton(nom + " " + prenom);
            listContactButton[i].setContentAreaFilled(false);
            listContactButton[i].setOpaque(true);
            listContactButton[i].setPreferredSize(new Dimension(500, 20));
            listContactButton[i].setBorder(border);
            listdecontact.add(listContactButton[i]);
            listdecontact.add("cell 1 3", blankLabel);
            blankLabel.setBackground(Color.BLACK);
            blankLabel.setOpaque(true);
            listContactButton[i].setFont(font2);
            listContactButton[i].setBackground(Color.BLACK);
            listContactButton[i].setForeground(Color.WHITE);
            listContactButton[i].addActionListener(new ListenerListContact());
            listContactButton[i].setActionCommand(prenom+nom+".txt");
            System.out.println(listContactButton[i].getActionCommand());

            Buff.close();
        }

    }

    class ListenerListContact implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            File selected =new File("src\\Contact\\contact\\" + e.getActionCommand());
            System.out.println(e.getActionCommand());
            String[] data = new String[7];

            //JPanel panelinformation = new JPanel();
            LabelWithIcon bonhomme = new LabelWithIcon("src\\Contact\\icone.png");



            Font font3  = new Font("Arial",Font.BOLD,20);
            Font font4 = new Font ("Arial",Font.TRUETYPE_FONT,25);
            Font fontnom = new Font("Arial",Font.TRUETYPE_FONT,40);

            BufferedReader Buff = null;
            try {
                Buff = new BufferedReader(new FileReader(selected));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }


            for(int i = 0; i<=6 ; i++) {
                try {
                    data[i] = Buff.readLine();
                    System.out.println("SALUT");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            /*identite = new JTextField();
            identite.setText(data[0]+" " + data[1]);
            identite.setEditable(false);
            identite.setBackground(Color.BLACK);
            identite.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            identite.setMinimumSize(new Dimension(400,10));*/

            name = new JTextField();
            name.setText(data[1]);
            name.setEditable(false);
            name.setBackground(Color.BLACK);
            name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            name.setMinimumSize(new Dimension(60,10));
            name.setMaximumSize(new Dimension(200,50));

            firstname = new JTextField();
            firstname.setText(data[0]);
            firstname.setEditable(false);
            firstname.setBackground(Color.BLACK);
            firstname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            firstname.setMinimumSize(new Dimension(80,10));
            firstname.setMinimumSize(new Dimension(150,50));

            email = new JTextField();
            email.setText(data[2]);
            email.setEditable(false);
            email.setBackground(Color.BLACK);
            email.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            email.setMinimumSize(new Dimension(400,10));

            telprive = new JTextField();
            telprive.setText(data[3]);
            telprive.setEditable(false);
            telprive.setBackground(Color.BLACK);
            telprive.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            telprive.setMinimumSize(new Dimension(400,10));

            telprof = new JTextField();
            telprof.setText(data[4]);
            telprof.setEditable(false);
            telprof.setBackground(Color.BLACK);
            telprof.setBorder(javax.swing.BorderFactory.createEmptyBorder());

            teldom = new JTextField();
            teldom.setText(data[5]);
            teldom.setEditable(false);
            teldom.setBackground(Color.BLACK);
            teldom.setBorder(javax.swing.BorderFactory.createEmptyBorder());

            address = new JTextField();
            address.setText(data[6]);
            address.setEditable(false);
            address.setBackground(Color.BLACK);
            address.setBorder(javax.swing.BorderFactory.createEmptyBorder());

            JLabel telPrive = new JLabel("Private Phone:");
            JLabel telProf = new JLabel("Professionnal Phone :");
            JLabel telDom = new JLabel("House Phone :");
            JLabel eMail = new JLabel("Email :");
            JLabel address1 = new JLabel("Adress : ");



            /*identite.setFont(fontnom);
            identite.setForeground(Color.WHITE);*/

            name.setFont(fontnom);
            name.setForeground(Color.WHITE);
            firstname.setFont(fontnom);
            firstname.setForeground(Color.WHITE);

            telprive.setFont(font4);
            telprive.setForeground(Color.WHITE);
            teldom.setFont(font4);
            teldom.setForeground(Color.WHITE);
            telprof.setFont(font4);
            telprof.setForeground(Color.WHITE);
            email.setFont(font4);
            email.setForeground(Color.WHITE);
            address.setFont(font4);
            address.setForeground(Color.WHITE);

            telPrive.setFont(font3);
            telPrive.setForeground(Color.WHITE);
            telDom.setFont(font3);
            telDom.setForeground(Color.WHITE);
            telProf.setFont(font3);
            telProf.setForeground(Color.WHITE);
            eMail.setFont(font3);
            eMail.setForeground(Color.WHITE);
            address1.setFont(font3);
            address1.setForeground(Color.WHITE);



            /*telprive.setFont(font4);
            telprof.setFont(font4);
            teldom.setFont(font4);
            email.setFont(font4);*/
            JLabel labelvide = new JLabel("ESPACEME");
            labelvide.setForeground(Color.BLACK);
            CC componentConstraints = new CC();
            componentConstraints.alignX("center").spanX();
            MigLayout mig = new MigLayout (  "", "fill", "[]20[]");
            information.setLayout(mig);
            //information.add("cell 0 0 1 1",labelvide);
            //information.add("cell 3 0 3 4",bonhomme);
            //information.add("cell 0 5 1 1",labelvide);
            //information.add("cell 3 5 3 1",identite);
            //information.add(bonhomme,componentConstraints);
            //information.add(identite,componentConstraints);
            information.add("pos 0 10",deletebutton);
            information.add("gapleft 160,wrap",bonhomme);
            information.add("pos 380 -5",editbutton);
            //information.add("gapleft 90,wrap",identite);
            information.add("pos 20 170",name);
            information.add("pos 245 170,wrap",firstname);


            information.add("gaptop 20,cell 0 6 6 1",telPrive);
            information.add("cell 0 7 6 1", telprive);

            information.add("cell 0 8 6 1" , telProf);
            information.add("cell 0 9 6 1", telprof);

            information.add("cell 0 10 6 1", telDom);
            information.add("cell 0 11 6 1", teldom);

            information.add("cell 0 12 6 1", eMail);
            information.add("cell 0 13 6 1", email);

            information.add("cell 0 14 6 1",address1);
            information.add("cell 0 15 6 1", address);

            deletebutton.addActionListener(new DeleteListener());

            editbutton.addActionListener(new EditListener());

            //information.add("cell 0 0 2 2",editbutton);




            cards.show(panelcontent,"2");

            try {
                Buff.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }

    }

    class EditListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            name.setEditable(true);
            name.setBackground(Color.WHITE);
            name.setForeground(Color.BLACK);

            firstname.setEditable(true);
            firstname.setBackground(Color.WHITE);
            firstname.setForeground(Color.BLACK);

            email.setEditable(true);
            email.setBackground(Color.WHITE);
            email.setForeground(Color.BLACK);

            telprive.setEditable(true);
            telprive.setBackground(Color.WHITE);
            telprive.setForeground(Color.BLACK);

            telprof.setEditable(true);
            telprof.setBackground(Color.WHITE);
            telprof.setForeground(Color.BLACK);

            teldom.setEditable(true);
            teldom.setBackground(Color.WHITE);
            teldom.setForeground(Color.BLACK);

            address.setEditable(true);
            address.setBackground(Color.WHITE);
            address.setForeground(Color.BLACK);
            //information.revalidate();
            information.remove(deletebutton);
            information.remove(editbutton);
            information.repaint();
            information.add("pos 380 -5",saveedit); // CREATION NOUVEAU BOUTON
            saveedit.addActionListener(new SaveEditContact());
            information.revalidate();



        }
    }

    class SaveEditContact implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            File monRepertoire=new File("src\\Contact\\contact");
            File[] f = monRepertoire.listFiles();
            String newContact = firstname.getText()+name.getText()+".txt";





            FileWriter monFichier = null;
            BufferedWriter tampon = null;
            String[] data = new String[7];

            // Entrer les données dans le tableau

            data[0] = firstname.getText() ;
            data[1] = name.getText();
            data[2] = email.getText();
            data[3] = telprive.getText();
            data[4] = telprof.getText();
            data[5] = teldom.getText();
            data[6] = address.getText();






                    try {
                        File file = new File("src\\Contact\\contact\\" + firstname.getText() + name.getText() + ".txt");
                        monFichier = new FileWriter(file);
                        tampon = new BufferedWriter(monFichier);

                        File repertoire = new File("chemin_du_dossier");
                        File[] listeFilePath = repertoire.listFiles();

                        for (int i = 0; i < data.length; i++) {
                            tampon.write((data[i]) + "\r\n");
                        }

                    } catch (IOException exception) {
                        exception.printStackTrace();
                    } finally {
                        try {
                            tampon.flush();
                            tampon.close();
                            monFichier.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                    cards.show(panelcontent, "1");


            name.setEditable(false);
            name.setBackground(Color.BLACK);
            name.setForeground(Color.WHITE);

            firstname.setEditable(false);
            firstname.setBackground(Color.BLACK);
            firstname.setForeground(Color.WHITE);

            email.setEditable(false);
            email.setBackground(Color.BLACK);
            email.setForeground(Color.WHITE);

            telprive.setEditable(false);
            telprive.setBackground(Color.BLACK);
            telprive.setForeground(Color.WHITE);

            telprof.setEditable(false);
            telprof.setBackground(Color.BLACK);
            telprof.setForeground(Color.WHITE);

            teldom.setEditable(false);
            teldom.setBackground(Color.BLACK);
            teldom.setForeground(Color.WHITE);

            address.setEditable(false);
            address.setBackground(Color.BLACK);
            address.setForeground(Color.WHITE);


                /*information.remove(saveedit);
                information.repaint();
                editbutton.addActionListener(new EditListener());*/
                information.removeAll();
                information.revalidate();

                }

            }

    class DeleteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {


            File file = new File ("src\\Contact\\contact\\"+firstname.getText()+ name.getText()+".txt");
            System.out.println("src\\Contact\\contact\\"+firstname.getText()+ name.getText()+".txt");
            System.out.println(file.getName());
            System.out.println(file.exists());


            file.delete();


            listdecontact.removeAll();
            try {
                initContactList();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            cards.show(panelcontent,"1");
        }
    }

    public boolean validateEmail(String email) {
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
    }

        }









