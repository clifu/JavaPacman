package Pacman;

import java.util.*;
import java.io.*;

/**
 * Klasa umo�liwiaj�ca tworzenie listy najlepszych wynik�w i zapisanie ich do
 * pliku .txt
 *
 */
public class HighScoreManager {

	/**
	 * Lista wynik�w
	 */
	private ArrayList<Score> scores;

	/**
	 * Nzazwa pliku, w kt�rym przechowywane b�d� najlepsze wyniki.
	 */
	private static final String HIGHSCORE_FILE = "highscores.txt";

	/**
	 * Inicjalizacja obiektu do pracy na strumieniu wyj�ciowym z pliku
	 */
	ObjectOutputStream outputStream = null;

	/**
	 * Inicjalizacja obiektu do pracy na strumieniu wejsciowym
	 */
	ObjectInputStream inputStream = null;

	public HighScoreManager() {

		/**
		 * Inicjalizacja listy do zapisu wynik�w
		 */
		scores = new ArrayList<Score>();
	}

	/**
	 * Metoda zwracaj�ca list� wynik�w, zawiera ona dwie sk�adowe mestody, kt�re
	 * zapewniaj� �e wyniki b�d� posortowane od najwi�kszego
	 */
	public ArrayList<Score> getPoints() {
		loadScoreFile();
		sort();
		return scores;
	}

	/**
	 * Metoda s�u��ca sortowaniu listy najlepszych wynik�w.
	 */
	private void sort() {
		ScoreComparator comparator = new ScoreComparator();
		Collections.sort(scores, comparator);
	}

	/**
	 * Metoda pozwalaj�ca na dodawanie wynik�w do pliku.
	 * 
	 * @param name
	 *            nazwa u�ytkownika
	 * @param points
	 *            ilo�� punkt�w zdobytych przez u�utkownika w trakcie rozgrywki
	 */
	public void addScore(String name, int points) {
		loadScoreFile();
		scores.add(new Score(name, points));
		updateScoreFile();
	}

	/**
	 * Metoda �aduj�ca wyniki z pliku .txt do listy scores
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
	 * Metoda zapisuj�ca list� wynik�w do pliku.
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
	 * Metoda wy�wietlaj�ca najlepszych u�ytkownik�w i ich wyniki.
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