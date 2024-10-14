package juego;
import entorno.Entorno;

import java.awt.Color;

public class CasaGnomos {

    /***************/ 
    /** VARIABLES **/
    /***************/ 

    private double x, y; // Posición de la casa

    /*************/
    /** GETTERS **/
    /*************/

    public double getX() { return x; }
    public double getY() { return y; }

    /*************/
    /*CONSTRUCTOR*/
    /*************/

    public CasaGnomos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /*************/ 
    /** MÉTODOS **/
    /*************/
    
    // Dibuja la casa
    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, 50, 30, 0, Color.GRAY); // Representa la casa como un rectángulo
    }

    // Respawnea un gnomo
    public Gnomo respawnearGnomo() {
        double direccion = Math.random() < 0.5 ? 1 : -1; // Decide si el gnomo irá a la izquierda o derecha
        return new Gnomo(this.x + direccion * 20, this.y + 20); // Ajusta la posición inicial del gnomo
    }

    
}
