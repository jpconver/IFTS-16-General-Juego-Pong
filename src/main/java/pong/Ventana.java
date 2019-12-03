package pong;

import javax.swing.JFrame;

public class Ventana extends JFrame {

    private static final long serialVersionUID = 1L;

    public Ventana() {
        setTitle("Pong");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        Tablero tablero = new Tablero(800,500);
        add(tablero);
        addKeyListener(new EventoTeclado());
        this.pack();
        new Thread(tablero).start();
    }

}
