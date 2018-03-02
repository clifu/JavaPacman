package Pacman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;

/**
 * Klasa Client. Oferuje funkcje sieciowe. Metody tej klasy pozwalaj¹ na
 * nawi¹zanie po³¹czenia z serwerem oraz swobodn¹ komunikacje. Umo¿liwa pobranie
 * danych konfiguracyjnych, listy najlepszych wyników, definicji poziomów oraz
 * przes³anie wyniku rozgrywki na serwer.
 *
 */
public class Client {

	/**
	 * Sprawdza czy po³¹czenie do serwera pod adresem podanym w configu jest
	 * mo¿liwe.
	 *
	 * @return czy uda³o siê po³¹czyæ z serwerem
	 */
	public static boolean canConnect() {
		try {
			Socket socket = new Socket(GameConfig.connectIP, GameConfig.connectPort);
			socket.setSoTimeout(GameConfig.connectTimeout);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

			writer.println(Protocol.PING);

			System.out.println(reader.readLine());
			socket.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Metoda wysy³a ¿¹danie GETHIGHSCORES do serwera, po czym odbiera liste
	 * najlepszych wynik
	 *
	 * @return Lista najlepszych wyników
	 */
	public static String getHighscores() {

		Socket socket;
		String highScores = null;
		try {
			socket = new Socket(GameConfig.connectIP, GameConfig.connectPort);
			socket.setSoTimeout(GameConfig.connectTimeout);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

			writer.println(Protocol.GETHIGHSCORES);

			System.out.println("Pobrano najlepsze wyniki");

			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return highScores;
	}

	/**
	 * Metoda s³u¿¹ca do pobrania z serwera danych konfiguracyjnych do
	 * rozgrywki.
	 */
	public static void getConfig() {

		Socket socket;
		try {
			socket = new Socket(GameConfig.connectIP, GameConfig.connectPort);
			socket.setSoTimeout(GameConfig.connectTimeout);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			writer.println(Protocol.GETCONFIG);

			String[] answer = reader.readLine().split(new String("#"));

			Properties property = new Properties();

			for (int i = 1; i < answer.length; i++) {
				String[] str = answer[i].split(new String("="));
				property.setProperty(str[0], str[1]);
			}

			Config.readConstantsOnline(property);

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda wysy³a ¿¹danie GETLEVELS do serwera, po czym odbiera liczbê
	 * dostêpnych poziomów
	 */
	public static void getLevels() {

		Socket socket;
		try {
			socket = new Socket(GameConfig.connectIP, GameConfig.connectPort);
			socket.setSoTimeout(GameConfig.connectTimeout);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

			writer.println(Protocol.GETLEVELS);

			String[] answer = reader.readLine().split(new String("/"));

			Config.numberOfLevels = Integer.parseInt(answer[1]);

			System.out.println("Liczba dostêpnych poziomów: " + answer[1]);

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda wysy³a ¿¹danie GETLEVEL/numer_poziomu do serwera, po czym odbiera
	 * definicje poziomu
	 *
	 * @param numberOfLevel
	 *            numer poziomu
	 * @return definicja poziomu
	 */
	public static String getLevel(int numberOfLevel) {

		Socket socket;
		String result = null;
		try {
			socket = new Socket(GameConfig.connectIP, GameConfig.connectPort);
			socket.setSoTimeout(GameConfig.connectTimeout);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

			writer.println(Protocol.GETLEVEL + "/" + numberOfLevel);

			String[] answer = reader.readLine().split(new String("#"));
			String str = new String();

			for (int i = 1; i < answer.length; i++) {
				str = str + answer[i];
			}
			result = str;

			System.out.println("Pobrane definicje poziomu " + numberOfLevel);

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * Metoda wysy³a wynik rozgrywki do serwera, po czym odbiera informacjê czy
	 * wynik zosta³ zaakceptowany. Zwraca true gdy wynik zostanie zaakceptowany.
	 * False gdy odrzucony.
	 *
	 * @param name
	 *            nick gracza
	 * @param score
	 *            wynik punktowy
	 * @return true - gdy wynik zaakceptowany, false - gdy wynik nie zosta³
	 *         zaakceptowany
	 */
	public static boolean sendGameScore(String name, int score) {

		Socket socket;
		boolean result = false;
		try {
			socket = new Socket(GameConfig.connectIP, GameConfig.connectPort);
			socket.setSoTimeout(GameConfig.connectTimeout);

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}
