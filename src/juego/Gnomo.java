package juego;
import java.awt.Color;
import entorno.Entorno;
import java.util.Random;


public class Gnomo {

    /***************/ 
    /** VARIABLES **/
    /***************/ 
    private double x, y; // Posición del gnomo
    private double velocidadX; // Velocidad de movimiento del gnomo
    private double velocidadY; // Velocidad vertical (caída)
    private boolean enElAire; // Para verificar si el gnomo está cayendo
    
   

    /*********************/
    /**GETTERS Y SETTERS**/
    /*********************/
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setEnElAire(boolean enElAire) {
        this.enElAire = enElAire;
    }

    /*************/
    /*CONSTRUCTOR*/
    /*************/
    public Gnomo(double xInicial, double yInicial) {
        this.x = xInicial;
        this.y = yInicial;
        this.velocidadX = generarDireccionAleatoria(); // Generar dirección aleatoria
        this.velocidadY = 0;
        this.enElAire = false; // Inicialmente el gnomo está en una isla
    }

    /*************/ 
    /** MÉTODOS **/
    /*************/ 
    
    // Genera la dirección aleatoria del gnomo
    private double generarDireccionAleatoria() {
        int random = (int)(Math.random() * 11); // Genera un número entre 0 y 10
        if (random >= 6) {
            return 2; // Si es mayor o igual a 6, va a la derecha
        } else {
            return -2; // Si es menor que 6, va a la izquierda
        }
    }

    // Mueve el gnomo lateralmente
    public void moverLateral() {
        this.x += velocidadX;
    }

    // Hacer caer al gnomo
    public void caer() {
        if (enElAire) {
            this.velocidadY += 0.5; // Acelera hacia abajo (gravedad)
            this.y += this.velocidadY; // Actualiza la posición vertical
        }
    }

    // Verificar si el gnomo llegó al borde de la isla
    public boolean llegoAlBorde(Isla isla) {
        return (this.x <= isla.getX() - isla.getAncho() / 2 || this.x >= isla.getX() + isla.getAncho() / 2);
    }

    // Invertir la dirección al caer sobre otra isla
    public void cambiarDireccion() {
        this.velocidadX = -this.velocidadX;
    }

    // Verificar si el gnomo aterrizó sobre una isla
    public boolean aterrizoSobreIsla(Isla isla) {
        return (this.y >= isla.getY() - isla.getAlto() / 2 && this.y <= isla.getY() + isla.getAlto() / 2);
    }

    // Dibuja el gnomo en pantalla
    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, 20, 20, 0, Color.BLUE);
    }

    
}

