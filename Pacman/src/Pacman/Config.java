package Pacman;

import java.util.Properties;

/**
 * Klasa, w kt�rej znajduj� si� sta�e parametry, kt�re s� udost�pniane wszystkim
 * komponentom programu. Nazwy danych s� identyczne z nazwami parametr�w
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
	 * Szeroko�� okna
	 */
	public static int windowWidth;

	/**
	 * Wysoko�� okna
	 */
	public static int windowHeight;

	/**
	 * Nazwy plik�w z definicjami poziom�w
	 */
	public static String[] filenameLevel;

	/**
	 * Nazwa pliku z najlepszymi wynikami
	 */
	public static String filenameHighscores;

	/**
	 * Liczba dost�pnych poziom�w - d�ugo�c flinenameLevel
	 */
	public static int numberOfLevels;

	/**
	 * Tekst na przycsiku Start
	 */
	public static String menuStart;

	/**
	 * Tekst na przycisku pow�rt do menu g��wnego
	 */
	public static String menuStartBack;

	/**
	 * Tekst na przecisku Najlepsze Wyniki
	 */
	public static String menuHighscore;

	/**
	 * Tekst na przycsiku powr�t do menu g��wnego
	 */
	public static String menuHighscoreBack;

	/**
	 * Tekst na przycisku Zasady
	 */
	public static String menuRules;

	/**
	 * Tekst na przycisku Zako�cz Gr�
	 */
	public static String menuExit;

	/**
	 * Pocz�tkowa liczba �y�
	 */
	public static int lives;

	/**
	 * Klatki na sekund�
	 */
	public static int FPS;

	/**
	 * Rozmiar pojedynczej kom�rki
	 */
	public static int tileSize;

	/**
	 * Liczba punkt�w za zjedzenie duszka
	 */
	public static int enemyPoints;

	/**
	 * Punkty za zjedzenie ma�ej kulki
	 */
	public static int ball1Points;

	/**
	 * Punty za zjedzenie du�ej kulki
	 */
	public static int bonus1Points;

	/**
	 * Czas trwania bonusu z du�ej kulki
	 */
	public static int bonus1Duration;

	/**
	 * Punkty za zjedzenie owocu
	 */
	public static int bonus2Points;

	/**
	 * Czas trwania b�yskawicy
	 */
	public static int thunderDuration;

	/**
	 * Liczba wierszy najelpszych wynik�w
	 */
	public static int highScoresRows;

	/**
	 * Liczba kolumn najlepszych wynik�w
	 */
	public static int highScoresColumns;

	/**
	 * Pocz�tkowa liczba punkt�w
	 */
	public static int startScore;

	/**
	 * Domy�lny nick wy�iwetlany w polu, do kt�rego wpisuje si� nazwe gracza
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
	 * Pr�dko�� przeciwnika
	 */
	public static int EnemySpeed1;

	/**
	 * Metoda parsuj�ca dane konfiguracyjne, dane s� wczytane z lokalnego pliku
	 * konfiguracyjnego
	 * 
	 * @param config
	 *            obiekt Properties przechowuj�cy dane konfiguracyjne, kt�re
	 *            nale�y sparsowa�
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
	 * Metoda parsuj�ca dane konfiguracyjne, dane s� wczytane pobranego pliku
	 * konfiguracyjnego
	 * 
	 * @param config
	 *            obiekt Properties przechowuj�cy dane konfiguracyjne, kt�re
	 *            nale�y sparsowa�
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