package Pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GUI {

	/**
	 * Metoda renderowania interfejsu u�ytownika, wywo�uje funkcj�
	 * odpowiedzialn� za rysowanie poszczeg�lnych elemetn�w
	 * 
	 * @param g
	 *            Kotekst graficzny
	 * @param nick
	 *            Nick Gracza
	 * @param punkty
	 *            Ilo�� punkt�w zdobytych przez Gracza
	 * @param zycia
	 *            Ilo�� �y� pozosta�ych Graczowi
	 */
	public void render(Graphics g, String nick, int punkty, int zycia) {
		drawInfo(g, nick, punkty, zycia);

	}

	/**
	 * Metoda rysuj�ca poszczeg�lne elementy interfejsu u�ytkownika
	 * 
	 * @param g
	 *            Kotekst graficzny
	 * @param nick
	 *            Nick Gracza
	 * @param punkty
	 *            Ilo�� punkt�w zdobytych przez Gracza
	 * @param zycia
	 *            Ilo�� �y� pozosta�ych Graczowi
	 */
	public void drawInfo(Graphics g, String nick, int punkty, int zycia) {
		
		g.setColor(Color.YELLOW);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 19));
		g.drawString("Punkty: " + punkty, 230, 505);
		g.drawString("Zycia: " + zycia, 400, 25);
		g.drawString("Gracz: " + nick, 15, 25);
	}

}