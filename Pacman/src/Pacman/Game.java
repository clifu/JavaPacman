package Pacman;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Klasa Game, w kt�rej rysowana jest grafika. ��czy w sobie inne cz�ci
 * aplikacji.
 * 
 * @author Damian Fronia
 * @author Zuzanna Kap�on
 * @version 1.0
 */

public class Game extends JPanel implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6256303383691407115L;

	/**
	 * Zmienna statyczna klasy Player, s�u�y do odwo�ywania si� do zmiennych tej
	 * klasy
	 */
	public static Player pacman;

	/**
	 * Flaga informuj�ca, czy w�tek dzia�a
	 */
	public boolean isRunning = false;

	/**
	 * Flaga do ustalenia czy moze zosta� zjedzony przeciwnik
	 */
	public static boolean canEat = false;

	/**
	 * Zmienna pomocnicza sluzaca do opisu poczatku czasu przez ktory mozemy
	 * zjesc przeciwnika
	 */
	public static int start;

	/**
	 * Zmienna pomocnicza sluzaca do opisu poczatku czasu przez ktory jestesmy
	 * przyspieszeni
	 */
	public static int start2;

	/**
	 * Iterator tablicy map, s�u��cy wyboru mapy
	 */
	public static int indeks = 0;

	/**
	 * Flaga informuj�ca czy gracz zosta� przy�pieszony
	 */
	public static boolean canSpeedUp = false;
	/**
	 * G��wny w�tek
	 */
	private Thread thread;
	/**
	 * Nick gracza
	 */
	public String nick;
	/**
	 * Utworzenie obiektu Player-gracz.
	 */
	public static Player player;
	/**
	 * Utworzenie obiektu Mapa.
	 */

	public static Mapa mapa;

	/**
	 * Utworzenie obiektu GUI.
	 */
	public static GUI gui;

	/**
	 * Zmienna okre�laj�ca stan zapauzowania
	 */
	public static boolean paused = false;

	public Timer timer;

	public String[] a;

	public String b;

	/**
	 * Konstruktor klasy Game, przyjmuj�cy jako parametr Nick gracza. Tworzy
	 * map� i gracza, ustawia rozmiar.
	 * 
	 * @param nicktext
	 *            Nick gracza
	 */
	public Game(String nicktext) {

		Dimension dimension = new Dimension(Config.GameWidth, Config.GameHeight);
		setPreferredSize(dimension);
		nick = nicktext;
		player = new Player(Config.GameWidth / 2, Config.GameHeight / 2);

		// if (Main.online == true) {

		// String a = Client.getLevel(indeks + 1);
		// String b = a;

		// } else if (Main.online == false) {

		a = Config.filenameLevel;
		b = a[indeks];

		// }

		mapa = new Mapa(b);
		gui = new GUI();
		addKeyListener(this);
		setFocusable(true);

		class TimeListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!paused) {
					repaint();
					tick();
				}

			}
		}

		/**
		 * Obiekt odpowiedzialny za od�wie�anie gry
		 */
		ActionListener listener = new TimeListener();
		timer = new Timer(500 / Config.FPS, listener);
		timer.start();

	}

	/**
	 * Metoda odpowiadaj�ca za inicjacj� w�tku rysowania.
	 */

	@Override
	public void run() {
		if (isRunning)
			return;
		paused = false;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
		setFocusable(true);
	}

	/**
	 * Metoda ko�cz�ca program.
	 */
	public synchronized void stop() {
		if (!isRunning)
			return;
		isRunning = false;
		try {
			if (Player.win) {
				Frame.okno.dispose();
				timer.stop();
				Win win = new Win(nick, Player.points);
				HighScoreManager hm = new HighScoreManager();
				hm.addScore(nick, Player.points);

			} else {
				Frame.okno.dispose();
				timer.stop();
				Lose lose = new Lose(nick, Player.points);
				HighScoreManager hm = new HighScoreManager();
				hm.addScore(nick, Player.points);

			}
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda odpowiedzialna za aktualizacje mapy oraz gracza
	 */
	private void tick() {
		player.update(nick);
		mapa.tick();
	}

	/**
	 * Metoda odpowiedzialna za rysowanie planszy i wywo�ywanie poszczeg�lnych
	 * metod render(); dla danych sk�adowych mapy.
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void paintComponent(Graphics g) {

		BufferedImage dbImage = new BufferedImage(Config.GameWidth, Config.GameHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics dbg = dbImage.getGraphics();

		super.paintComponent(dbg);

		mapa.render(dbg);

		for (int i = 0; i < mapa.ball.size(); i++) {
			mapa.ball.get(i).render(dbg);
		}
		for (int i = 0; i < mapa.enemies.size(); i++) {
			mapa.enemies.get(i).render(dbg);
		}
		for (int i = 0; i < mapa.cherry.size(); i++) {
			mapa.cherry.get(i).render(dbg);
		}
		for (int i = 0; i < mapa.bonus.size(); i++) {
			mapa.bonus.get(i).render(dbg);
		}
		for (int i = 0; i < mapa.thunder.size(); i++) {
			mapa.thunder.get(i).render(dbg);
		}

		player.render(dbg);
		gui.render(dbg, nick, player.getPoints(), player.getLife());

		BufferedImage scaled = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D gg = scaled.createGraphics();
		gg.drawImage(dbImage, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(scaled, 0, 0, this);

		dbg.dispose();
		setFocusable(true);
	}

	/**
	 * Metoda opowiedialna za prze��czanie w stan zapauzowania
	 */
	public void togglePause() {
		paused = !paused;
	}

	/**
	 * Metoda odpowiedzialna za ruch Pacmana, reagowanie na naciskanie klawiszy.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			player.right = true;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			player.left = true;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			player.up = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			player.down = true;
		if (e.getKeyCode() == KeyEvent.VK_P)
			togglePause();
	}

	/**
	 * Metoda odpowiedzialna za ruch Pacmana, reagowanie na zwalnianie klawiszy.
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			player.right = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			player.left = false;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			player.up = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			player.down = false;

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
