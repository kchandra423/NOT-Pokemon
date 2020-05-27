/*
 * Author: Eric Chang
 * Notes: A JPanel that can have a background
 * Bugs: none yet
 */

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
	Image background;
	
	public BackgroundPanel(Image background) {
		super();
		this.background = background.getScaledInstance(800, 600, Image.SCALE_DEFAULT);//
		// sets background to the chosen image
	}
	
	@Override
	protected void paintComponent(Graphics g) {//paint component override
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
}
