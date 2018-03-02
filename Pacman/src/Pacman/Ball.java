package Pacman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Klasa reprezentuj¹ca kuleczkê dziedziczy po klasie Rectangle
 * 
 * @author Damian
 * @version 1.0
 */
public class Ball extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8247506461938108583L;
	/**
	 * Zmienna klasy Image, s³u¿y prechowywaniu obrazka obiektów klasy Ball
	 */
	private Image ball;

	/**
	 * Konstruktor klasy Ball
	 * 
	 * @param x
	 *            po³o¿enie w osi x
	 * @param y
	 *            po³o¿enie w osi y
	 */
	public Ball(int x, int y) {
		setBounds(x, y, 32, 32);
		ImageIcon img = new ImageIcon("ball1.png");
		ball = img.getImage();
	}

	/**
	 * Funkcja odpowiedzialna za pobranie obrazka reprezentujacego obiekt klasy
	 * Ball
	 * 
	 * @return ball - obrazek reprezentujacy obiekt
	 */
	public Image getBall() {
		return ball;
	}

	/**
	 * Funkcja odpowiadaj¹ca za wyrenderowanie obrazka reprezentujacego obiekt
	 * klasy Ball
	 * 
	 * @param g
	 *            obiekt klasy Graphics
	 */
	public void render(Graphics g) {
		g.drawImage(ball, x, y, null);
	}

}
