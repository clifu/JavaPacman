package Pacman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Klasa reprezentuj�ca �cian�, dziedziczy po klasie Rectangle
 * 
 * @author Damian Fronia
 * @author Zuzanna Kap�on
 * @version 1.0
 */
public class Tile extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1037376209655998842L;
	/**
	 * Zmienna klasy Image, s�u�y prechowywaniu obrazka obiekt�w klasy Tile
	 */
	private Image wall;

	/**
	 * Konstruktor klasy Ground
	 * 
	 * @param x
	 *            po�o�enie w osi x
	 * @param y
	 *            po�o�enie w osi y
	 */
	public Tile(int x, int y) {
		setBounds(x, y, 32, 32);
		ImageIcon img = new ImageIcon("wall.png");
		wall = img.getImage();
	}

	/**
	 * Funkcja odpowiedzialna za pobranie obrazka reprezentujacego obiekt klasy
	 * Ground
	 * 
	 * @return ground - obrazek reprezentujacy obiekt
	 */
	public Image getWall() {
		return wall;
	}

	/**
	 * Funkcja odpowiadaj�ca za wyrenderowanie obrazka reprezentujacego obiekt
	 * klasy Ground
	 * 
	 * @param g
	 *            Kontekst graficzny
	 */
	public void render(Graphics g) {

		g.drawImage(wall, x, y, null);
	}

}
