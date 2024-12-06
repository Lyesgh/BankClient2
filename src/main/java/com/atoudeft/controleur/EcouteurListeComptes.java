package com.atoudeft.controleur;

import com.atoudeft.client.Client;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class EcouteurListeComptes extends MouseAdapter {

    private Client client;
    public EcouteurListeComptes(Client client) {
        this.client = client;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        //à compléter
        if(evt.getClickCount()==2){
            //client.envoyer("SELECT epargne");
            Object src = evt.getSource();
            if(src instanceof JList){
                String selection = String.valueOf(((JList<?>) src).getSelectedValue());
                String typeCompteBancaireSelection = selection.substring(selection.indexOf("[")+1, selection.indexOf("]")).toLowerCase();
                client.envoyer("SELECT "+typeCompteBancaireSelection);
            }
        }
    }
}
