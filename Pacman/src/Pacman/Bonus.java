package Pacman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Klasa reprezentuj¹ca bonus, dziedziczy po klasie Rectangle
 * 
 * @author Damian
 * @version 1.0
 */
public class Bonus extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4907115703984272053L;
	/**
	 * Zmienna klasy Image, s³u¿y prechowywaniu obrazka obiektów klasy Bonus
	 */
	private Image bonus;

	/**
	 * Konstruktor klasy Bonus
	 * 
	 * @param x
	 *            po³o¿enie w osi x
	 * @param y
	 *            po³o¿enie w osi y
	 */
	public Bonus(int x, int y) {
		setBounds(x, y, 32, 32);
		ImageIcon img = new ImageIcon("bonusball1.png");
		bonus = img.getImage();
	}

	/**
	 * Funkcja odpowiedzialna za pobranie obrazka reprezentujacego obiekt klasy
	 * Bonus
	 * 
	 * @return bonus - obrazek reprezentujacy obiekt
	 */
	public Image getBonus() {
		return bonus;
	}

	/**
	 * Funkcja odpowiadaj¹ca za wyrenderowanie obrazka reprezentujacego obiekt
	 * klasy Ball
	 * 
	 * @param g
	 *            Kontekst graficzny
	 */
	public void render(Graphics g) {

		g.drawImage(bonus, x, y, null);
	}

}
