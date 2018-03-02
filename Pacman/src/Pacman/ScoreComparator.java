package Pacman;

import java.util.Comparator;

/**
 * Klasa Komparaotr, dziedziczy po klasie Comparator
 * 
 * Umo¿liwia porównywanie liczby punktów uzyskanych przez gracza w celu
 * posortowania ich malej¹co.
 * 
 */
public class ScoreComparator implements Comparator<Score> {

	/**
	 * Metoda klasy Komparator umozliwiajaca porownywanie wyników uzyskowanych
	 * przez graczy w czasie rozgrywki.
	 * 
	 *
	 * @param points1
	 *            obiekt klasy Score, iloœæ uzyskanych punktów
	 * @param points2
	 *            obiekt klasy Score, iloœæ uzyskanych punktów
	 */
	public int compare(Score points1, Score points2) {

		int sc1 = points1.getPoints();
		int sc2 = points2.getPoints();

		if (sc1 > sc2) {
			return -1;
		} else if (sc1 < sc2) {
			return +1;
		} else {
			return 0;
		}
	}
}
