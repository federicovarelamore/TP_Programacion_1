package juego;

import java.awt.Color;
import java.util.ArrayList;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo

	/***************/ 
    /** VARIABLES **/
    /***************/ 

	private Pep pep; // El personaje principal
    private ArrayList<Gnomo> gnomos; // Lista de gnomos
    private ArrayList<Tortuga> tortugas; // Lista de tortugas
    private ArrayList<Isla> islas; // Lista de islas
    private CasaGnomos casaGnomos; // Instancia de CasaGnomo
	
	Juego()
	{
    // Inicializa el objeto entorno
    this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
    
    // Inicializar lo que haga falta para el juego

    /*************/ 
    /** OBJETOS **/
    /*************/ 

    this.pep = new Pep(400, 550); // Posición inicial de Pep
    this.gnomos = new ArrayList<>();
    this.tortugas = new ArrayList<>();
    this.islas = new ArrayList<>();

    // Crear algunas islas
    crearIslas();

    // Crear la casa de los Gnomos
    casaGnomos = new CasaGnomos(375, 300); // Ubicación de la casa en la parte superior

        // Crear algunos gnomos
    for (int i = 0; i < 5; i++) {
        gnomos.add(new Gnomo(200 + (i * 100), 400)); // Posiciones iniciales de los gnomos
    }

    // Crear algunas tortugas 
    for (int i = 0; i < 3; i++) {
        tortugas.add(new Tortuga(150 + (i * 200), 100)); // Posiciones iniciales de las tortugas
    }

    // Inicia el juego!
    this.entorno.iniciar();
	}

	/*************/ 
    /** MÉTODOS **/
    /*************/ 

	// Crear las islas en forma de pirámide
private void crearIslas() {
    // Base de la pirámide (5 islas)
    islas.add(new Isla(150, 500, 100, 20)); // Isla 1 (fila inferior)
    islas.add(new Isla(300, 500, 100, 20)); // Isla 2
    islas.add(new Isla(450, 500, 100, 20)); // Isla 3
    islas.add(new Isla(600, 500, 100, 20)); // Isla 4
    islas.add(new Isla(750, 500, 100, 20)); // Isla 5

    // Segunda fila (4 islas)
    islas.add(new Isla(200, 450, 100, 20)); // Isla 1 (segunda fila)
    islas.add(new Isla(350, 450, 100, 20)); // Isla 2
    islas.add(new Isla(500, 450, 100, 20)); // Isla 3
    islas.add(new Isla(650, 450, 100, 20)); // Isla 4

    // Tercera fila (3 islas)
    islas.add(new Isla(250, 400, 100, 20)); // Isla 1 (tercera fila)
    islas.add(new Isla(400, 400, 100, 20)); // Isla 2
    islas.add(new Isla(550, 400, 100, 20)); // Isla 3

    // Cuarta fila (2 islas)
    islas.add(new Isla(300, 350, 100, 20)); // Isla 1 (cuarta fila)
    islas.add(new Isla(450, 350, 100, 20)); // Isla 2

    // Quinta fila (1 isla, la cima)
    islas.add(new Isla(375, 300, 100, 20)); // Isla única (cima)
}

private void respawnearTortuga() {
    double xRandom = Math.random() * 800; // Genera una posición aleatoria en X
    tortugas.add(new Tortuga(xRandom, 0)); // La tortuga respawnea desde la parte superior
}

 // Actualiza la posición de Pep
    private void actualizarPep() {
        if (entorno.estaPresionada('a') || entorno.sePresiono(entorno.TECLA_IZQUIERDA)){
            pep.moverIzquierda(); // Mover a la izquierda
        }
        if (entorno.estaPresionada('d') || entorno.sePresiono(entorno.TECLA_DERECHA)) {
            pep.moverDerecha(); // Mover a la derecha
        }
        if (entorno.sePresiono(entorno.TECLA_ARRIBA) || entorno.sePresiono(entorno.TECLA_ESPACIO)) {
            pep.saltar(); // Saltar
        }
        pep.actualizar(); // Actualizar la posición de Pep
    }

    // Dibujar todos los objetos en pantalla
    private void dibujarObjetos() {
        entorno.dibujarRectangulo(400, 300, 800, 600, 0, Color.WHITE); // Limpiar el fondo
        pep.dibujar(entorno); // Dibujar a Pep
        
        for (Gnomo gnomo : gnomos) {
            gnomo.dibujar(entorno); // Dibujar gnomos
        }
        
        for (Tortuga tortuga : tortugas) {
            tortuga.dibujar(entorno); // Dibujar tortugas
        }
        
        for (Isla isla : islas) {
            isla.dibujar(entorno); // Dibujar islas
        }

        casaGnomos.dibujar(entorno); // Dibujar la CasaGnomos
    }

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		actualizarPep();
		
		 // Actualizar la posición de los gnomos
		 for (Gnomo gnomo : gnomos) {
            gnomo.moverLateral();
            gnomo.caer(); // Caer si está en el aire

            // Verificar si el gnomo llegó al borde de una isla
            for (Isla isla : islas) {
                if (gnomo.llegoAlBorde(isla)) {
                    gnomo.cambiarDireccion(); // Cambia la dirección si llegó al borde
                }
                // Verificar si el gnomo aterriza sobre una isla
                if (gnomo.aterrizoSobreIsla(isla)) {
                    gnomo.setEnElAire(false); // No está en el aire si aterriza
                }
            }
        }

		// Actualizar la posición de las tortugas
        for (Tortuga tortuga : tortugas) {
            tortuga.moverLateral();
            tortuga.caer(); // Caer si está en el aire

            // Verificar si la tortuga aterriza sobre una isla
            for (Isla isla : islas) {
                if (tortuga.aterrizoSobreIsla(isla)) {
                    tortuga.setEnElAire(false); // No está en el aire si aterriza
                }
            }
        }
        
         // Respawn gnomos de la casa
         if (Math.random() < 0.05) { // 5% de probabilidad de respawnear un gnomo en cada tick
            gnomos.add(casaGnomos.respawnearGnomo());
        }

        // Respawn tortugas
        if (Math.random() < 0.1) { // 10% de probabilidad de respawnear una tortuga en cada tick
            respawnearTortuga();
        }

        // Actualizar tortugas
        for (Tortuga tortuga : tortugas) {
            tortuga.moverLateral();
            tortuga.caer();
            for (Isla isla : islas) {
                if (tortuga.aterrizoSobreIsla(isla)) {
                    tortuga.setEnElAire(false);
                }
            }
        }

		 // Dibujar todos los objetos en pantalla
		 dibujarObjetos();

	} // Cierre Tick
	
	 

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}

} // Cierre Juego
