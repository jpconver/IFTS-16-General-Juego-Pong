
package pong;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class RaquetaDerecha implements Raqueta {

    private int x, y;
    private int ancho;
    private int alto;

    public RaquetaDerecha(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    @Override
    public Rectangle2D getRaqueta() {
        return new Rectangle2D.Double(x, y, ancho, alto);
    }

    @Override
    public void mover(Rectangle limites) {
        if (EventoTeclado.up && y > limites.getMinY()) {
            y--;
        }
        if (EventoTeclado.down && y < limites.getMaxY() - alto) {
            y++;
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getAncho() {
        return ancho;
    }

    @Override
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    @Override
    public int getAlto() {
        return alto;
    }

    @Override
    public void setAlto(int alto) {
        this.alto = alto;
    }

}
