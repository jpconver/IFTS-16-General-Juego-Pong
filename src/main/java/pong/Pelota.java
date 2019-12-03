package pong;

import java.awt.geom.Rectangle2D;

public class Pelota {

    private double x;
    private double y;
    private int ancho;
    private int largo;
    private double dx;
    private double dy;

    public Pelota(double x, double y, double dx, double dy, int ancho, int largo) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.ancho = ancho;
        this.largo = largo;
    }

    public Rectangle2D getPelota() {
        return new Rectangle2D.Double(x, y, ancho, largo);
    }

    public void moverse() {
        x += dx;
        y += dy;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
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

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

}
