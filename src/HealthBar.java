/*
 * Author: Eric Chang
 * Notes: A graphical representation of a health bar
 * Bugs: Increasing insets of some components will make the health bar disappear
 */

import javax.swing.*;
import java.awt.*;

public class HealthBar extends JLabel {
	private Pokemon pokemon; // the pokemon this health bar is for
	private int xOffset; // to center the health bar with other components
	private int hpOnScreen; // displayed health before it changes
	private boolean changing; // if there is a change in the health of the pokemon
	
	// Colors in the health bar
	private Color background = new Color(46, 46, 46);
	private Color border = new Color(151, 159, 134);
	private Color hpText = new Color(247, 61, 46);
	private Color lightGreen = new Color(85, 247, 85);
	private Color darkGreen = new Color(18, 185, 25);
	private Color lightYellow = new Color(255, 219, 74);
	private Color darkYellow = new Color(215, 172, 0);
	private Color lightRed = new Color(222, 73, 66);
	private Color darkRed = new Color(190, 40, 33);
	
	// just a constructor
	public HealthBar(Pokemon pokemon) {
		super();
		this.pokemon = pokemon;
		xOffset = 0;
		hpOnScreen = pokemon.getHealth();
		changing = false;
	}
	
	// to draw the health bar
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(border);
		g2.fillRoundRect(xOffset+2, 2, 140, 12, 2, 2);
		g2.setColor(background);
		g2.fillRoundRect(xOffset, 0, 140, 12, 2, 2);
		g2.setColor(hpText);
		g2.setFont(g2.getFont().deriveFont(10f));
		g2.drawString("HP", xOffset+8, 10);
		// change color based on health percentage
		if(hpOnScreen > pokemon.getBaseHealth()/2.0) {
			g2.setColor(lightGreen);
			g2.fillRect(xOffset+30, 2, (int)((double)hpOnScreen/pokemon.getBaseHealth()*105), 8);
			g2.setColor(darkGreen);
			g2.fillRect(xOffset+30, 2, (int)((double)hpOnScreen/pokemon.getBaseHealth()*105), 2);
			g2.fillRect(xOffset+30, 8, (int)((double)hpOnScreen/pokemon.getBaseHealth()*105), 2);
		}
		else if(hpOnScreen >= pokemon.getBaseHealth()/5.0) {
			g2.setColor(lightYellow);
			g2.fillRect(xOffset+30, 2, (int)((double)hpOnScreen/pokemon.getBaseHealth()*105), 8);
			g2.setColor(darkYellow);
			g2.fillRect(xOffset+30, 2, (int)((double)hpOnScreen/pokemon.getBaseHealth()*105), 2);
			g2.fillRect(xOffset+30, 8, (int)((double)hpOnScreen/pokemon.getBaseHealth()*105), 2);
		}
		else {
			g2.setColor(lightRed);
			g2.fillRect(xOffset+30, 2, (int)((double)hpOnScreen/pokemon.getBaseHealth()*105), 8);
			g2.setColor(darkRed);
			g2.fillRect(xOffset+30, 2, (int)((double)hpOnScreen/pokemon.getBaseHealth()*105), 2);
			g2.fillRect(xOffset+30, 8, (int)((double)hpOnScreen/pokemon.getBaseHealth()*105), 2);
		}
		g2.setColor(border);
		g2.fillRect(xOffset+28, 2, 2, 8);
		g2.fillRect(xOffset+135, 2, 2, 8);
		// change displayed health if a change occurred
		if(hpOnScreen != pokemon.getHealth()) {
			if(hpOnScreen > pokemon.getHealth()) {
				hpOnScreen--;
			}
			else {
				hpOnScreen++;
			}
			changing = true;
		}
		else {
			changing = false;
		}
	}
	
	// specifying the size of the health bar
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(xOffset+142, 14);
	}

	// change the pokemon the health bar is for
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
		hpOnScreen = pokemon.getHealth();
		changing = false;
	}
	
	// finds the offset to center the health bar
	public void setXOffset(boolean backSide) {
		ImageIcon sprite;
		if(backSide) {
			sprite = new ImageIcon("Images/Sprites/SpritesBack/" + pokemon.getID() + "-back.gif");
		}
		else {
			sprite = new ImageIcon("Images/Sprites/SpritesFront/" + pokemon.getID() + ".gif");
		}
		if(sprite.getIconWidth() > 140) {
			xOffset = (sprite.getIconWidth()-140)/2;
		}
	}
	
	// rest is just getters
	public Pokemon getPokemon() {
		return pokemon;
	}
	
	public int getHPOnScreen() {
		return hpOnScreen;
	}
	
	public boolean isChanging() {
		return changing;
	}
}
