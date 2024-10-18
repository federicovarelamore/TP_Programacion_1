package juego;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Image;

import entorno.Entorno;

public class Tortuga {

    /***************/ 
    /** VARIABLES **/
    /***************/ 

    private double x, y; // Posición de la tortuga
    private double velocidadX; // Velocidad de movimiento horizontal
    private double velocidadY; // Velocidad vertical (caída)
    private boolean enElAire = true; // Indica si la tortuga está cayendo
    private Image imagenTurtle;

    /*********************/
    /**GETTERS Y SETTERS**/
    /*********************/

    public double getX() { return x; }
    public double getY() { return y; }
    public void setEnElAire(boolean enElAire) { this.enElAire = enElAire; }
    public boolean getEnElAire( ) { return enElAire; }

    /*************/
    /*CONSTRUCTOR*/
    /*************/
    
    public Tortuga(double xInicial, double yInicial) {
        this.x = xInicial;
        this.y = yInicial;
        this.velocidadX = generarDireccionAleatoria(); // Genera una dirección aleatoria para moverse lateralmente
        this.velocidadY = 0; // Inicialmente no tiene velocidad vertical (no está cayendo)
        this.enElAire = true; // Las tortugas caen inicialmente desde el cielo

        // Cargar la imagen desde el archivo
        this.imagenTurtle = new ImageIcon(getClass().getResource("/juego/imagenes/turtle.png")).getImage();
    }

    /*************/ 
    /** MÉTODOS **/
    /*************/ 
    
    // Genera una dirección aleatoria para la tortuga (izquierda o derecha)
    private double generarDireccionAleatoria() {
        int random = (int)(Math.random() * 11); // Genera un número entre 0 y 10
        if (random >= 6) {
            return 2; // Se mueve a la derecha
        } else {
            return -2; // Se mueve a la izquierda
        }
    }

    // Mover la tortuga lateralmente
    public void moverLateral() {
        this.x += velocidadX;
    }

    public void moverLateral(Isla isla) {
        this.x += velocidadX;
    
        // Si la tortuga llega al borde de la isla, cambia de dirección
        if (this.x <= isla.getX() - isla.getAncho() / 2 + 5 || this.x >= isla.getX() + isla.getAncho() / 2 - 5) {
            this.velocidadX = -this.velocidadX; // Cambia de dirección al llegar al borde de la isla
        }
    }

    // Hacer caer a la tortuga si está en el aire
    public void caer() {
        if (enElAire) {
            this.velocidadY += 0.5; // Aumenta la velocidad hacia abajo (gravedad)
            this.y += this.velocidadY; // Actualiza la posición vertical
        }
    }

    // Invierte la dirección de la tortuga cuando llega al borde de una isla
    public void cambiarDireccion() {
        this.velocidadX = -this.velocidadX; // Invierte la velocidad horizontal
    }

    // Verifica si la tortuga aterrizó sobre una isla
    // Verifica si la tortuga aterrizó sobre una isla
    public boolean aterrizoSobreIsla(Isla isla) {
    // Verificar si la tortuga está en el rango horizontal y vertical de la isla
        return (this.y + this.velocidadY >= isla.getY() - isla.getAlto() / 2) &&
            (this.x >= isla.getX() - isla.getAncho() / 2) &&
            (this.x <= isla.getX() + isla.getAncho() / 2);
    }


    // Dibuja la tortuga en pantalla
    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, 30, 15, 0, Color.ORANGE); // Dibuja la tortuga como un rectángulo naranja

        // Dibuja la imagen de la tortuga
        entorno.dibujarImagen(this.imagenTurtle,this.x,this.y,0,1);
    }

}
