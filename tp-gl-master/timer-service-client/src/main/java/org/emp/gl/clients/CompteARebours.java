package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class CompteARebours implements TimerChangeListener {

    private final String nom;
    private int valeur;
    private final TimerService timer;

    public CompteARebours(String nom, int valeurInitiale, TimerService timer) {
        this.nom = nom;
        this.valeur = valeurInitiale;
        this.timer = timer;

        // S’abonne aux changements du temps
        timer.addTimeChangeListener(this);
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        // On ne traite que le changement de seconde
        if (TimerChangeListener.SECONDE_PROP.equals(prop)) {
            if (valeur > 0) {
                valeur--;
                System.out.println(nom + " : " + valeur + " secondes restantes");

                // Si le compteur atteint zéro → se désinscrire
                if (valeur == 0) {
                    System.out.println(nom + " a terminé !");
                    timer.removeTimeChangeListener(this);
                }
            }
        }
    }
}
