package Pacman;

import java.util.Properties;

/**
 * Klasa zawieraj�ca dane konfiguracyjne, kt�re musz� zosta� wczytane od razu po
 * starcie aplikacji. Zawiera parametry komunikacji sieciowej.
 * 
 */
public class GameConfig {

	/**
	 * IP Serwera
	 */
	public static String connectIP;
	/**
	 * Port wykorzystywany do komunikacji
	 */
	public static int connectPort;
	/**
	 * Limit casu po���zennia
	 */
	public static int connectTimeout;

	/**
	 * Metoda wczytuj�ca i parsuj�ca dane
	 *
	 * @param config
	 *            kolekcja Properties z danymi
	 */
	public static void readConstants(Properties config) {

		connectIP = config.getProperty("connectIP");
		connectPort = Integer.parseInt(config.getProperty("connectPort"));
		connectTimeout = Integer.parseInt(config.getProperty("connectTimeout"));
	}
}
