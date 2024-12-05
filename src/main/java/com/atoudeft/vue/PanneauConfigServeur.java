package com.atoudeft.vue;

import com.atoudeft.client.Config;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauConfigServeur extends JPanel{
    private JTextField txtAdrServeur, txtNumPort;
    private JLabel adresseEtiquette, portEtiquette;
    private final String addrChaine = "Adresse IP: ", portChaine = "Port: ";
    private GridBagLayout gbl;
    private GridBagConstraints cons;
    //private Config;
    public PanneauConfigServeur(String adr, int port) {
        //à compléter
        gbl = new GridBagLayout();
        cons = new GridBagConstraints();
        cons.insets=new Insets(5,5,5,5);
        this.setLayout(gbl);
        cons.gridx=0;
        cons.gridy=0;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridheight=1;
        cons.anchor=GridBagConstraints.EAST;

        //Creation des composantes.
        adresseEtiquette = new JLabel(addrChaine);
        portEtiquette = new JLabel(portChaine);
        portEtiquette.setHorizontalAlignment(SwingConstants.RIGHT);
        txtAdrServeur = new JTextField();
        txtNumPort = new JTextField();

        //ajout des compasantes dans le paneau
        this.add(adresseEtiquette,cons);
        cons.gridx=1;
        cons.anchor=GridBagConstraints.WEST;
        this.add(txtAdrServeur, cons);

        cons.gridx=0;
        cons.gridy=1;
        cons.anchor=GridBagConstraints.EAST;
        this.add(portEtiquette, cons);

        cons.gridx=1;
        cons.anchor=GridBagConstraints.WEST;
        this.add(txtNumPort, cons);
        this.setAdresseServeur(adr);
        this.setPortServeur(port);
    }

    //Accesseurs
    public String getAdresseServeur() {
        return txtAdrServeur.getText();
    }
    public String getPortServeur() {
        return txtNumPort.getText();
    }

    //Mutateurs
    public void setAdresseServeur(String adr){ txtAdrServeur.setText(adr);}
    public void setPortServeur(int port){ txtNumPort.setText(String.valueOf(port));}
}
