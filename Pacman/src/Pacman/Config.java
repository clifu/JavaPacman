package Pacman;

import java.util.Properties;

/**
 * Klasa, w której znajduj¹ siê sta³e parametry, które s¹ udostêpniane wszystkim
 * komponentom programu. Nazwy danych s¹ identyczne z nazwami parametrów
 * konfiguracyjnych,
 * 
 * 
 * @author Damian Fronia
 * @version 1.0
 */

public class Config {

	/**
	 * Nazwa aplikacji
	 */
	public static String appName;

	/**
	 * Szerokoœæ okna
	 */
	public static int windowWidth;

	/**
	 * Wysokoœæ okna
	 */
	public static int windowHeight;

	/**
	 * Nazwy plików z definicjami poziomów
	 */
	public static String[] filenameLevel;

	/**
	 * Nazwa pliku z najlepszymi wynikami
	 */
	public static String filenameHighscores;

	/**
	 * Liczba dostêpnych poziomów - d³ugoœc flinenameLevel
	 */
	public static int numberOfLevels;

	/**
	 * Tekst na przycsiku Start
	 */
	public static String menuStart;

	/**
	 * Tekst na przycisku powórt do menu g³ównego
	 */
	public static String menuStartBack;

	/**
	 * Tekst na przecisku Najlepsze Wyniki
	 */
	public static String menuHighscore;

	/**
	 * Tekst na przycsiku powrót do menu g³ównego
	 */
	public static String menuHighscoreBack;

	/**
	 * Tekst na przycisku Zasady
	 */
	public static String menuRules;

	/**
	 * Tekst na przycisku Zakoñcz Grê
	 */
	public static String menuExit;

	/**
	 * Pocz¹tkowa liczba ¿yæ
	 */
	public static int lives;

	/**
	 * Klatki na sekundê
	 */
	public static int FPS;

	/**
	 * Rozmiar pojedynczej komórki
	 */
	public static int tileSize;

	/**
	 * Liczba punktów za zjedzenie duszka
	 */
	public static int enemyPoints;

	/**
	 * Punkty za zjedzenie ma³ej kulki
	 */
	public static int ball1Points;

	/**
	 * Punty za zjedzenie du¿ej kulki
	 */
	public static int bonus1Points;

	/**
	 * Czas trwania bonusu z du¿ej kulki
	 */
	public static int bonus1Duration;

	/**
	 * Punkty za zjedzenie owocu
	 */
	public static int bonus2Points;

	/**
	 * Czas trwania b³yskawicy
	 */
	public static int thunderDuration;

	/**
	 * Liczba wierszy najelpszych wyników
	 */
	public static int highScoresRows;

	/**
	 * Liczba kolumn najlepszych wyników
	 */
	public static int highScoresColumns;

	/**
	 * Pocz¹tkowa liczba punktów
	 */
	public static int startScore;

	/**
	 * Domyœlny nick wyœiwetlany w polu, do którego wpisuje siê nazwe gracza
	 */
	public static String defaultNickname;

	/**
	 * Liczba kafelek w kierunku x
	 */
	public static int notx;

	/**
	 * Liczba kafelek w kierunku y
	 */
	public static int noty;

	/**
	 * Predkosc Pacmana
	 */
	public static int PacmanSpeed;

	/**
	 * Szerokosc okna gry
	 */
	public static int GameWidth;

	/**
	 * Wysokosc okna gry
	 */
	public static int GameHeight;

	/**
	 * Prêdkoœæ przeciwnika
	 */
	public static int EnemySpeed1;

	/**
	 * Metoda parsuj¹ca dane konfiguracyjne, dane s¹ wczytane z lokalnego pliku
	 * konfiguracyjnego
	 * 
	 * @param config
	 *            obiekt Properties przechowuj¹cy dane konfiguracyjne, które
	 *            nale¿y sparsowaæ
	 */
	public static void readConstants(Properties config) {

		appName = config.getProperty("appName");
		windowWidth = Integer.parseInt(config.getProperty("windowWidth"));
		windowHeight = Integer.parseInt(config.getProperty("windowHeight"));
		filenameLevel = config.getProperty("filenameLevel").split(" ");
		filenameHighscores = config.getProperty("filenameHighscores");
		menuStart = config.getProperty("menuStart");
		menuStartBack = config.getProperty("menuStartBack");
		menuHighscore = config.getProperty("menuHighscore");
		menuHighscoreBack = config.getProperty("menuHighscoreBack");
		menuRules = config.getProperty("menuRules");
		menuExit = config.getProperty("menuExit");
		lives = Integer.parseInt(config.getProperty("lives"));
		FPS = Integer.parseInt(config.getProperty("FPS"));
		tileSize = Integer.parseInt(config.getProperty("tileSize"));
		enemyPoints = Integer.parseInt(config.getProperty("enemyPoints"));
		ball1Points = Integer.parseInt(config.getProperty("ball1Points"));
		bonus1Points = Integer.parseInt(config.getProperty("bonus1Points"));
		bonus1Duration = Integer.parseInt(config.getProperty("bonus1Duration"));
		bonus2Points = Integer.parseInt(config.getProperty("bonus2Points"));
		thunderDuration = Integer.parseInt(config.getProperty("thunderDuration"));
		highScoresRows = Integer.parseInt(config.getProperty("highScoresRows"));
		highScoresColumns = Integer.parseInt(config.getProperty("highScoresColumns"));
		startScore = Integer.parseInt(config.getProperty("startScore"));
		defaultNickname = config.getProperty("defaultNickname");
		notx = Integer.parseInt(config.getProperty("notx"));
		noty = Integer.parseInt(config.getProperty("noty"));
		PacmanSpeed = Integer.parseInt(config.getProperty("PacmanSpeed"));
		GameWidth = Integer.parseInt(config.getProperty("GameWidth"));
		GameHeight = Integer.parseInt(config.getProperty("GameHeight"));
		EnemySpeed1 = Integer.parseInt(config.getProperty("EnemySpeed1"));

	}

	/**
	 * Metoda parsuj¹ca dane konfiguracyjne, dane s¹ wczytane pobranego pliku
	 * konfiguracyjnego
	 * 
	 * @param config
	 *            obiekt Properties przechowuj¹cy dane konfiguracyjne, które
	 *            nale¿y sparsowaæ
	 */
	public static void readConstantsOnline(Properties config) {
		appName = config.getProperty("appName");
		windowWidth = Integer.parseInt(config.getProperty("windowWidth"));
		windowHeight = Integer.parseInt(config.getProperty("windowHeight"));
		filenameLevel = config.getProperty("filenameLevel").split(" ");
		filenameHighscores = config.getProperty("filenameHighscores");
		menuStart = config.getProperty("menuStart");
		menuStartBack = config.getProperty("menuStartBack");
		menuHighscore = config.getProperty("menuHighscore");
		menuHighscoreBack = config.getProperty("menuHighscoreBack");
		menuRules = config.getProperty("menuRules");
		menuExit = config.getProperty("menuExit");
		lives = Integer.parseInt(config.getProperty("lives"));
		FPS = Integer.parseInt(config.getProperty("FPS"));
		tileSize = Integer.parseInt(config.getProperty("tileSize"));
		enemyPoints = Integer.parseInt(config.getProperty("enemyPoints"));
		ball1Points = Integer.parseInt(config.getProperty("ball1Points"));
		bonus1Points = Integer.parseInt(config.getProperty("bonus1Points"));
		bonus1Duration = Integer.parseInt(config.getProperty("bonus1Duration"));
		bonus2Points = Integer.parseInt(config.getProperty("bonus2Points"));
		thunderDuration = Integer.parseInt(config.getProperty("thunderDuration"));
		highScoresRows = Integer.parseInt(config.getProperty("highScoresRows"));
		highScoresColumns = Integer.parseInt(config.getProperty("highScoresColumns"));
		startScore = Integer.parseInt(config.getProperty("startScore"));
		defaultNickname = config.getProperty("defaultNickname");
		notx = Integer.parseInt(config.getProperty("notx"));
		noty = Integer.parseInt(config.getProperty("noty"));
		PacmanSpeed = Integer.parseInt(config.getProperty("PacmanSpeed"));
		GameWidth = Integer.parseInt(config.getProperty("GameWidth"));
		GameHeight = Integer.parseInt(config.getProperty("GameHeight"));
		EnemySpeed1 = Integer.parseInt(config.getProperty("EnemySpeed1"));
	}

}