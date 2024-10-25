package juego;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Sonidos {
	
	private Clip clip;
	
	public Sonidos(String audio) {
		try {
			//abrir el archivo de audio
			File file = new File(audio);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			
			//obtener un clip de audio
			clip = AudioSystem.getClip();
			
			//cargar el archivo de audio en un clip
			clip.open(audioStream);
			
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
	    }
	}
	
	public void play() {
		if (clip != null) {
			clip.start();
			clip.setFramePosition(0);  //reinicia la posicion del sonido al principio.
			//clip.loop(clip.LOOP_CONTINUOUSLY);  // Para que la música se repita indefinidamente
			
		}
	}
	
	public void stop() {
		if(clip != null) {
			clip.stop();
		}
	}
	
	public void close() {
		if(clip != null) {
			clip.close();
		}
	}
	

}
