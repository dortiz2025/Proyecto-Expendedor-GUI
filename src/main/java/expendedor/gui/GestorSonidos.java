package expendedor.gui;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton encargado de cargar y reproducir efectos de sonido.
 */
public class GestorSonidos {
    private static GestorSonidos instancia;
    private final Map<String, URL> sonidos;

    private GestorSonidos() {
        this.sonidos = new HashMap<>();
        cargarSonidos();
    }

    /**
     * Devuelve la única instancia del gestor.
     * @return Instancia de GestorSonidos.
     */
    public static GestorSonidos getInstancia() {
        if (instancia == null) {
            instancia = new GestorSonidos();
        }
        return instancia;
    }

    /**
     * Registra los sonidos del sistema en memoria.
     */
    private void cargarSonidos() {
        registrar("beber", "/sonidos/beber.wav");
        registrar("masticar", "/sonidos/masticar.wav");
    }

    /**
     * Vincula un identificador con la ruta de un recurso de audio.
     * @param nombre Clave de identificación del sonido.
     * @param ruta Ruta del recurso dentro del classpath.
     */
    private void registrar(String nombre, String ruta) {
        URL url = getClass().getResource(ruta);
        if (url != null) {
            sonidos.put(nombre, url);
        } else {
            System.err.println("Sonido no encontrado: " + ruta);
        }
    }

    /**
     * Reproduce el archivo de audio asociado a la clave proporcionada.
     * @param nombre Clave del sonido a reproducir.
     */
    public void reproducir(String nombre) {
        URL url = sonidos.get(nombre);
        if (url == null) {
            System.err.println("No hay sonido registrado con nombre: " + nombre);
            return;
        }

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);

            clip.addLineListener(evento -> {
                if (evento.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            clip.start();

        } catch (UnsupportedAudioFileException e) {
            System.err.println("Formato no soportado para '" + nombre + "': " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de lectura para '" + nombre + "': " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Línea de audio no disponible para '" + nombre + "': " + e.getMessage());
        }
    }
}