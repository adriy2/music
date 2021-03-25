package fr.pgah.java.unbrco.ui.outils;

import fr.pgah.java.unbrco.lecteurs.LecteurDeDessin;
import fr.pgah.java.unbrco.ui.EditeurDeFormes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OutilJouerDessin extends Outil {

  public OutilJouerDessin(EditeurDeFormes editeur, JComponent parent) {
    super(editeur, parent);
  }

  @Override
  protected void creerBouton(JComponent parent) {
    bouton = new JButton("Jouer tout le dessin");
    bouton = customiserButton(bouton);
    ajouterAuParent(parent);
  }

  @Override
  protected void ajouterListener() {
    bouton.addActionListener(new OutilJouerDessinClicHandler());
  }

  private void jouerDessin() {
    final Timer timer = new Timer(2, null);
    ActionListener listener = new LecteurDeDessin(editeur.getDessinCourant(), timer);
    timer.addActionListener(listener);
    timer.setInitialDelay(0);
    timer.start(); // appelle LecteurDeDessin.actionPerformed périodiquement jusqu'à fin du timer
  }

  private class OutilJouerDessinClicHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      jouerDessin();
    }
  }

}
