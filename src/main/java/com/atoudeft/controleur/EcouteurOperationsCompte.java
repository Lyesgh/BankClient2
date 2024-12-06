package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauDesOperations;
import com.atoudeft.vue.PanneauHistorique;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;
    private PanneauDesOperations pdo;
    private PanneauHistorique ph;

    public EcouteurOperationsCompte(Client client) {this.client = client;}

    @Override
    public void actionPerformed(ActionEvent e) {
        //à compléter :
        if(e.getSource() instanceof JButton){
            if(e.getActionCommand().equals("EPARGNE")){
                pdo.getCard().show(pdo, "vide");
                client.envoyer("EPARGNE");
            }
            //client.envoyer(e.getActionCommand());
            if(e.getActionCommand().equals("DEPOT")){
                pdo.getCard().show(pdo, "depot");
                /*String res = JOptionPane.showInputDialog(null, "Depot montant", JOptionPane.QUESTION_MESSAGE);
                if(res != null) {
                    while(true){
                        try{
                            Integer.parseInt(res);
                            client.envoyer("DEPOT "+res);
                            //JOptionPane.showMessageDialog(null,"Un montant de "+res+" est déposé");
                            break;
                        } catch (Exception ex) {
                            res = JOptionPane.showInputDialog(null, "Montant", JOptionPane.QUESTION_MESSAGE);

                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Opération annulée ");
                }*/
            }
        }

        if(e.getActionCommand().equals("RETRAIT")){
            pdo.getCard().show(pdo, "retrait");
        }
        if(e.getActionCommand().equals("TRANSFER")){
            pdo.getCard().show(pdo, "transfer");
        }
        if(e.getActionCommand().equals("FACTURE")){
            pdo.getCard().show(pdo, "facture");
        }
        if(e.getActionCommand().equals("HIST")){
            client.envoyer("HIST");
            pdo.getCard().show(pdo, "vide");
        }

        if(e.getActionCommand().equals("deposer")){
            String montant = pdo.getJTFDepot().getText();
            try{
                Double.parseDouble(montant);
                client.envoyer("DEPOT "+montant);
                pdo.getJTFDepot().setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Montant invalide! ");
            }
        }

        if(e.getActionCommand().equals("retirer")){
            String montant = pdo.getJTFRetrait().getText();
            try{
                Double.parseDouble(montant);
                client.envoyer("RETRAIT "+montant);
                pdo.getJTFRetrait().setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Montant invalide! ");
            }

        }

        if(e.getActionCommand().equals("transferer")) {
            String montant = pdo.getJTFTransfer().getText();
            String compte = pdo.getJTFNumeroCompte().getText();
            try {
                Double.parseDouble(montant);
                client.envoyer("TRANSFER " + montant + " " + compte);
                pdo.getJTFTransfer().setText("");
                pdo.getJTFNumeroCompte().setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Montant invalide! ");
            }
        }

        if(e.getActionCommand().equals("facturer")) {
            String montant = pdo.getJTFFacture().getText();
            String numFacture = pdo.getJTFNumFacture().getText();
            String descFacture = pdo.getJTFDescription().getText();
            try {
                Double.parseDouble(montant);
                client.envoyer("FACTURE " + montant + " " + numFacture+" "+ descFacture);
                pdo.getJTFFacture().setText("");
                pdo.getJTFNumFacture().setText("");
                pdo.getJTFDescription().setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Montant invalide! ");
            }
        }

    }

    public void setPDO(PanneauDesOperations pp){
        this.pdo=pp;
    }

    public void setPHR(PanneauHistorique ph){
        this.ph = ph;
    }
}
