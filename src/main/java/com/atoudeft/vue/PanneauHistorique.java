package com.atoudeft.vue;

import javax.swing.*;

public class PanneauHistorique extends JPanel {

    private JTextArea zoneHist;
    private JScrollPane defileur;

    public PanneauHistorique(){
        zoneHist= new JTextArea(25, 50);
        zoneHist.setEditable(false);
        defileur = new JScrollPane(zoneHist);
        //this.add(zoneHist);
        this.add(defileur);
    }

    public JTextArea getZoneHist(){
        return zoneHist;
    }

}
