package juego;
import java.awt.Color;
import entorno.Entorno;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Pep {

    /***************/ 
    /** VARIABLES **/
    /***************/ 
    
    private double x, y; // Posición de Pep en la pantalla
    private double velocidadX, velocidadY; // Velocidades de movimiento horizontal y vertical
    private boolean saltando; // Para saber si Pep está en el aire o no
    private Image imagenPep;
 
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

         // Cargar la imagen desde el archivo
         this.imagenPep = new ImageIcon(getClass().getResource("/juego/imagenes/warrior.png")).getImage();
    }
    

    /*************/ 
    /** MÉTODOS **/
    /*************/ 

    // Mover a Pep hacia la izquierda
    public void moverIzquierda() {
        this.velocidadX = -5;
    }

    // Mover a Pep hacia la izquierda un solo casillero
    public void moverIzquierda(boolean sePresiono) {
        if (sePresiono) {
            this.x -= 5; // Mover una sola vez 5 unidades hacia la izquierda
        }
    }

    // Mover a Pep hacia la derecha
    public void moverDerecha() {
        this.velocidadX = 5;
    }

    // Mover a Pep hacia la izquierda un solo casillero
    public void moverDerecha(boolean sePresiono) {
        if (sePresiono) {
            this.x += 5; // Mover una sola vez 5 unidades hacia la derecha
        }
    }

    // Hacer saltar a Pep (solo si no está en el aire)
    public void saltar() {
        if (!saltando) {
            this.velocidadY = -12; // Velocidad negativa para ir hacia arriba
            this.saltando = true; // Indica que Pep está en el aire
        }
    }

  // Verifica si Pep está colisionando con una isla en la parte superior
    public boolean colisionaCon(Isla isla) {
    // Verifica colisión en el eje X (dentro del ancho de la isla)
    boolean colisionX = this.x + 25 > isla.getX() && this.x - 25 < isla.getX() + isla.getAncho();
    
    // Verifica colisión en el eje Y (solo si está "aterrizando" en la parte superior de la isla)
    boolean colisionY = this.y + 30 >= isla.getY() && this.y <= isla.getY(); 
    
    return colisionX && colisionY;
    }

    // Actualiza la posición de Pep en cada ciclo del juego
    public void actualizar() {
        // Actualiza la posición horizontal y vertical de Pep
        this.x += this.velocidadX; // Actualizar la posición horizontal
        this.y += this.velocidadY; // Actualizar la posición vertical

        // Aplica gravedad si Pep está en el aire
        if (saltando) {
            this.velocidadY += 0.5; // La velocidad aumenta hacia abajo debido a la gravedad
        }

        // Detiene el movimiento horizontal cuando no se mantiene presionada la tecla
        this.velocidadX = 0;
    }

    // Dibuja a Pep en la pantalla
    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, 30, 50, 0, java.awt.Color.white); // Dibuja un rectángulo que representa a Pep

        // Dibuja la imagen de Pep
        entorno.dibujarImagen(this.imagenPep,this.x,this.y,0,1);
    }
}