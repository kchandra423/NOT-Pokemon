import java.io.File;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;


public class PlayMusic {
	
	 private static LinkedList<Line> speakers = new LinkedList<Line>();
	
	public void playMusic(String musicLocation)
	{
		try 
		{
			File musicPath = new File(musicLocation);
			
			if(musicPath.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				
				
//				JOptionPane.showMessageDialog(null, "Hit ok to pause");
//				long clipTimePosition = clip.getMicrosecondPosition();
//				clip.stop();
//				
//				JOptionPane.showMessageDialog(null, "Hit ok to unpause");
//				clip.setMicrosecondPosition(clipTimePosition);
//				clip.start();
				
				
			}
			else
			{
				System.out.println("Can't find file");
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
