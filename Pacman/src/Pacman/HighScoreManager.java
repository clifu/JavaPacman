package Pacman;

import java.util.*;
import java.io.*;

/**
 * Klasa umo¿liwiaj¹ca tworzenie listy najlepszych wyników i zapisanie ich do
 * pliku .txt
 *
 */
public class HighScoreManager {

	/**
	 * Lista wyników
	 */
	private ArrayList<Score> scores;

	/**
	 * Nzazwa pliku, w którym przechowywane bêd¹ najlepsze wyniki.
	 */
	private static final String HIGHSCORE_FILE = "highscores.txt";

	/**
	 * Inicjalizacja obiektu do pracy na strumieniu wyjœciowym z pliku
	 */
	ObjectOutputStream outputStream = null;

	/**
	 * Inicjalizacja obiektu do pracy na strumieniu wejsciowym
	 */
	ObjectInputStream inputStream = null;

	public HighScoreManager() {

		/**
		 * Inicjalizacja listy do zapisu wyników
		 */
		scores = new ArrayList<Score>();
	}

	/**
	 * Metoda zwracaj¹ca listê wyników, zawiera ona dwie sk³adowe mestody, które
	 * zapewniaj¹ ¿e wyniki bêd¹ posortowane od najwiêkszego
	 */
	public ArrayList<Score> getPoints() {
		loadScoreFile();
		sort();
		return scores;
	}

	/**
	 * Metoda s³u¿¹ca sortowaniu listy najlepszych wyników.
	 */
	private void sort() {
		ScoreComparator comparator = new ScoreComparator();
		Collections.sort(scores, comparator);
	}

	/**
	 * Metoda pozwalaj¹ca na dodawanie wyników do pliku.
	 * 
	 * @param name
	 *            nazwa u¿ytkownika
	 * @param points
	 *            iloœæ punktów zdobytych przez u¿utkownika w trakcie rozgrywki
	 */
	public void addScore(String name, int points) {
		loadScoreFile();
		scores.add(new Score(name, points));
		updateScoreFile();
	}

	/**
	 * Metoda ³aduj¹ca wyniki z pliku .txt do listy scores
	 */
	public void loadScoreFile() {
		try {
			inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
			scores = (ArrayList<Score>) inputStream.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("[Laad] FNF Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[Laad] IO Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[Laad] CNF Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Laad] IO Error: " + e.getMessage());
			}
		}
	}

	/**
	 * Metoda zapisuj¹ca listê wyników do pliku.
	 */
	public void updateScoreFile() {
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
			outputStream.writeObject(scores);
		} catch (FileNotFoundException e) {
			System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
		} catch (IOException e) {
			System.out.println("[Update] IO Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}
	}

	/**
	 * Metoda wyœwietlaj¹ca najlepszych u¿ytkowników i ich wyniki.
	 */
	public String getHighscoreString() {

		String highscoreString = "";
		int man = 10;

		ArrayList<Score> scores;
		scores = getPoints();

		int i = 0;
		int x = scores.size();
		if (x > man) {
			x = man;
		}
		while (i < x) {
			highscoreString += (i + 1) + ".\t" + scores.get(i).getNick() + "\t\t" + scores.get(i).getPoints() + "\t\n";
			i++;
		}
		return highscoreString;
	}

}