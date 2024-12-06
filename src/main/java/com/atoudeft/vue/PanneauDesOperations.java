package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanneauDesOperations extends JPanel {
    private JLabel montantDepot, montantRetrait, montantTranfer, numeroCompte, montantFacture, numeroFact, descriptionFact;
    private JTextField montantADeposer, montantAretirer, montantAtransferer, compteATransferer, factureApayer, numeroFacture, descriptionFacture;
    private JButton validerDepot, validerRetrait, validerTransfer, validerFacture;
    private GridBagLayout gbl;
    private GridBagConstraints cons;
    private JPanel depotPan, retraitPan, transferPan, facturePan, vide;
    private CardLayout card = new CardLayout(5, 5);

    public PanneauDesOperations(){
        this.setBackground(Color.GRAY);
        this.setLayout(card);

        vide = new JPanel();
        this.add(vide, "vide");

        //panneau pour depot
        gbl = new GridBagLayout();
        cons = new GridBagConstraints();
        cons.insets=new Insets(5,5,5,5);
        depotPan = new JPanel();
        depotPan.setLayout(gbl);

        cons.gridx=0;
        cons.gridy=0;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridheight=1;
        cons.anchor=GridBagConstraints.WEST;

        montantDepot = new JLabel("Montant : ");
        montantADeposer = new JTextField();
        validerDepot = new JButton("Deposer");
        validerDepot.setActionCommand("deposer");
        depotPan.add(montantDepot, cons);

        cons.gridx=1;
        //cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridheight=1;
        cons.anchor=GridBagConstraints.EAST;
        depotPan.add(montantADeposer, cons);
        cons.gridy=1;
        depotPan.add(validerDepot, cons);
        this.add(depotPan, "depot");
        /// //fin depotPan

        //debut retraitPan
        cons.gridx=0;
        cons.gridy=0;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridheight=1;
        cons.anchor=GridBagConstraints.WEST;
        retraitPan = new JPanel();
        retraitPan.setLayout(gbl);

        montantRetrait = new JLabel("Montant : ");
        montantAretirer = new JTextField();
        validerRetrait = new JButton("Retirer");
        validerRetrait.setActionCommand("retirer");
        retraitPan.add(montantRetrait, cons);

        cons.gridx=1;
        //cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridheight=1;
        cons.anchor=GridBagConstraints.EAST;
        retraitPan.add(montantAretirer, cons);
        cons.gridy=1;
        retraitPan.add(validerRetrait, cons);
        this.add(retraitPan, "retrait");
        /// Fin retraitPan

        /// Debut transferPan
        cons.gridx=0;
        cons.gridy=0;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridheight=1;
        cons.anchor=GridBagConstraints.WEST;
        transferPan = new JPanel();
        transferPan.setLayout(gbl);

        montantTranfer = new JLabel("Montant : ");
        numeroCompte = new JLabel("Compte : ");
        montantAtransferer = new JTextField();
        compteATransferer = new JTextField();
        validerTransfer = new JButton("Transferer");
        validerTransfer.setActionCommand("transferer");

        transferPan.add(montantTranfer, cons);

        cons.gridx=1;
        //cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridheight=1;
        cons.anchor=GridBagConstraints.EAST;
        transferPan.add(montantAtransferer, cons);

        cons.gridx=0;
        cons.gridy=1;
        cons.anchor=GridBagConstraints.WEST;
        transferPan.add(numeroCompte, cons);
        cons.gridx=1;
        cons.anchor=GridBagConstraints.EAST;
        transferPan.add(compteATransferer, cons);
        cons.gridy=2;
        transferPan.add(validerTransfer, cons);
        this.add(transferPan, "transfer");
        ////Fin transferPan

        //Debut facturePan
        cons.gridx=0;
        cons.gridy=0;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridheight=1;
        cons.anchor=GridBagConstraints.WEST;
        facturePan = new JPanel();
        facturePan.setLayout(gbl);

        montantFacture = new JLabel("Montant : ");
        numeroFact = new JLabel("Numero facture : ");
        descriptionFact =new JLabel("Description : ");
        factureApayer = new JTextField();
        numeroFacture = new JTextField();
        descriptionFacture = new JTextField();
        validerFacture = new JButton("Payer facture");
        validerFacture.setActionCommand("facturer");

        facturePan.add(montantFacture, cons);

        cons.gridx = 1;
        facturePan.add(factureApayer, cons);

        cons.gridx = 0;
        cons.gridy = 1;
        facturePan.add(numeroFact, cons);

        cons.gridx = 1;
        facturePan.add(numeroFacture, cons);

        cons.gridx = 0;
        cons.gridy = 2;
        facturePan.add(descriptionFact, cons);

        cons.gridx = 1;
        facturePan.add(descriptionFacture, cons);

        cons.gridy = 3;
        facturePan.add(validerFacture,cons);

        this.add(facturePan,"facture");
        ////Fin facturePan

    }
    public void setEcouteur(ActionListener ecouteur){
        validerDepot.addActionListener(ecouteur);
        validerRetrait.addActionListener(ecouteur);
        validerTransfer.addActionListener(ecouteur);
        validerFacture.addActionListener(ecouteur);
    }

    public CardLayout getCard(){
        return card;
    }

    public JTextField getJTFDepot(){
        return montantADeposer;
    }

    public JTextField getJTFRetrait(){
        return montantAretirer;
    }

    public JTextField getJTFTransfer(){
        return montantAtransferer;
    }
    public JTextField getJTFNumeroCompte(){
        return compteATransferer;
    }

    public JTextField getJTFFacture(){
        return factureApayer;
    }

    public JTextField getJTFNumFacture(){
        return numeroFacture;
    }
    public JTextField getJTFDescription(){
        return descriptionFacture;
    }
}
