package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;


import java.util.Random;

public class App {

    public static void main(String[] args) {
        testDuTimeService();
    }

    private static void testDuTimeService() {
        // 1️⃣ Créer le service de temps (le "timer")
        DummyTimeServiceImpl timer = new DummyTimeServiceImpl();


        // 2️⃣ Créer plusieurs horloges observant le même timer
        Horloge horloge1 = new Horloge("Num 1", timer);
        Horloge horloge2 = new Horloge("Num 2", timer);

        // 3️⃣ Créer 10 compteurs à rebours avec valeurs aléatoires entre 10 et 20 secondes
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            int valeurInitiale = 10 + random.nextInt(11); // 10 à 20 inclus
            new CompteARebours("CompteARebours " + i, valeurInitiale, timer);
        }

        // 4️⃣ Le timer démarre automatiquement et notifie tout le monde
        System.out.println("Simulation en cours... (appuyez sur CTRL+C pour arrêter)");
    }

    // (optionnel) pour nettoyer la console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
