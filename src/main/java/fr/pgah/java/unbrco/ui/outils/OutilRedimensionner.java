package fr.pgah.java.unbrco.ui.outils;

import fr.pgah.java.unbrco.model.Forme;
import fr.pgah.java.unbrco.ui.EditeurDeFormes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class OutilRedimensionner extends Outil {

  private Forme formeARedimensionner;

  public OutilRedimensionner(EditeurDeFormes editeur, JComponent parent) {
    super(editeur, parent);
    formeARedimensionner = null;
  }

  @Override
  protected void creerBouton(JComponent parent) {
    bouton = new JButton("Redimensionner");
    ajouterAuParent(parent);
  }

  @Override
  protected void ajouterListener() {
    bouton.addActionListener(new OutilRedimensionnerClicHandler());
  }

  @Override
  public void pressDansZoneDessin(MouseEvent e) {
    formeARedimensionner = editeur.getFormeEn(e.getPoint());
    if (formeARedimensionner != null) {
      formeARedimensionner.selectionnerEtJouer();
    }
  }

  @Override
  public void releaseDansZoneDessin(MouseEvent e) {
    if (formeARedimensionner != null) {
      formeARedimensionner.deselectionnerEtStopper();
      formeARedimensionner = null;
    }
  }

  @Override
  public void dragDansZoneDessin(MouseEvent e) {
    if (formeARedimensionner != null) {
      formeARedimensionner.setLimites(e.getPoint());
    }
  }

  private class OutilRedimensionnerClicHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      editeur.setOutilActif(OutilRedimensionner.this);
    }
  }
}
