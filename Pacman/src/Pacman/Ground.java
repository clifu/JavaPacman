package Pacman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Klasa reprezentuj�ca pod�og� dziedziczy po klasie Rectangle
 * 
 * @author Damian
 * @version 1.0
 */
public class Ground extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5564974867463613853L;
	/**
	 * Zmienna klasy Image, s�u�y prechowywaniu obrazka obiekt�w klasy Ground
	 */
	private Image ground;

	/**
	 * Konstruktor klasy Ground
	 * 
	 * @param x
	 *            po�o�enie w osi x
	 * @param y
	 *            po�o�enie w osi y
	 */
	public Ground(int x, int y) {
		setBounds(x, y, 32, 32);
		ImageIcon img = new ImageIcon("ground2.png");
		ground = img.getImage();
	}

	/**
	 * Funkcja odpowiedzialna za pobranie obrazka reprezentujacego obiekt klasy
	 * Ground
	 * 
	 * @return groud - obrazek reprezentujacy obiekt
	 */
	public Image getGround() {
		return ground;
	}

	/**
	 * Funkcja odpowiadaj�ca za wyrenderowanie obrazka reprezentujacego obiekt
	 * klasy Ball
	 * 
	 * @param g
	 *            obiekt klasy Graphics
	 */
	public void render(Graphics g) {

		g.drawImage(ground, x, y, null);
	}

}
