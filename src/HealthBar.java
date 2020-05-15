import javax.swing.*;
import java.awt.*;

public class HealthBar extends JPanel {
	private Pokemon pokemon;
	private Color background = new Color(46, 46, 46);
	private Color border = new Color(151, 159, 134);
	private Color hpText = new Color(247, 61, 46);
	private Color lightGreen = new Color(85, 247, 85);
	private Color darkGreen = new Color(18, 185, 25);
	private Color lightYellow = new Color(255, 219, 74);
	private Color darkYellow = new Color(215, 172, 0);
	private Color lightRed = new Color(222, 73, 66);
	private Color darkRed = new Color(190, 40, 33);
	
	public HealthBar(Pokemon pokemon) {
		super();
		this.pokemon = pokemon;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(border);
		g2.fillRoundRect(2, 2, 140, 12, 2, 2);
		g2.setColor(background);
		g2.fillRoundRect(0, 0, 140, 12, 2, 2);
		g2.setColor(hpText);
		g2.setFont(g2.getFont().deriveFont(10f));
		g2.drawString("HP", 8, 10);
		if(pokemon.getHealth() > pokemon.getBaseHealth()/2.0) {
			g2.setColor(lightGreen);
			g2.fillRect(30, 2, (int)((double)pokemon.getHealth()/pokemon.getBaseHealth()*105), 8);
			g2.setColor(darkGreen);
			g2.fillRect(30, 2, (int)((double)pokemon.getHealth()/pokemon.getBaseHealth()*105), 2);
			g2.fillRect(30, 8, (int)((double)pokemon.getHealth()/pokemon.getBaseHealth()*105), 2);
		}
		else if(pokemon.getHealth() >= pokemon.getBaseHealth()/5.0) {
			g2.setColor(lightYellow);
			g2.fillRect(30, 2, (int)((double)pokemon.getHealth()/pokemon.getBaseHealth()*105), 8);
			g2.setColor(darkYellow);
			g2.fillRect(30, 2, (int)((double)pokemon.getHealth()/pokemon.getBaseHealth()*105), 2);
			g2.fillRect(30, 8, (int)((double)pokemon.getHealth()/pokemon.getBaseHealth()*105), 2);
		}
		else {
			g2.setColor(lightRed);
			g2.fillRect(30, 2, (int)((double)pokemon.getHealth()/pokemon.getBaseHealth()*105), 8);
			g2.setColor(darkRed);
			g2.fillRect(30, 2, (int)((double)pokemon.getHealth()/pokemon.getBaseHealth()*105), 2);
			g2.fillRect(30, 8, (int)((double)pokemon.getHealth()/pokemon.getBaseHealth()*105), 2);
		}
		g2.setColor(border);
		g2.fillRect(28, 2, 2, 8);
		g2.fillRect(135, 2, 2, 8);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(142, 14);
	}
}
