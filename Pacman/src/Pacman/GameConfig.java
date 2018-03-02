package Pacman;

import java.util.Properties;

/**
 * Klasa zawieraj¹ca dane konfiguracyjne, które musz¹ zostaæ wczytane od razu po
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
	 * Limit casu po³¹æzennia
	 */
	public static int connectTimeout;

	/**
	 * Metoda wczytuj¹ca i parsuj¹ca dane
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
