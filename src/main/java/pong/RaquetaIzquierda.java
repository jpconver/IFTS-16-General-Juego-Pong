
package pong;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class RaquetaIzquierda implements Raqueta {

    private int x, y;
    private int ancho;
    private int alto;

    public RaquetaIzquierda(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public Rectangle2D getRaqueta() {
        return new Rectangle2D.Double(x, y, ancho, alto);
    }

    public void mover(Rectangle limites) {
        if (EventoTeclado.w && y > limites.getMinY()) {
            y--;
        }
        if (EventoTeclado.s && y < limites.getMaxY() - alto) {
            y++;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

}
