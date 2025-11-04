package org.emp.gl.time.service.impl;

import java.beans.PropertyChangeEvent;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 * Implémentation du service de temps factice (Dummy)
 */
public class DummyTimeServiceImpl implements TimerService {

    int dixiemeDeSeconde;
    int minutes;
    int secondes;
    int heures;

    // Liste des abonnés (listeners)
    private final List<TimerChangeListener> listeners = new LinkedList<>();

    /**
     * Constructeur : initialise un Timer qui génère un "tic" toutes les 100 ms
     */
    public DummyTimeServiceImpl() {
        setTimeValues();

        // initialise le Timer Java
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timeChanged();
            }
        };

        // le Timer s’exécute toutes les 100 ms
        timer.scheduleAtFixedRate(task, 100, 100);
    }

    private void setTimeValues() {
        LocalTime localTime = LocalTime.now();

        setSecondes(localTime.getSecond());
        setMinutes(localTime.getMinute());
        setHeures(localTime.getHour());
        setDixiemeDeSeconde(localTime.getNano() / 100000000);
    }

    // === Gestion des abonnés ===
    @Override
    public void addTimeChangeListener(TimerChangeListener pl) {
        listeners.add(pl);
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener pl) {
        listeners.remove(pl);
    }

    // === Changement de temps ===
    private void timeChanged() {
        setTimeValues();
    }

    public void setDixiemeDeSeconde(int newDixiemeDeSeconde) {
        if (dixiemeDeSeconde == newDixiemeDeSeconde)
            return;

        int oldValue = dixiemeDeSeconde;
        dixiemeDeSeconde = newDixiemeDeSeconde;
        notifyListeners(TimerChangeListener.DIXEME_DE_SECONDE_PROP, oldValue, dixiemeDeSeconde);
    }

    public void setSecondes(int newSecondes) {
        if (secondes == newSecondes)
            return;

        int oldValue = secondes;
        secondes = newSecondes;
        notifyListeners(TimerChangeListener.SECONDE_PROP, oldValue, secondes);
    }

    public void setMinutes(int newMinutes) {
        if (minutes == newMinutes)
            return;

        int oldValue = minutes;
        minutes = newMinutes;
        notifyListeners(TimerChangeListener.MINUTE_PROP, oldValue, minutes);
    }

    public void setHeures(int newHeures) {
        if (heures == newHeures)
            return;

        int oldValue = heures;
        heures = newHeures;
        notifyListeners(TimerChangeListener.HEURE_PROP, oldValue, heures);
    }

    // ✅ Nouvelle version compatible avec PropertyChangeListener
    private void notifyListeners(String prop, Object oldValue, Object newValue) {
        for (TimerChangeListener listener : new LinkedList<>(listeners)) {
            listener.propertyChange(new PropertyChangeEvent(this, prop, oldValue, newValue));
        }
    }

    // === Getters ===
    @Override
    public int getDixiemeDeSeconde() {
        return dixiemeDeSeconde;
    }

    @Override
    public int getHeures() {
        return heures;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }
}
