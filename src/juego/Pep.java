package juego;
import java.awt.Color;
import entorno.Entorno;

public class Pep {

    /***************/ 
    /** VARIABLES **/
    /***************/ 
    
    private double x, y; // Posición de Pep en la pantalla
    private double velocidadX, velocidadY; // Velocidades de movimiento horizontal y vertical
    private boolean saltando; // Para saber si Pep está en el aire o no
 
    /*********************/
    /**GETTERS Y SETTERS**/
    /*********************/

    // Obtiene la posición X de Pep
    public double getX() { return x; }
    
    // Obtiene la posición Y de Pep
    public double getY() { return y; }
    
    // Obtiene la velocidad horizontal
    public double getVelocidadX() { return velocidadX; }
    
    // Obtiene la velocidad vertical
    public double getVelocidadY() { return velocidadY; }
    
    // Verifica si Pep está saltando
    public boolean isSaltando() { return saltando; }

    // Establece la posición X de Pep
    public void setX(double x) { this.x = x; }
    
    // Establece la posición Y de Pep
    public void setY(double y) { this.y = y; }
    
    // Establece la velocidad horizontal
    public void setVelocidadX(double velocidadX) { this.velocidadX = velocidadX; }
    
    // Establece la velocidad vertical
    public void setVelocidadY(double velocidadY) { this.velocidadY = velocidadY; }
    
    // Establece si Pep está en el aire o no
    public void setSaltando(boolean saltando) { this.saltando = saltando; }
    //#endregion

    /*************/
    /*CONSTRUCTOR*/
    /*************/

    // Inicializa la posición y estado de Pep
    public Pep(double xInicial, double yInicial) {
        this.x = xInicial;
        this.y = yInicial;
        this.velocidadX = 0; // Inicialmente sin velocidad horizontal
        this.velocidadY = 0; // Inicialmente sin velocidad vertical
        this.saltando = false; // Pep no está saltando al inicio
    }
    

    /*************/ 
    /** MÉTODOS **/
    /*************/ 

    // Mover a Pep hacia la izquierda
    public void moverIzquierda() {
        this.velocidadX = -5;
    }

    // Mover a Pep hacia la derecha
    public void moverDerecha() {
        this.velocidadX = 5;
    }

    // Hacer saltar a Pep (solo si no está en el aire)
    public void saltar() {
        if (!saltando) {
            this.velocidadY = -10; // Velocidad negativa para ir hacia arriba
            this.saltando = true; // Indica que Pep está en el aire
        }
    }

    // Actualiza la posición de Pep en cada ciclo del juego
    public void actualizar() {
        this.x += this.velocidadX; // Actualiza la posición horizontal
        this.y += this.velocidadY; // Actualiza la posición vertical

        // Aplica gravedad si Pep está en el aire
        if (saltando) {
            this.velocidadY += 0.5; // La velocidad aumenta hacia abajo
        }
    }

    // Dibuja a Pep en la pantalla
    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, 30, 50, 0, java.awt.Color.RED); // Dibuja un rectángulo que representa a Pep
    }
}