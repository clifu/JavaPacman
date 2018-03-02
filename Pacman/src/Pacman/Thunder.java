package Pacman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Klasa reprezentuj�ca b�yskawic�, dziedziczy po klasie Rectangle
 * 
 * @author Damian Fronia
 * @author Zuzanna Kap�on
 * @version 1.0
 */
public class Thunder extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8522581915001467595L;
	/**
	 * Zmienna klasy Image, s�u�y prechowywaniu obrazka obiekt�w klasy Thunder
	 */
	private Image thunder;

	/**
	 * Konstruktor klasy Thunder
	 * 
	 * @param x
	 *            po�o�enie w osi x
	 * @param y
	 *            po�o�enie w osi y
	 */
	public Thunder(int x, int y) {
		setBounds(x, y, 32, 32);
		ImageIcon img = new ImageIcon("thunder.png");
		thunder = img.getImage();
	}

	/**
	 * Funkcja odpowiedzialna za pobranie obrazka reprezentujacego obiekt klasy
	 * Thunder
	 * 
	 * @return thunder - obrazek reprezentujacy obiekt
	 */
	public Image getThunder() {
		return thunder;
	}

	/**
	 * Funkcja odpowiadaj�ca za wyrenderowanie obrazka reprezentujacego obiekt
	 * klasy Thunder
	 * 
	 * @param g
	 *            Kontekst graficzny
	 */
	public void render(Graphics g) {

		g.drawImage(thunder, x, y, null);
	}

}
