package juego;
import java.awt.Color;

import entorno.Entorno;

public class Tortuga {

    /***************/ 
    /** VARIABLES **/
    /***************/ 

    private double x, y; // Posición de la tortuga
    private double velocidadX; // Velocidad de movimiento horizontal
    private double velocidadY; // Velocidad vertical (caída)
    private boolean enElAire; // Indica si la tortuga está cayendo

    /*********************/
    /**GETTERS Y SETTERS**/
    /*********************/

    public double getX() { return x; }
    public double getY() { return y; }
    public void setEnElAire(boolean enElAire) { this.enElAire = enElAire; }

    /*************/
    /*CONSTRUCTOR*/
    /*************/
    
    public Tortuga(double xInicial, double yInicial) {
        this.x = xInicial;
        this.y = yInicial;
        this.velocidadX = generarDireccionAleatoria(); // Genera una dirección aleatoria para moverse lateralmente
        this.velocidadY = 0; // Inicialmente no tiene velocidad vertical (no está cayendo)
        this.enElAire = true; // Las tortugas caen inicialmente desde el cielo
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
    public boolean aterrizoSobreIsla(Isla isla) {
        return (this.y >= isla.getY() - isla.getAlto() / 2 && this.y <= isla.getY() + isla.getAlto() / 2);
    }

    // Dibuja la tortuga en pantalla
    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, 30, 15, 0, Color.ORANGE); // Dibuja la tortuga como un rectángulo naranja
    }

}
