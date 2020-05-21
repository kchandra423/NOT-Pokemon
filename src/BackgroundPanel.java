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
		this.background = background.getScaledInstance(600, 400, Image.SCALE_DEFAULT);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
}
