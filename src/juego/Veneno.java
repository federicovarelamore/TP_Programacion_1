package juego;

import java.awt.Image;
import javax.swing.ImageIcon;
import entorno.Entorno;

public class Veneno {

    /***************/ 
    /** VARIABLES **/
    /***************/ 

    private double x, y; // Posición de la bola de veneno
    private double velocidad; // Velocidad de movimiento de la bola
    private int direccion; // Dirección en la que se mueve (1 para derecha, -1 para izquierda)
    private boolean activo; // Indica si el veneno está activo
    private Image imagenVeneno; // Imagen de la bola de veneno

    /*************/
    /* CONSTRUCTOR */
    /*************/

    public Veneno(double x, double y, int direccion) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.velocidad = 4; // Velocidad de movimiento
        this.activo = true; // El veneno está activo al crear

        // Cargar la imagen del veneno
        this.imagenVeneno = new ImageIcon(getClass().getResource("/juego/imagenes/poison.png")).getImage();
    }

    /*************/ 
    /** MÉTODOS **/
    /*************/ 

    public boolean estaActivo() {
        return activo; // Verifica si el veneno está activo
    }

    public void mover() {
        if (direccion == 1) {
            x += velocidad; // Mover a la derecha
        } else {
            x -= velocidad; // Mover a la izquierda
        }

        // Si el veneno sale de los límites de la pantalla, se desactiva
        if (x < 0 || x > 800) {
            activo = false; // Desactivar si sale de los límites
        }
    }

    public void dibujar(Entorno entorno) {
        if (activo) {
            entorno.dibujarImagen(imagenVeneno, x, y, 0, 0.1); // Dibuja la imagen del veneno
        }
    }
}

