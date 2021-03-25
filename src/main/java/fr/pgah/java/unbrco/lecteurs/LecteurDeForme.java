package fr.pgah.java.unbrco.lecteurs;

import javax.swing.Timer;
import fr.pgah.java.unbrco.model.Dessin;
import fr.pgah.java.unbrco.model.Forme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LecteurDeForme implements ActionListener {

  private Forme forme;
  private Dessin dessin;
  private Timer t;
  private int colonneCourante;

  public LecteurDeForme(Dessin dessin, Forme forme, Timer t) {

    this.forme = forme;
    this.dessin = dessin;
    this.t = t;

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    jouerColonne();
    incrementerColonne();
    arreterSiFormeFinie();
  }

  private void incrementerColonne() {
    colonneCourante += 1;
  }

  private void jouerColonne() {
    forme.setColonneJouee(colonneCourante);
    forme.selectionnerEtJouer();
    dessin.repaint();
  }

  private void arreterSiFormeFinie() {
    if (colonneCourante > forme.getLongueur()) {
      forme.deselectionnerEtStopper();
      forme.setColonneJouee(0);
      dessin.repaint();
      t.stop();
    }
  }
}
