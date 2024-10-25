package juego;

import java.awt.Color;
import java.util.ArrayList;
import entorno.Entorno;
import entorno.InterfaceJuego;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	// Variables y métodos propios de cada grupo

	/***************/
	/** VARIABLES **/
	/***************/

	private Pep pep; // El personaje principal
<<<<<<< HEAD
	private ArrayList<Gnomo> gnomos; // Lista de gnomos
	private ArrayList<Tortuga> tortugas; // Lista de tortugas
	private ArrayList<Isla> islas; // Lista de islas
	private CasaGnomos casaGnomos; // Instancia de CasaGnomo
	private Image imagenBackground;

	private int gnomosRescatados;
	private int gnomosPerdidos;
	private int enemigosEliminados;
	private long tiempoInicio;
	private boolean juegoterminado;

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
=======
    private ArrayList<Gnomo> gnomos; // Lista de gnomos
    private ArrayList<Tortuga> tortugas; // Lista de tortugas
    private ArrayList<Isla> islas; // Lista de islas
    private CasaGnomos casaGnomos; // Instancia de CasaGnomo
    private Image imagenBackground;
    private Sonidos sonido;
    private int gnomosRescatados;
    private int gnomosPerdidos;
    private int enemigosEliminados;
    private long tiempoInicio;


	
	Juego()
	{
    // Inicializa el objeto entorno
    this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
    
    // Cargar la imagen desde el archivo antes que nada
    this.imagenBackground = new ImageIcon(getClass().getResource("/juego/imagenes/background.png")).getImage();

    // Inicializar lo que haga falta para el juego
    
    //inicializar el reproductor de audio
    sonido = new Sonidos("src/audio/disparo.wav");
    
>>>>>>> c32c0dd5f36fe81740b3b11be0ee933a3ef33afb

		// Cargar la imagen desde el archivo antes que nada
		this.imagenBackground = new ImageIcon(getClass().getResource("/juego/imagenes/background.png")).getImage();

		// Inicializar lo que haga falta para el juego

		/*************/
		/** OBJETOS **/
		/*************/

		this.pep = new Pep(400, 550);
		this.gnomos = new ArrayList<>();
		this.tortugas = new ArrayList<>();
		this.islas = new ArrayList<>();
		this.gnomosRescatados = 0;
		this.gnomosPerdidos = 0;
		this.enemigosEliminados = 0;
		this.tiempoInicio = System.currentTimeMillis();
		this.juegoterminado = false;
		// Crear algunas islas
		crearIslas();

		// Obtener la isla superior (única en la cima)
		Isla islaSuperior = islas.get(islas.size() - 1); // Última isla agregada (la única en la cima)

		// Crear la casa de los Gnomos
		casaGnomos = new CasaGnomos(islaSuperior.getX() - 45 + (islaSuperior.getAncho() / 2), islaSuperior.getY() - 40); // Ubicación
																															// de
																															// la
																															// casa
																															// en
																															// la
																															// parte
																															// superior

		// Crear gnomos iniciales
		for (int i = 0; i < 5; i++) {
			gnomos.add(new Gnomo(200 + (i * 100), 400)); // Posiciones iniciales de los gnomos
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
		int[] numIslasPorFila = { 5, 4, 3, 2, 1 }; // Pirámide

		// Crear islas para cada fila
		for (int fila = 0; fila < numIslasPorFila.length; fila++) {
			int numIslas = numIslasPorFila[fila];

			// Ajuste especial para la fila inferior (base)
			int espacioEntreIslas;
			if (fila == 0) {
				// Si es la última fila (base), distribuir las islas para que ocupen todo el
				// ancho
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
		Isla islaSuperior = islas.get(islas.size() - 1); // Última isla agregada

		// Crear la casa de los Gnomos centrada en la isla superior
		casaGnomos = new CasaGnomos(islaSuperior.getX() + (anchoIsla / 2), islaSuperior.getY() - 30); // Ajusto el -30
																										// según el
																										// tamaño de la
																										// casa
	}

<<<<<<< HEAD
	private void respawnearTortuga() {
=======
public boolean colisiona(Pep pep, Gnomo gnomos) {
	//coordenadas y dimensiones de pep
	double pepIzquierda = pep.getX();
	double pepDerecha = pep.getX() + pep.getAlto();
	double pepSuperior = pep.getY();
	double pepInferior = pep.getY() + pep.getAlto();
	
	//coordenadas y dimensiones de gnomo
	double gnomoIzquierda = gnomos.getX();
	double gnomoDerecha = gnomos.getX() + gnomos.getAlto();
	double gnomoSuperior = gnomos.getY();
	double gnomoInferior = gnomos.getY() + gnomos.getAlto();
	
	//verificar superposicion en el eje x
	boolean colisionX = pepDerecha > gnomoIzquierda && pepIzquierda < gnomoDerecha;
	
	//verificar superposicion en el eje y
	boolean colisionY = pepInferior > gnomoSuperior && pepSuperior < gnomoInferior;
	
	//hay colision si se superponen en ambas ejes
	return colisionX && colisionY;
	
}

public void rescatarGnomo(Gnomo gnomo) {
	//logica para rescatar al gnomo
	gnomosRescatados++;
	gnomos.remove(gnomo); // Eliminar gnomo del juego
	//System.out.println("gnomo rescatado!");
}


>>>>>>> c32c0dd5f36fe81740b3b11be0ee933a3ef33afb

		int tortugaAncho = 50; // Ajusta este valor al ancho real de la tortuga
		int tortugaAlto = 50; // Ajusta este valor al alto real de la tortuga
		Isla islaSeleccionada = null;
		double posicionX = 0;
		int indiceRandom = 0;

		// Crear tortugas solo en las filas 3, 4 y 5 (índices 0, 1 y 2 en el array de
		// islas)
		// Comienza desde el índice 2 (tercera fila)
		indiceRandom = (int) (Math.random() * ((2 - 0) + 1));
		islaSeleccionada = islas.get(indiceRandom);

		// Calcula una posición aleatoria dentro de la isla seleccionada
		posicionX = islaSeleccionada.getX() + Math.random() * (islaSeleccionada.getAncho() - tortugaAncho);

		// Crear la tortuga y agregarla a la lista, ajustando su posición en Y según el
		// alto de la tortuga
		Tortuga nuevaTortuga = new Tortuga(posicionX, 100);
		tortugas.add(nuevaTortuga);
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
		
		if (pep.getY() > entorno.alto()) {
	        juegoterminado = true; //si pep se cae al vacio game over
	    }
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

	private void mostrarEstadisticas() {
		// Calcular el tiempo transcurrido en milisegundos
		long tiempoActual = System.currentTimeMillis();
		long tiempoTranscurridoMillis = tiempoActual - tiempoInicio;

		// Convertir el tiempo transcurrido a horas, minutos y segundos
		long segundos = (tiempoTranscurridoMillis / 1000) % 60;
		long minutos = (tiempoTranscurridoMillis / (1000 * 60)) % 60;
		long horas = (tiempoTranscurridoMillis / (1000 * 60 * 60)) % 24;

		// Formatear el tiempo en hh:mm:ss
		String tiempoFormato = String.format("%02d:%02d:%02d", horas, minutos, segundos);

		// Cambiar la fuente y el color si es necesario
		entorno.cambiarFont("Arial", 18, Color.BLACK);

		// Mostrar el tiempo en formato de reloj
		entorno.escribirTexto("Tiempo: " + tiempoFormato, 10, 20);

		// Mostrar la cantidad de gnomos rescatados
		entorno.escribirTexto("Gnomos rescatados: " + gnomosRescatados, 200, 20);

		// Mostrar la cantidad de gnomos perdidos
		entorno.escribirTexto("Gnomos perdidos: " + gnomosPerdidos, 400, 20);

		// Mostrar la cantidad de enemigos eliminados
		entorno.escribirTexto("Enemigos eliminados: " + enemigosEliminados, 600, 20);
	}

	private void mostrarPantallaGameOver() {
		// Limpiar la pantalla (por ejemplo, puedes dibujar un fondo negro)
		entorno.dibujarRectangulo(entorno.ancho() / 2, entorno.alto() / 2, entorno.ancho(), entorno.alto(), 0,
				Color.BLACK);

		// Cambiar la fuente para el texto del "Game Over"
		entorno.cambiarFont("Arial", 36, Color.WHITE);

		// Escribir el texto "Game Over" en el centro de la pantalla
		entorno.escribirTexto("Game Over", entorno.ancho() / 2 - 100, entorno.alto() / 2);

	}

	private void verificarFinDeJuego() {
		// Si el juego ya ha terminado, no hacemos nada
		if (juegoterminado) {
			return;
		}

		// Calcular el tiempo transcurrido en milisegundos
		long tiempoActual = System.currentTimeMillis();
		long tiempoTranscurridoMillis = tiempoActual - tiempoInicio;

		// Convertir el tiempo transcurrido a minutos
		long minutos = tiempoTranscurridoMillis / (1000 * 60);

		// Si el tiempo transcurrido es de 1 minuto o más, termina el juego
		if (minutos >= 1) {
			juegoterminado = true; // Cambiar el estado del juego a terminado
		}
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
<<<<<<< HEAD
	public void tick() {
		verificarFinDeJuego();
=======
	public void tick()
	{
		//Iterar sobre todos los gnomos en el juego
		for(Gnomo gnomo : gnomos) { //error aca 
			// Verificar si Pep colisiona con el gnom
			if(colisiona(pep,gnomo)) {
				 // Acción en caso de colisión, por ejemplo, rescatar al gnomo
				rescatarGnomo(gnomo); 
			}
			
		}
		
		// Dibujar el fondo antes de todo lo demás
        entorno.dibujarImagen(this.imagenBackground, entorno.ancho() / 2, entorno.alto() / 2, 0, 1);
>>>>>>> c32c0dd5f36fe81740b3b11be0ee933a3ef33afb

		if (juegoterminado) {
	        mostrarPantallaGameOver(); // Mostrar la pantalla de Game Over
	        return; // Detener el resto del ciclo del juego
	    }

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

		///////////////////////////////////////
		// Actualizar la posición de los gnomos
		// for (Gnomo gnomo : gnomos) {
		// gnomo.moverLateral(); // Movimiento lateral
		// boolean enElAire = true; // Asumimos que está en el aire al inicio del ciclo

		// // Verificar si el gnomo ha aterrizado sobre alguna isla
		// for (Isla isla : islas) {
		// if (gnomo.aterrizoSobreIsla(isla)) {
		// gnomo.setEnElAire(false); // Detenemos su caída
		// gnomo.cambiarDireccion(); // Cambiamos dirección al aterrizar
		// enElAire = false; // El gnomo está sobre una isla
		// gnomo.setY(isla.getY() - 20); // Alineamos su posición sobre la isla
		// break; // Detenemos el ciclo ya que ha aterrizado
		// }
		// }

		// // Si no está sobre ninguna isla, sigue cayendo
		// if (enElAire) {
		// gnomo.setEnElAire(true); // El gnomo está en el aire
		// gnomo.caer(); // Actualizamos su posición vertical
		// }
		// }
		//////////////////////////////////////////////////////

		// for (Gnomo gnomo : gnomos) {
		// gnomo.moverLateral();
		// boolean enElAire = true;

		// for (Isla isla : islas) {
		// // Verifica si el gnomo está sobre la isla
		// if (gnomo.getY() + gnomo.getAlto() >= isla.getY() && gnomo.getY() <=
		// isla.getY() + isla.getAlto()) {
		// // Verificar si el gnomo está horizontalmente sobre la isla
		// if (gnomo.getX() + gnomo.getAncho() / 2 >= isla.getX() - isla.getAncho() / 2
		// &&
		// gnomo.getX() - gnomo.getAncho() / 2 <= isla.getX() + isla.getAncho() / 2) {
		// gnomo.setEnElAire(false);
		// gnomo.cambiarDireccion();
		// enElAire = false;

		// // Alinea el gnomo sobre la isla
		// gnomo.setY(isla.getY() - gnomo.getAlto()); // Coloca el gnomo justo encima de
		// la isla
		// break; // Salir del bucle de islas ya que se ha encontrado una colisión
		// }
		// }
		// }

		// // Si no está sobre ninguna isla, sigue cayendo
		// if (enElAire) {
		// gnomo.setEnElAire(true);
		// gnomo.caer(); // Lógica para hacer que el gnomo caiga
		// }
		// }

		// Actualizar la posición de las tortugas
		for (Tortuga tortuga : tortugas) {

			if (tortuga.getEnElAire()) {
				tortuga.caer(); // Hacer que la tortuga caiga si está en el aire
			}

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
		// Mostrar las estadísticas en pantalla
		mostrarEstadisticas(); // <- Aquí se llama el método

		// Respawn tortugas
		if (Math.random() < 0.02 && tortugas.size() <= 10) { // 2% de probabilidad de respawnear una tortuga en cada
																// tick
			respawnearTortuga();
		}

<<<<<<< HEAD
		// Dibujar todos los objetos en pantalla
		dibujarObjetos();

		// Lógica para disparar bolas de fuego con la tecla 'c'
=======
    // Lógica para disparar bolas de fuego con la tecla 'c'

    if (entorno.sePresiono('c')) {
    	pep.disparar();
    	sonido.play();
    }
   
>>>>>>> c32c0dd5f36fe81740b3b11be0ee933a3ef33afb

		if (entorno.sePresiono('c')) {
			pep.disparar();
		}

		// Actualizar y dibujar las bolas de fuego
		pep.actualizarBolasDeFuego(entorno);

	} // Cierre Tick

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

} // Cierre Juego
