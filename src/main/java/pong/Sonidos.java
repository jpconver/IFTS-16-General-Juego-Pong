package pong;

import java.applet.AudioClip;
import java.util.HashMap;
import java.util.Map;

public class Sonidos {

    private Map<String, AudioClip> sonidos;

    public Sonidos() {
        this.sonidos = new HashMap<String, AudioClip>();
    }

    public void agregarSonido(String nombre, String archivo) {
        sonidos.put(nombre, java.applet.Applet.newAudioClip(getClass().getResource("/" + archivo)));
    }

    public void tocarSonido(String nombre) {
        this.sonidos.get(nombre).play();
    }

}
