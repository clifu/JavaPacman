package Pacman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Klasa reprezentująca owoc, dziedziczy po klasie Rectangle
 * 
 * @author Damian Fronia
 * @author Zuzanna Kapłon
 * @version 1.0
 */
public class Cherry extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = -613367726499521437L;
	/**
	 * Zmienna klasy Image, służy prechowywaniu obrazka obiektów klasy Cherry
	 */
	private Image cherry;

	/**
	 * Konstruktor klasy Cherry
	 * 
	 * @param x
	 *            położenie w osi x
	 * @param y
	 *            położenie w osi y
	 */
	public Cherry(int x, int y) {
		setBounds(x, y, 32, 32);
		ImageIcon img = new ImageIcon("cherry.png");
		cherry = img.getImage();
	}

	/**
	 * Funkcja odpowiedzialna za pobranie obrazka reprezentujacego obiekt klasy
	 * Cherry
	 * 
	 * @return cherry - obrazek reprezentujacy obiekt
	 */
	public Image getCherry() {
		return cherry;
	}

	/**
	 * Funkcja odpowiadająca za wyrenderowanie obrazka reprezentujacego obiekt
	 * klasy Cherry
	 * 
	 * @param g
	 *            Kontekst graficzny
	 */
	public void render(Graphics g) {

		g.drawImage(cherry, x, y, null);
	}

}
