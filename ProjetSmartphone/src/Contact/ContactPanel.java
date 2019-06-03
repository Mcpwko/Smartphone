package Contact;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
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
    private Font fontadd = new Font("arial",Font.TRUETYPE_FONT,30);
    private Dimension dimensionadd = new Dimension(300,50);
    private Dimension dimensionadd2 = new Dimension(420,50);

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



            File monRepertoire=new File("src\\Contact\\contact");
            File[] f = monRepertoire.listFiles();
            String newContact = prenom.getText()+nom.getText()+".txt";
            boolean twice = false;

            for(int i = 0 ; i<f.length;i++){
                System.out.println(f[i].getName());
                System.out.println(newContact);


                if(newContact.equals(f[i].getName())){
                    twice = true;
                    break;
                }else
                    twice = false;
                System.out.println("JE NAI PAS DOUBLE");
            }

            if(twice == true) {

                JOptionPane pane = new JOptionPane();

                int answer = JOptionPane.showConfirmDialog(null, "This contact already exists, Do you want to overwrite it ?",
                        "Choice", JOptionPane.YES_NO_OPTION);

                if (answer == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "the contact has been overwritten");




                    FileWriter monFichier = null;
                    BufferedWriter tampon = null;
                    String[] data = new String[7];

                    // Entrer les données dans le tableau

                    data[0] = prenom.getText() ;
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
            }else{

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

                data[0] = prenom.getText() ;
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



        }else{

            panelcontent.add(information,(e.getActionCommand()+4)+"");
            cards.show(panelcontent,""+(e.getActionCommand()+4));


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

        }
    }

    class ListenerListContact implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            File selected =new File("src\\Contact\\contact\\" + e.getActionCommand());
            System.out.println(e.getActionCommand());
            String[] data = new String[7];

            JPanel panelinformation = new JPanel();
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

            JLabel identite = new JLabel(data[0]+" " + data[1]);
            JLabel email = new JLabel(data[2]);
            JLabel telprive = new JLabel(data[3]);
            JLabel telprof = new JLabel(data[4]);
            JLabel teldom = new JLabel(data[5]);
            JLabel address = new JLabel(data[6]);

            JLabel telPrive = new JLabel("Private Phone:");
            JLabel telProf = new JLabel("Professionnal Phone :");
            JLabel telDom = new JLabel("House Phone :");
            JLabel eMail = new JLabel("Email :");
            JLabel address1 = new JLabel("Adress : ");


            identite.setFont(fontnom);
            identite.setForeground(Color.WHITE);
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
            MigLayout mig = new MigLayout (  "", "", "[]20[]");
            information.setLayout(mig);
            //information.add("cell 0 0 1 1",labelvide);
            //information.add("cell 3 0 3 4",bonhomme);
            //information.add("cell 0 5 1 1",labelvide);
            //information.add("cell 3 5 3 1",identite);
            //information.add(bonhomme,componentConstraints);
            //information.add(identite,componentConstraints);
            information.add("gapleft 160, wrap",bonhomme);
            information.add("gapleft 90,wrap",identite);


            information.add("cell 0 6 6 1",telPrive);
            information.add("cell 0 7 6 1", telprive);

            information.add("cell 0 8 6 1" , telProf);
            information.add("cell 0 9 6 1", telprof);

            information.add("cell 0 10 6 1", telDom);
            information.add("cell 0 11 6 1", teldom);

            information.add("cell 0 12 6 1", eMail);
            information.add("cell 0 13 6 1", email);

            information.add("cell 0 14 6 1",address);
            information.add("cell 0 15 6 1", address1);




            cards.show(panelcontent,"2");



        }
    }


}









