package juego;
import java.awt.Color;
import java.util.ArrayList;
import entorno.Entorno;
import entorno.InterfaceJuego;
import javax.swing.ImageIcon;
import java.awt.Image;

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
    private Image imagenBackground;
	
	Juego()
	{
    // Inicializa el objeto entorno
    this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
    
    // Cargar la imagen desde el archivo
    this.imagenBackground = new ImageIcon(getClass().getResource("/juego/imagenes/background.png")).getImage();

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

    // Obtener la isla superior (única en la cima)
    Isla islaSuperior = islas.get(islas.size() - 1); // Última isla agregada (la única en la cima)  

    // Crear la casa de los Gnomos
    casaGnomos = new CasaGnomos(islaSuperior.getX() - 45 + (islaSuperior.getAncho() / 2), islaSuperior.getY() - 40); // Ubicación de la casa en la parte superior

    // Crear gnomos iniciales
    for (int i = 0; i < 5; i++) {
        gnomos.add(new Gnomo(200 + (i * 100), 400)); // Posiciones iniciales de los gnomos
    }

     // Crear algunas tortugas iniciales
     for (int i = 0; i < 3; i++) {
        tortugas.add(new Tortuga(150 + (i * 200), 100)); // Posiciones iniciales
    }

    // Inicia el juego!
    this.entorno.iniciar();
	}

	/*************/ 
    /** MÉTODOS **/
    /*************/ 

// Crear las islas en forma de pirámide
private void crearIslas() {
    // Dimensiones de la pantalla
    int alturaPantalla = entorno.alto();
    int anchuraPantalla = entorno.ancho();

    // Dimensiones de las islas
    int anchoIsla = 90;
    int altoIsla = 20;

    // Espacio vertical entre filas
    int espacioVertical = 100;

    // Inicializar la posición Y para la primera fila (fila más baja)
    int yPosicion = alturaPantalla - altoIsla;

    // Número de islas por fila, de la base a la cima
    int[] numIslasPorFila = {5, 4, 3, 2, 1}; // Pirámide

    // Crear islas para cada fila
    for (int fila = 0; fila < numIslasPorFila.length; fila++) {
        int numIslas = numIslasPorFila[fila];

        // Ajuste especial para la fila inferior (base)
        int espacioEntreIslas;
        if (fila == 0) {
            // Si es la última fila (base), distribuir las islas para que ocupen todo el ancho
            espacioEntreIslas = (anchuraPantalla - (numIslas * anchoIsla)) / (numIslas - 1);
        } else {
            // Para las demás filas
            espacioEntreIslas = (anchuraPantalla - (numIslas * anchoIsla)) / (numIslas + 1);
        }

        // Crear las islas en la fila actual
        for (int i = 0; i < numIslas; i++) {
            int xPosicion;
            if (fila == 0) {
                // Fila inferior: asegurar que cubra todo el ancho
                xPosicion = i * (anchoIsla + espacioEntreIslas);
            } else {
                // Filas superiores: usar el cálculo habitual con espacio al inicio
                xPosicion = (i * (anchoIsla + espacioEntreIslas)) + espacioEntreIslas;
            }

            islas.add(new Isla(xPosicion, yPosicion, anchoIsla, altoIsla));
        }

        // Bajar la posición Y para la siguiente fila
        yPosicion -= (altoIsla + espacioVertical);
    }

    // Obtener la isla superior (única en la cima)
    Isla islaSuperior = islas.get(islas.size() - 1); // Última isla agregada (la única en la cima)

    // Crear la casa de los Gnomos centrada en la isla superior
    casaGnomos = new CasaGnomos(islaSuperior.getX() + (anchoIsla / 2), islaSuperior.getY() - 30); // Ajusta el -30 según el tamaño de la casa
}


private void respawnearTortuga() {
    // // Generar un número aleatorio de isla (evitamos la primera fila, donde está la casa de los gnomos)
    // int indiceIsla = (int) (Math.random() * (islas.size() - 5)) + 5; // Evitar las primeras 5 islas
    // Isla islaSeleccionada = islas.get(indiceIsla);
    
    // // Calcula una posición X aleatoria dentro de la mitad del ancho de la isla seleccionada.
    // // la tortuga aparece en una posición aleatoria en la isla, pero dentro del rango delimitado
    // // centrado en el medio de la isla (restando un cuarto del ancho para limitar la dispersión)
    // double posicionX = islaSeleccionada.getX() + (Math.random() * islaSeleccionada.getAncho() / 2) - islaSeleccionada.getAncho() / 4;
    
    // // Crea una nueva Tortuga en la posición X calculada y en la posición Y
    // // justo encima de la isla (altura de la isla dividida entre 2, para que la tortuga aparezca sobre la isla).
    // tortugas.add(new Tortuga(posicionX, islaSeleccionada.getY() - islaSeleccionada.getAlto() / 2));
     // Evitar las primeras 2 filas de islas
     
     // Valores predeterminados del ancho y alto de la tortuga (cámbialos por los correctos)
    int tortugaAncho = 50;  // Ajusta este valor al ancho real de la tortuga
    int tortugaAlto = 50;   // Ajusta este valor al alto real de la tortuga

    // Crear tortugas solo en las filas 3, 4 y 5 (índices 2, 3 y 4 en tu array de islas)
    for (int i = 2; i < islas.size(); i++) {  // Comienza desde el índice 2 (tercera fila)
        Isla islaSeleccionada = islas.get(i);

        // Calcula una posición aleatoria dentro de la isla seleccionada
        double posicionX = islaSeleccionada.getX() + Math.random() * (islaSeleccionada.getAncho() - tortugaAncho);

        // Crear la tortuga y agregarla a la lista, ajustando su posición en Y según el alto de la tortuga
        Tortuga nuevaTortuga = new Tortuga(posicionX, islaSeleccionada.getY() - tortugaAlto);
        tortugas.add(nuevaTortuga);
    }

    
    
    
}

// Actualiza la posición de Pep
private void actualizarPep() {
    // Mover a la izquierda si se mantiene presionada 'a' o la tecla izquierda
    if (entorno.estaPresionada('a') || entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
        pep.moverIzquierda(); // Movimiento continuo a la izquierda mientras esté presionada
    }
    // Si se presiona solo una vez, mover un casillero
    else if (entorno.sePresiono('a') || entorno.sePresiono(entorno.TECLA_IZQUIERDA)) {
        pep.moverIzquierda(true); // Mover solo un casillero si se presiona una vez
    }

    // Mover a la derecha si se mantiene presionada 'd' o la tecla derecha
    if (entorno.estaPresionada('d') || entorno.estaPresionada(entorno.TECLA_DERECHA)) {
        pep.moverDerecha(); // Movimiento continuo a la derecha mientras esté presionada
    }
    // Si se presiona solo una vez, mover un casillero
    else if (entorno.sePresiono('d') || entorno.sePresiono(entorno.TECLA_DERECHA)) {
        pep.moverDerecha(true); // Mover solo un casillero si se presiona una vez
    }

    // Saltar si se presiona la tecla de salto
    if (entorno.sePresiono(entorno.TECLA_ARRIBA) || entorno.sePresiono(entorno.TECLA_ESPACIO)) {
        pep.saltar(); // Saltar
    }
    boolean enElAire = true;
    // Detectar colisiones con las islas
    for (Isla isla : islas) {
        if (pep.colisionaCon(isla)) {
            pep.setY(isla.getY() - 30); // Colocar a Pep justo encima de la isla
           // pep.setVelocidadY(0); // Detener la caída
            pep.setSaltando(false); // Permitir que Pep vuelva a saltar
             enElAire = false; // Pep está en el suelo (sobre una isla)
        }
    }

    // Si Pep no colisiona con ninguna isla, sigue en el aire
    if (enElAire) {
        pep.setSaltando(true); // Mantener el salto en curso
    }

    pep.actualizar(); // Actualizar la posición de Pep
}

    // Dibujar todos los objetos en pantalla
    private void dibujarObjetos() {
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
        // Dibujar el fondo antes de todo lo demás
        entorno.dibujarImagen(this.imagenBackground, entorno.ancho() / 2, entorno.alto() / 2, 0, 1);


		// Procesamiento de un instante de tiempo
		actualizarPep();
		
		 // Actualizar la posición de los gnomos
		 for (Gnomo gnomo : gnomos) {
            gnomo.moverLateral();
            gnomo.caer(); // Caer si está en el aire

            // Verificar si el gnomo llegó al borde de una isla
            for (Isla isla : islas) {
                if (gnomo.llegoAlBorde(isla)) {
                    gnomo.caer(); // Cae si llegó al borde
                }
                // Verificar si el gnomo aterriza sobre una isla
                if (gnomo.aterrizoSobreIsla(isla)) {
                    gnomo.setEnElAire(false); // No está en el aire si aterrizó
                }
            }
        }

		// Actualizar la posición de las tortugas
         for (Tortuga tortuga : tortugas) {

            tortuga.caer(); // Caer si está en el aire

            for (Isla isla : islas) {
                if (tortuga.aterrizoSobreIsla(isla)) {// Verificar si la tortuga aterriza sobre una isla
                    tortuga.setEnElAire(false); // La tortuga ha aterrizado
                    tortuga.moverLateral(isla); // Moverse lateralmente en la isla
                    break; // Una vez que la tortuga aterriza, no necesita seguir revisando más islas
                }
            }
        }
        
        
         // Respawn gnomos de la casa
         if (Math.random() < 0.01) { // 1% de probabilidad de respawnear un gnomo en cada tick
            gnomos.add(casaGnomos.respawnearGnomo());
        }

        // Respawn tortugas
        if (Math.random() < 0.02) { // 2% de probabilidad de respawnear una tortuga en cada tick
            respawnearTortuga();
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
