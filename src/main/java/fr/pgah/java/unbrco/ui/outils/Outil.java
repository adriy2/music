package fr.pgah.java.unbrco.ui.outils;

import fr.pgah.java.unbrco.ui.EditeurDeFormes;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.event.MouseEvent;

public abstract class Outil {
  protected JButton bouton;
  protected EditeurDeFormes editeur;

  private boolean actif;

  public Outil(EditeurDeFormes editeur, JComponent parent) {
    this.editeur = editeur;
    creerBouton(parent);
    ajouterAuParent(parent);
    actif = false;
    ajouterListener();
  }

  protected JButton customiserButton(JButton bouton) {
    bouton.setBorderPainted(true);
    bouton.setFocusPainted(true);
    bouton.setContentAreaFilled(true);
    return bouton;
  }

  public boolean estActif() {
    return actif;
  }

  public void activer() {
    actif = true;
  }

  public void desactiver() {
    actif = false;
  }

  protected abstract void creerBouton(JComponent parent);
  
  protected abstract void ajouterListener();

  public void ajouterAuParent(JComponent parent) {
    parent.add(bouton);
  }

  public void pressDansZoneDessin(MouseEvent e) {
  }

  public void releaseDansZoneDessin(MouseEvent e) {
  }

  public void clicDansZoneDessin(MouseEvent e) {
  }

  public void dragDansZoneDessin(MouseEvent e) {
  }
}
