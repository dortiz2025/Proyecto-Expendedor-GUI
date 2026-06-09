package expendedor.gui;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GestorSonidos {
    private static GestorSonidos instancia;

    /** Mapa nombre → URL del recurso de audio ya validado. */
    private final Map<String, URL> sonidos;


    private GestorSonidos() {
        this.sonidos = new HashMap<>();
        cargarSonidos();
    }

    /**
     * Devuelve la única instancia del gestor (lazy init).
     * @return Instancia del GestorSonidos.
     */
    public static GestorSonidos getInstancia() {
        if (instancia == null) {
            instancia = new GestorSonidos();
        }
        return instancia;
    }


    /**
     * Registra todos los sonidos del juego.
     * Los archivos deben estar en src/resources/sonidos/ (ruta de classpath: /sonidos/).
     */
    private void cargarSonidos() {
        registrar("beber", "/sonidos/beber.wav");   // Reproducido al consumir una Bebida
        registrar("masticar",  "/sonidos/masticar.wav");    // Reproducido al consumir un Dulce
    }

    /**
     * Registra un sonido en el mapa.
     * Si el recurso no se encuentra, imprime un aviso pero no lanza excepción,
     * para que el juego siga funcionando sin audio.
     *
     * @param nombre Clave con la que se identificará el sonido.
     * @param ruta   Ruta del recurso dentro del classpath (p. ej. "/sonidos/bebida.wav").
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
     * Reproduce el sonido asociado al nombre dado.
     * Cada llamada abre un Clip independiente, por lo que varios sonidos
     * pueden sonar en simultáneo sin bloquearse entre sí.
     * Si el nombre no existe o hay un error, falla silenciosamente.
     *
     * @param nombre Clave del sonido a reproducir ("bebida" o "dulce").
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

            // Liberamos recursos en cuanto termina la reproducción
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
