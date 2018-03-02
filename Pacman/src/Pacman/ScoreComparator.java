package Pacman;

import java.util.Comparator;

/**
 * Klasa Komparaotr, dziedziczy po klasie Comparator
 * 
 * Umo�liwia por�wnywanie liczby punkt�w uzyskanych przez gracza w celu
 * posortowania ich malej�co.
 * 
 */
public class ScoreComparator implements Comparator<Score> {

	/**
	 * Metoda klasy Komparator umozliwiajaca porownywanie wynik�w uzyskowanych
	 * przez graczy w czasie rozgrywki.
	 * 
	 *
	 * @param points1
	 *            obiekt klasy Score, ilo�� uzyskanych punkt�w
	 * @param points2
	 *            obiekt klasy Score, ilo�� uzyskanych punkt�w
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
