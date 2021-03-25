package fr.pgah.java.unbrco.ui.outils;

import fr.pgah.java.unbrco.lecteurs.LecteurDeForme;
import fr.pgah.java.unbrco.model.Forme;
import fr.pgah.java.unbrco.ui.EditeurDeFormes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class OutilJouerForme extends Outil {

  public OutilJouerForme(EditeurDeFormes editeur, JComponent parent) {
    super(editeur, parent);
  }

  @Override
  public void pressDansZoneDessin(MouseEvent e) {
    jouerFormeEn(e.getPoint());
  }

  @Override
  protected void creerBouton(JComponent parent) {
    bouton = new JButton("Jouer forme");
    bouton = customiserButton(bouton);
    ajouterAuParent(parent);
  }

  @Override
  protected void ajouterListener() {
    bouton.addActionListener(new OutilJouerFormeClicHandler());
  }

  private void jouerFormeEn(Point p) {
    Forme forme = editeur.getFormeEn(p);
    if (forme != null) {
      final Timer t = new Timer(2, null);
      ActionListener a = new LecteurDeForme(editeur.getDessinCourant(), forme, t);
      t.addActionListener(a);
      t.setInitialDelay(0);
      t.start();
    }
  }

  private class OutilJouerFormeClicHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      editeur.setOutilActif(OutilJouerForme.this);
    }
  }
}
