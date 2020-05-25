
/*
 * Author: Gilad Bitton
 * Revised: Kumar Chandra
 */
import java.io.File;
//import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//import javax.sound.sampled.FloatControl;
//import javax.sound.sampled.Line;
//import javax.sound.sampled.LineUnavailableException;
//import javax.swing.JOptionPane;

public class MusicPlayer {

	// private static LinkedList<Line> speakers = new LinkedList<Line>();
	private long currentTime;
	private Clip clip;

	// Constructor with nothing
	public MusicPlayer() {
		currentTime = 0;
		clip = null;
	}

	// Constructor that plays the music at musicLocation
	public MusicPlayer(String musicLocation) {
		// checks for music location
		try {
			File musicPath = new File(musicLocation);
			// starts and loops audio
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
			clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// method to play the music
	public void play(String musicLocation) {
		// checks for music location
		try {
			File musicPath = new File(musicLocation);
			// starts and loops audio
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
			clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// pauses music
	public void pause() {
		currentTime = clip.getMicrosecondPosition();
		clip.stop();
	}

	// resumes music
	public void resume() {
		clip.setMicrosecondPosition(currentTime);
		clip.start();

	}

	// stops music
	public void stop() {
		clip.stop();
	}
}
