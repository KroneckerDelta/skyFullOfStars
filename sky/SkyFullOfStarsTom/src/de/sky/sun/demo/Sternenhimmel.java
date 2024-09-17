package de.sky.sun.demo;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Sternenhimmel extends JFrame {

    private static final int BREITE = 800;
    private static final int HOEHE = 600;
    private static final int ANZAHL_STERNE = 200;

    public Sternenhimmel() {
        setTitle("Sternenhimmel");
        setSize(BREITE, HOEHE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        add(new SternenhimmelPanel());
    }

    class SternenhimmelPanel extends JPanel {
        private Random random = new Random();

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setBackground(Color.BLACK);

            g.setColor(Color.WHITE);
            for (int i = 0; i < ANZAHL_STERNE; i++) {
                int x = random.nextInt(BREITE);
                int y = random.nextInt(HOEHE);
                int groesse = random.nextInt(3) + 1;
                g.fillOval(x, y, groesse, groesse);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Sternenhimmel sternenhimmel = new Sternenhimmel();
            sternenhimmel.setVisible(true);
        });
    }
}