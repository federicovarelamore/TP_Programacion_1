package juego;
import java.awt.Color;
import entorno.Entorno;
import java.awt.Image;

import javax.swing.ImageIcon;

public class BolaDeFuego {

    /***************/ 
    /** VARIABLES **/
    /***************/ 

    private double x, y;
    private double velocidad;
    private boolean visible;
    private int direccion;
    private Image[] imagenesBolasDeFuego; // Array con las diferentes imágenes
    private int frameActual; // Índice de la imagen actual para animación
    private int velocidadAnimacion; // Velocidad para cambiar de frame

    public BolaDeFuego(double x, double y, int direccion) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.velocidad = 5;  // Puedes ajustar la velocidad según lo necesites
        this.visible = true;
        this.frameActual = 0; // Empezamos con la primera imagen
        this.velocidadAnimacion = 4; // Cada 4 ciclos cambiará de frame 

        // Cargamos las imágenes
        this.imagenesBolasDeFuego = new Image[]{
            new ImageIcon(getClass().getResource("/juego/imagenes/fireball1.png")).getImage(),
            new ImageIcon(getClass().getResource("/juego/imagenes/fireball2.png")).getImage(),
            new ImageIcon(getClass().getResource("/juego/imagenes/fireball3.png")).getImage(),
            new ImageIcon(getClass().getResource("/juego/imagenes/fireball4.png")).getImage()
        };
    }

    

    /*********************/
    /**GETTERS Y SETTERS**/
    /*********************/



    /*************/ 
    /** MÉTODOS **/
    /*************/ 

    public void mover() {
        if (direccion == 1) {
            x += velocidad;  // Mover a la derecha
        } else {
            x -= velocidad;  // Mover a la izquierda
        }
        
        // Si la bola sale de los límites, la hacemos invisible
        if (x < 0 || x > 800) {
            visible = false;
        }

        // Cambia el frame para la animación
        frameActual++;
        if (frameActual >= imagenesBolasDeFuego.length * velocidadAnimacion) {
            frameActual = 0;
        }
    }

     // Devuelve la imagen actual para dibujar la animación
     public Image getImagenActual() {
        return imagenesBolasDeFuego[frameActual / velocidadAnimacion];
    }

    public void dibujar(Entorno entorno) {
        if (visible) {
            entorno.dibujarCirculo(x, y, 10, Color.RED);  // Dibuja una bola roja de fuego

            entorno.dibujarImagen(getImagenActual(), this.x, this.y, 0);
        }
    }

    public boolean esVisible() {
        return visible;
    }
}

