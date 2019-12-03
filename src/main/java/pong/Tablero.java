package pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class Tablero extends JPanel implements Runnable { 
     
    private static final long serialVersionUID = 1L;
    private String pantallaActiva;
    private int scoreJugador1;
    private int scoreJugador2;
    private Sonidos sonidos;
    private Map<String,Pelota> pelotas;
    private Map<String,Raqueta> raquetas;
    private int anchoJuego;
    private int largoJuego;
    
    public Tablero(int anchoJuego, int largoJuego) {
        this.anchoJuego = anchoJuego;
        this.largoJuego = largoJuego;
        this.setBackground(Color.BLACK);
        this.pantallaActiva = "juego";
        agregarRaquetas();
        agregarPelotas();
        agregarSonidos();
        getBounds();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(anchoJuego, largoJuego);
    }
    
    private void agregarRaquetas() {
        this.raquetas = new HashMap<String,Raqueta>();
        raquetas.put("raquetaDerecha",new RaquetaDerecha(794 - 10 - 10, 200,10,60));
        raquetas.put("raquetaIzquierda",new RaquetaIzquierda(0, 200,10,60));
    }
    
    private void agregarPelotas() {
        this.pelotas = new HashMap<String,Pelota>();
        pelotas.put("pelota1", new Pelota (300,300,1,1,15,15));
        pelotas.put("pelota2",new Pelota (350,350,1,1,15,15));
    }
    
    private void agregarSonidos() {
        this.sonidos = new Sonidos();
        sonidos.agregarSonido("rebote_1", "rebote_pelota1.wav");
        sonidos.agregarSonido("rebote_2", "rebote_pelota2.wav");
        sonidos.agregarSonido("falta", "falta.wav");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 
        g2.setPaint(Color.WHITE);
        int jugadorGanador = obtenerJugadorGanador();
        if (jugadorGanador != 0 ) {
            pantallaActiva = "finJuego";
        }
        if (pantallaActiva.equals("juego")) {
            dibujarPuntaje(g2);
            dibujarJuego(g2);
        }
        if (pantallaActiva.equals("finJuego")) {
            dibujarFinJuego(g2);
            escucharEventosTeclasFinDeJuego();
        }
    }

    private void dibujarJuego(Graphics2D g) {
        Line2D.Double linea = new Line2D.Double(getBounds().getCenterX(), 0, getBounds().getCenterX(), getBounds().getMaxY());
        g.draw(linea);
        g.fill(raquetas.get("raquetaDerecha").getRaqueta());
        g.fill(raquetas.get("raquetaIzquierda").getRaqueta());
        for (Pelota pelota : pelotas.values()) {
            g.fill(pelota.getPelota());
        }
    }

    private void actualizarJuego() {
        if (pantallaActiva.equals("juego")) {
            for (Pelota pelota : pelotas.values()) {
                for (Raqueta raqueta : raquetas.values()) {
                    if (hayColisionRaquetaConPelota(pelota,raqueta)) {
                        accionColisionRaquetaConPelota(pelota,raqueta);
                    }    
                }
            }        
            for (Pelota pelota : pelotas.values()) {
                if (hayColisionPelotaConParteDerecha(pelota)) {
                    accionColisionPelotaConParteDerecha(pelota);
                }
                if (hayColisionPelotaConParteIzquierda(pelota)) {
                    accionColisionPelotaConParteIzquierda(pelota);
                }
                if (hayColisionPelotaConParteSuperior(pelota) || hayColisionPelotaConParteInferior(pelota)) {
                    accionColisionPelotaConParteSuperiorOInderior(pelota);
                }
                pelota.moverse();
            }
            for (Raqueta raqueta : raquetas.values()) {
                raqueta.mover(getBounds());
            }
        }    
    }

    private void dibujarPuntaje(Graphics2D g) {
        Graphics2D g1 = g, g2 = g, g3 = g;
        Font score = new Font("Arial", Font.BOLD, 30);
        Font reiniciar = new Font("Arial", Font.BOLD, 30);
        g.setFont(score);
        g3.setFont(reiniciar);
        g1.drawString(Integer.toString(scoreJugador1), (float) getBounds().getCenterX() - 50, 30);
        g2.drawString(Integer.toString(scoreJugador2), (float) getBounds().getCenterX() + 25, 30);
    }
    
    private void dibujarFinJuego(Graphics2D g) {
        g.drawString("GANA El JUGADOR 1", (float) getBounds().getCenterX() - 180, (float) getBounds().getCenterY() - 100);
        g.drawString("F5 para volver a jugar", (float) getBounds().getCenterX() - 180, (float) getBounds().getCenterY() - 30);
        g.drawString("F8 para salir", (float) getBounds().getCenterX() - 180, (float) getBounds().getCenterY());
    }
    
    private int obtenerJugadorGanador() {
        if (scoreJugador1 >= 5 && scoreJugador1 > scoreJugador2 + 1) {
            return 1;
        } 
        if (scoreJugador2 >= 5 && scoreJugador2 > scoreJugador1 + 1) {
            return 2;
        }
        return 0;
    }
    
    private void escucharEventosTeclasFinDeJuego() {
        if (EventoTeclado.endGame) {
            System.exit(0);
        }
        if (EventoTeclado.newGame) {
            scoreJugador1 = 0;
            scoreJugador2 = 0;
            pantallaActiva = "juego";
        }    
    }
    
    private boolean hayColisionRaquetaConPelota(Pelota pelota, Raqueta raqueta) {
        return pelota.getPelota().intersects(raqueta.getRaqueta());
    }
    
    private boolean hayColisionPelotaConParteIzquierda(Pelota pelota) {
        return pelota.getX() < getBounds().getMinX();
    }
    
    private boolean hayColisionPelotaConParteDerecha(Pelota pelota) {
        return pelota.getX() + pelota.getAncho() >= getBounds().getMaxX();
    }
    
    private boolean hayColisionPelotaConParteSuperior(Pelota pelota) {
        return pelota.getY() < getBounds().getMinY();
    }
    
    private boolean hayColisionPelotaConParteInferior(Pelota pelota) {
        return pelota.getY() + pelota.getLargo() >= getBounds().getMaxY();
    }
    
    private void accionColisionRaquetaConPelota(Pelota pelota, Raqueta raqueta) {
        if (pelota.getDx() > 0) {
            pelota.setX(pelota.getX()-1);
        } else {
            pelota.setX(pelota.getX()+1);
        }
        pelota.setDx(-pelota.getDx());
        sonidos.tocarSonido("rebote_1");
        pelota.setDx(pelota.getDx()+0.1);
    }
    
    private void accionColisionPelotaConParteIzquierda(Pelota pelota) {
        scoreJugador2++;
        Raqueta raqueta = raquetas.get("raquetaIzquierda");
        raqueta.setAlto(raqueta.getAlto()-1);
        pelota.setX((int)getBounds().getCenterX());
        pelota.setY((int)getBounds().getCenterY());
        sonidos.tocarSonido("falta");
    }
    
    private void accionColisionPelotaConParteDerecha(Pelota pelota) {
        scoreJugador1++;
        Raqueta raqueta = raquetas.get("raquetaIzquierda");
        raqueta.setAlto(raqueta.getAlto()-1);
        pelota.setX((int)getBounds().getCenterX());
        pelota.setY((int)getBounds().getCenterY());
        sonidos.tocarSonido("falta");
    }
    
    private void accionColisionPelotaConParteSuperiorOInderior(Pelota pelota) {
        pelota.setDy(-pelota.getDy());
        sonidos.tocarSonido("rebote_1");
    }

    @Override
    public void run() {
        while (true) {
            actualizarJuego();
            repaint();
            esperar(4);
        }
    }
    
    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }
    
}