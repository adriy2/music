package fr.pgah.java.unbrco.ui.outils;

import fr.pgah.java.unbrco.model.Forme;
import fr.pgah.java.unbrco.ui.EditeurDeFormes;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class OutilSupprimer extends Outil {

  private Forme formeASupprimer;

  public OutilSupprimer(EditeurDeFormes editeur, JComponent parent) {
    super(editeur, parent);
    formeASupprimer = null;
  }

  @Override
  protected void creerBouton(JComponent parent) {
    bouton = new JButton("Supprimer");
    ajouterAuParent(parent);
  }

  @Override
  protected void ajouterListener() {
    bouton.addActionListener(new OutilSupprimerClicHandler());
  }

  @Override
  public void pressDansZoneDessin(MouseEvent e) {
    formeASupprimer = editeur.getFormeEn(e.getPoint());
    if (formeASupprimer != null) {
      formeASupprimer.selectionnerEtJouer();
    }
  }

  @Override
  public void releaseDansZoneDessin(MouseEvent e) {
    if (formeASupprimer != null) {
      formeASupprimer.deselectionnerEtStopper();
      editeur.supprimerDuDessin(formeASupprimer);
      formeASupprimer = null;
    }
  }

  @Override
  public void dragDansZoneDessin(MouseEvent e) {
    formeASupprimer = editeur.getFormeEn(e.getPoint());
    if (formeASupprimer != null) {
      formeASupprimer.selectionnerEtJouer();
    }
  }

  private class OutilSupprimerClicHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      editeur.setOutilActif(OutilSupprimer.this);
    }
  }
}
