package juego;
import java.awt.Color;
import entorno.Entorno;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Isla {

    /***************/ 
    /** VARIABLES **/
    /***************/ 
    private double x, y; // Posición de la isla en la pantalla
    private double ancho, alto; // Dimensiones de la isla
    private Image imagenIsla; 
    

    /*********************/
    /**GETTERS Y SETTERS**/
    /*********************/

    // Obtiene la posición X de la isla
    public double getX() { return x; }
    
    // Obtiene la posición Y de la isla
    public double getY() { return y; }
    
    // Obtiene el ancho de la isla
    public double getAncho() { return ancho; }
    
    // Obtiene el alto de la isla
    public double getAlto() { return alto; }
    
    /*************/
    /*CONSTRUCTOR*/
    /*************/

    // Inicializa la posición y dimensiones de la isla
    public Isla(double x, double y, double ancho, double alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;

        // Cargar la imagen desde el archivo
        this.imagenIsla = new ImageIcon(getClass().getResource("/juego/imagenes/island.png")).getImage();
    }   
    
    /*************/ 
    /** MÉTODOS **/
    /*************/ 

    // Dibuja la isla en la pantalla
    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GREEN); // Dibuja un rectángulo verde que representa la isla

         // Dibuja la imagen de la isla ajustando el ancho y el alto de la isla
        entorno.dibujarImagen(this.imagenIsla,this.x,this.y,0,0.52);

    }
    
    // Verifica si Pep está dentro de los límites de la isla
    public boolean estaSobreLaIsla(Pep pep) {
        boolean dentroDelLimiteX = pep.getX() > this.x - this.ancho / 2 && pep.getX() < this.x + this.ancho / 2; // Verifica si Pep está dentro de los límites horizontales
        boolean dentroDelLimiteY = pep.getY() >= this.y - this.alto / 2; // Verifica si Pep está por encima del borde inferior de la isla
        return dentroDelLimiteX && dentroDelLimiteY; // Retorna verdadero si Pep está sobre la isla
    }
    

}

    
