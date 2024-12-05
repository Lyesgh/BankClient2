package com.atoudeft.client;

import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;
import com.atoudeft.vue.PanneauPrincipal;
import com.programmes.MainFrame;

import javax.swing.*;

public class GestionnaireEvenementClient2 implements GestionnaireEvenement {
    private Client client;
    private PanneauPrincipal panneauPrincipal;

    /**
     * Construit un gestionnaire d'événements pour un client.
     *
     * @param client Client Le client pour lequel ce gestionnaire gère des événements
     */
    public GestionnaireEvenementClient2(Client client, PanneauPrincipal panneauPrincipal) {

        this.client = client;
        this.panneauPrincipal = panneauPrincipal;
        this.client.setGestionnaireEvenement(this);
    }
    @Override
    public void traiter(Evenement evenement) {
        Object source = evenement.getSource();
        //Connexion cnx;
        String typeEvenement, arg, str, res;
        int i;
        String[] t;
        MainFrame fenetre;

        if (source instanceof Connexion) {
            //cnx = (Connexion) source;
            typeEvenement = evenement.getType();
            switch (typeEvenement) {
                /******************* COMMANDES GÉNÉRALES *******************/
                case "END": //Le serveur demande de fermer la connexion
                    client.deconnecter(); //On ferme la connexion
                    break;
                /******************* CREATION et CONNEXION *******************/
//                case "HIST": //Le serveur a renvoyé
//                    panneauPrincipal.setVisible(true);
//                    JOptionPane.showMessageDialog(null,"Panneau visible");
//                    cnx.envoyer("LIST");
//                    arg = evenement.getArgument();
//                    break;
                case "OK":
                    panneauPrincipal.setVisible(true);
                    fenetre = (MainFrame)panneauPrincipal.getTopLevelAncestor();
                    fenetre.setTitle(MainFrame.TITRE);//+" - Connecté"
                    break;
                case "NOUVEAU":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Nouveau refusé");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        panneauPrincipal.ajouterCompte(str);
                        System.out.println("String: "+ str);
                    }
                    break;
                case "CONNECT":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Connexion refusée");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        t = str.split(":");
                        for (String s:t) {
                            panneauPrincipal.ajouterCompte(s.substring(0,s.indexOf("]")+1));
                        }
                    }
                    break;
                /******************* SÉLECTION DE COMPTES *******************/
                case "EPARGNE" :
                    arg = evenement.getArgument();
                    System.out.println("this is arg :  "+arg);
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Compte epargne existe.");
                    }
                    else {
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        System.out.println("String Epargne: "+ str);
                        panneauPrincipal.ajouterCompte(str+"[EPARGNE] 0.0");

                    }
                    break;
                case "SELECT" :
                    arg = evenement.getArgument();
                    t = arg.split(" ");
                    panneauPrincipal.labelSolde().setText("Solde: "+t[t.length-1]);
                    JOptionPane.showMessageDialog(panneauPrincipal,"SELECT "+arg);
                    break;

                /******************* OPÉRATIONS BANCAIRES *******************/
                case "DEPOT" :
                    //cnx.envoyer("DEPOT 1000");
                    arg = evenement.getArgument();
                    t = arg.split(" ");
                    panneauPrincipal.labelSolde().setText("Solde : "+t[t.length-1]);
                    //client.envoyer("DEPOT 1500");

                    /*if(res != null) {
                        while(true){
                            try{
                                Integer.parseInt(res);
                                client.envoyer("DEPOT"+res);
                                JOptionPane.showMessageDialog(null,"Un motant de "+res+" est déposé");
                                break;
                            } catch (Exception ex) {
                                res = JOptionPane.showInputDialog(null, "Montant", JOptionPane.QUESTION_MESSAGE);

                            }
                        }
                   }
                    else {
                        JOptionPane.showMessageDialog(null,"Opération annulée ");
                    }*/

                    JOptionPane.showMessageDialog(panneauPrincipal,"DEPOT "+arg);
                    break;
                case "RETRAIT" :
                    arg = evenement.getArgument();
                    t = arg.split(" ");
                    panneauPrincipal.labelSolde().setText("Solde : "+t[t.length-1]);
                    /*res = JOptionPane.showInputDialog(panneauPrincipal, "Retrait montant", JOptionPane.QUESTION_MESSAGE);
                    if(res != null) {
                        while(true){
                            try{
                                Integer.parseInt(res);
                                client.envoyer("RETRAIT "+res);
                                panneauPrincipal.labelSolde().repaint();
                                break;
                            } catch (Exception e) {
                                res = JOptionPane.showInputDialog(panneauPrincipal, "Montant invalide", JOptionPane.QUESTION_MESSAGE);

                            }
                        }
                    }*/
                    JOptionPane.showMessageDialog(panneauPrincipal,"RETRAIT "+arg);
                    break;
                case "FACTURE" :
                    arg = evenement.getArgument();
                    t = arg.split(" ");
                    panneauPrincipal.labelSolde().setText("Solde : "+t[t.length-1]);
                    JOptionPane.showMessageDialog(panneauPrincipal,"FACTURE " + arg);
                    break;
                case "TRANSFER" :
                    arg = evenement.getArgument();
                    t = arg.split(" ");
                    panneauPrincipal.labelSolde().setText("Solde : "+t[t.length-1]);
                    JOptionPane.showMessageDialog(panneauPrincipal,"TRANSFER " + arg);
                    break;
                case "HIST":
                    arg = evenement.getArgument();
                    panneauPrincipal.getPhr().getZoneHist().setText(arg);
                    System.out.println("la taille du panneau : "+ panneauPrincipal.getPhr().getSize());
                    System.out.println("la taille du panneau : "+ panneauPrincipal.getPhr().getSize());

                    JOptionPane.showMessageDialog(panneauPrincipal, panneauPrincipal.getPhr(), "Historique Des Opérations:", JOptionPane.OK_OPTION | JOptionPane.PLAIN_MESSAGE);
                    break;
                /******************* TRAITEMENT PAR DÉFAUT *******************/
                default:
                    System.out.println("RECU : "+evenement.getType()+" "+evenement.getArgument());
            }
        }
    }
}