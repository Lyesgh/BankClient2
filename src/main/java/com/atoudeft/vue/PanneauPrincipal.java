package com.atoudeft.vue;

import com.atoudeft.client.Client;
import com.atoudeft.controleur.EcouteurConnexion;
import com.atoudeft.controleur.EcouteurListeComptes;
import com.atoudeft.controleur.EcouteurOperationsCompte;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2024-11-01
 */
public class PanneauPrincipal  extends JPanel {
    private Client client;
    private PanneauConnexion panneauConnexion;
    private JPanel panneauCompteClient;
    private PanneauOperationsCompte panneauOperationsCompte;
    private PanneauDesOperations pdr;
    private PanneauHistorique phr;

    private EcouteurOperationsCompte ecouteurOp;

    private DefaultListModel<String> numerosComptes;
    private JList<String> jlNumerosComptes;
    private JDesktopPane bureau;


    public PanneauPrincipal(Client client) {
        this.client = client;

        panneauConnexion = new PanneauConnexion();
        panneauConnexion.setEcouteur(new EcouteurConnexion(client,panneauConnexion));

        panneauOperationsCompte = new PanneauOperationsCompte();
        //ecouteur pour boutons du panneau operations
        ecouteurOp = new EcouteurOperationsCompte(this.client);
        panneauOperationsCompte.setEcouteur(ecouteurOp);
        pdr = new PanneauDesOperations();
        pdr.setEcouteur(ecouteurOp);

        phr = new PanneauHistorique();
        //panneauCompteClient.add(pdr, BorderLayout.CENTER);
        ecouteurOp.setPDO(pdr);

        panneauCompteClient = new JPanel();

        panneauCompteClient.setLayout(new BorderLayout());
        panneauCompteClient.setBackground(Color.WHITE);
        panneauOperationsCompte.setBackground(Color.WHITE);

        numerosComptes = new DefaultListModel<>();

        jlNumerosComptes = new JList<>(numerosComptes);
        jlNumerosComptes.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        jlNumerosComptes.setBorder(BorderFactory.createTitledBorder("Comptes bancaires"));
        jlNumerosComptes.setPreferredSize(new Dimension(250,500));


        panneauCompteClient.add(panneauOperationsCompte, BorderLayout.NORTH);
        panneauCompteClient.add(jlNumerosComptes, BorderLayout.WEST);
        ///pdr = new PanneauDesOperations();
        ///pdr.setEcouteur(ecouteurOp);
        panneauCompteClient.add(pdr, BorderLayout.CENTER);
        //JPanel pp = new JPanel();
        //pp.setBackground(Color.MAGENTA);
        //panneauCompteClient.add(pp, BorderLayout.CENTER);
        //Enregistrement de l'écouteur de souris:
        jlNumerosComptes.addMouseListener(new EcouteurListeComptes(client));

        this.setLayout(new BorderLayout());

        this.add(panneauConnexion, BorderLayout.NORTH);
        this.add(panneauCompteClient, BorderLayout.CENTER);
        panneauCompteClient.setVisible(false);
    }

    public PanneauHistorique getPhr(){
        return phr;
    }

    public PanneauDesOperations getPdr(){
        return pdr;
    }

    public JLabel labelSolde(){
        return panneauOperationsCompte.getLblSolde();
    }


    /**
     * Vide les éléments d'interface du panneauPrincipal.
     */
    public void vider() {
        this.numerosComptes.clear();
        this.bureau.removeAll();
    }
    public void cacherPanneauConnexion() {
        panneauConnexion.effacer();
        panneauConnexion.setVisible(false);
    }
    public void montrerPanneauConnexion() {
        panneauConnexion.setVisible(true);
    }
    public void cacherPanneauCompteClient() {
        panneauCompteClient.setVisible(false);
        this.numerosComptes.clear();
    }
    public void montrerPanneauCompteClient() {
        panneauCompteClient.setVisible(true);
    }
    /**
     * Affiche un numéro de compte dans le JList des comptes.
     *
     * @param str chaine contenant le numéros de compte
     */
    public void ajouterCompte(String str) {
        numerosComptes.addElement(str);
        System.out.println(numerosComptes.get(numerosComptes.size()-1));
    }
}