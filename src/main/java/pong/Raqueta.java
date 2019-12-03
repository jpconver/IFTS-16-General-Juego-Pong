package pong;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public interface Raqueta {

    Rectangle2D getRaqueta();

    void mover(Rectangle limites);

    int getX();

    void setX(int x);

    int getY();

    void setY(int y);

    int getAncho();

    void setAncho(int ancho);

    int getAlto();

    void setAlto(int alto);

}