package org.emp.gl.timer.service;

import java.beans.PropertyChangeListener;

/**
 *
 * @author tina
 */
public interface TimerChangeListener extends PropertyChangeListener {

    String DIXEME_DE_SECONDE_PROP = "dixième";
    String SECONDE_PROP = "seconde";
    String MINUTE_PROP = "minute";
    String HEURE_PROP = "heure";

    // Plus besoin de redéfinir propertyChange ici
    // car elle vient de PropertyChangeListener
}
