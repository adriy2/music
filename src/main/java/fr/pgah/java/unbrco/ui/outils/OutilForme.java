package fr.pgah.java.unbrco.ui.outils;

import fr.pgah.java.unbrco.model.Forme;
import fr.pgah.java.unbrco.ui.EditeurDeFormes;

import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class OutilForme extends Outil {

  private Forme forme;

  public OutilForme(EditeurDeFormes editeur, JComponent parent) {
    super(editeur, parent);
    forme = null;
  }

  @Override
  protected void creerBouton(JComponent parent) {
    bouton = new JButton("Forme");
    bouton = customiserButton(bouton);
  }

  @Override
  protected void ajouterListener() {
    bouton.addActionListener(new OutilFormeClicHandler());
  }

  @Override
  public void pressDansZoneDessin(MouseEvent e) {
    forme = new Forme(e.getPoint(), editeur.getMidiSynth());
    forme.selectionnerEtJouer();
    forme.setLimites(e.getPoint());
    editeur.ajouterAuDessin(forme);
  }

  @Override
  public void releaseDansZoneDessin(MouseEvent e) {
    forme.deselectionnerEtStopper();
    forme = null;
  }

  @Override
  public void dragDansZoneDessin(MouseEvent e) {
    forme.setLimites(e.getPoint());
  }

  private class OutilFormeClicHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      editeur.setOutilActif(OutilForme.this);
    }
  }
}
