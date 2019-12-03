package pong;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Principal {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        Ventana ventana = new Ventana();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
    }

}
