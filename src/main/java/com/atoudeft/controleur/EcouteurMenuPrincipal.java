package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauConfigServeur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2024-11-01
 */
public class EcouteurMenuPrincipal implements ActionListener {
    private Client client;
    private JFrame fenetre;
    private PanneauConfigServeur panConfServeur;

    public EcouteurMenuPrincipal(Client client, JFrame fenetre) {
        this.client = client;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        String action;
        String alias;
        int res;

        if (source instanceof JMenuItem) {
            action = ((JMenuItem)source).getActionCommand();
            switch (action) {
                case "CONNECTER":
                    if (!client.isConnecte()) {
                        if (!client.connecter()) {
                            JOptionPane.showMessageDialog(fenetre, "Le serveur ne répond pas");
                            break;
                        }
                    }
                    break;
                case "DECONNECTER":
                    if (!client.isConnecte())
                        break;
                    res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                            "Confirmation Déconnecter",
                            JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if (res == JOptionPane.OK_OPTION){
                        client.deconnecter();
                    }
                    break;
                case "CONFIGURER":
                    //TODO : compléter (question 1.3)
                    if (client.isConnecte()) {
                        panConfServeur = new PanneauConfigServeur(client.getAdrServeur(), client.getPortServeur());
                        res = JOptionPane.showConfirmDialog(fenetre, panConfServeur, "Configuration serveur", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(res == JOptionPane.OK_OPTION) {
                            //client.deconnecter();
                            while(true){
                                try{
                                    int serv = Integer.parseInt(panConfServeur.getPortServeur());
                                    client.setAdrServeur(panConfServeur.getAdresseServeur());
                                    client.setPortServeur(serv);
                                    break;
                                } catch (Exception e) {
                                    res = JOptionPane.showConfirmDialog(fenetre, panConfServeur, "Configuration serveur", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                                    System.out.println(panConfServeur.getAdresseServeur());
                                    System.out.println(panConfServeur.getPortServeur());
                                }
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(fenetre,  "Vous n'etes pas connecte!");
                        break;
                    }
                    break;
                case "QUITTER":
                    if (client.isConnecte()) {
                        res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                                "Confirmation Quitter",
                                JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                        if (res == JOptionPane.OK_OPTION){
                            client.deconnecter();
                            System.exit(0);
                        }
                    }
                    else
                        System.exit(0);
                    break;
            }
        }
    }
}