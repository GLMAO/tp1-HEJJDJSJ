package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class HorlogeGUI extends JFrame implements TimerChangeListener {

    private final JLabel heureLabel = new JLabel("", SwingConstants.CENTER);
    private final TimerService timer;

    public HorlogeGUI(TimerService timer) {
        this.timer = timer;
        this.timer.addTimeChangeListener(this);

        setTitle("Horloge Graphique");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        heureLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        add(heureLabel, BorderLayout.CENTER);

        updateLabel();
        setVisible(true);
    }

    private void updateLabel() {
        String time = String.format("%02d:%02d:%02d.%d",
                timer.getHeures(),
                timer.getMinutes(),
                timer.getSecondes(),
                timer.getDixiemeDeSeconde());
        heureLabel.setText(time);
    }

    // 🔧 méthode obligatoire de TimerChangeListener (héritée de PropertyChangeListener)
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())
                || TimerChangeListener.DIXEME_DE_SECONDE_PROP.equals(evt.getPropertyName())) {
            SwingUtilities.invokeLater(this::updateLabel);
        }
    }
}
