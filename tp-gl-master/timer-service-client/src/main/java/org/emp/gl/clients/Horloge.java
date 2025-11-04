package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;

public class Horloge implements TimerChangeListener {

    String name;
    TimerService timerService;

    public Horloge(String name, TimerService timerService) {
        this.name = name;
        this.timerService = timerService;

        // S'abonner aux changements
        timerService.addTimeChangeListener(this);

        System.out.println("Horloge " + name + " initialisée !");
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if (prop.equals(TimerChangeListener.SECONDE_PROP)) {
            afficherHeure();
        }
    }

    public void afficherHeure() {
        System.out.println(name + " affiche : " +
                String.format("%02d:%02d:%02d",
                        timerService.getHeures(),
                        timerService.getMinutes(),
                        timerService.getSecondes()));
    }
}
