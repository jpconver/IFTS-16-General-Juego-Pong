package pong;

import java.awt.event.*;

public class EventoTeclado extends KeyAdapter {

    static boolean w, s, up, down, endGame, newGame;

    @Override
    public void keyPressed(KeyEvent e) {

        int id = e.getKeyCode();
        if (id == KeyEvent.VK_W) {
            w = true;
        }
        if (id == KeyEvent.VK_S) {
            s = true;
        }
        if (id == KeyEvent.VK_UP) {
            up = true;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (id == KeyEvent.VK_F8) {
            endGame = true;
        }
        if (id == KeyEvent.VK_F5) {
            newGame = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int id = e.getKeyCode();
        if (id == KeyEvent.VK_W) {
            w = false;
        }
        if (id == KeyEvent.VK_S) {
            s = false;
        }
        if (id == KeyEvent.VK_UP) {
            up = false;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = false;
        }
        if (id == KeyEvent.VK_F8) {
            endGame = false;
        }
        if (id == KeyEvent.VK_F5) {
            newGame = false;
        }

    }
}
