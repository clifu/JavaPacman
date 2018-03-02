package Pacman;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Rectangle;
import java.awt.Graphics;

/**
 * Klasa reprezentuj¹ca gracza - Pacman, dziedziczy po klasie Rectangle
 *
 * @author Damian Fronia
 * @author Zuzanna Kap³on
 * @version 1.0
 */
public class Player extends Rectangle {

	/**
	 * Numer seryjny
	 */
	private static final long serialVersionUID = 1729337417931602650L;

	/**
	 * Zmienna s³u¿¹ca przechowywaniu stanu rozgrywki.
	 */
	public static boolean win = false;

	/**
	 * Obiekt klasy Frame - okno rozgrywki
	 */
	public static Frame frame;

	/**
	 * Zmienna klasy Image, s³u¿y prechowywaniu obrazka obiektów klasy Player
	 */
	private Image player;

	/**
	 * Obiekt klasy Bonus, s³u¿y do odwo³ania siê do jego elemntów w klasie
	 * Player
	 */
	public static Bonus bonus;

	/**
	 * Obiekt klasy Enemy, s³u¿y do odwo³ania siê do jego elemntów w klasie
	 * Player
	 */
	public static Enemy enemy;

	/**
	 * Punkty gromadzone w trakcie rozgrywki, pocz¹tkowa wartoœæ 0
	 */
	public static int points = 0;

	/**
	 * Iloœæ "¿yæ" Gracza, pocz¹tkowa wartoœæ 3
	 */
	public static int life = 3;

	/**
	 * Obiekt klasy Bonus, s³u¿y do odwo³ania siê do jego elemntów w klasie
	 * Player
	 */
	public ImageIcon img;

	/**
	 * Obiekt klasy Game, s³u¿y do odwo³ania siê do jego elemntów w klasie
	 * Player
	 */
	public static Game game;

	/**
	 * String a
	 */
	public String[] a;

	/**
	 * String b
	 */
	public String b;
	/**
	 * Zmienna pomocnicza, s³u¿y przechowywaniu prêdkoœci zapisanej w pliku
	 * config.properties
	 */
	public static int pspeed = Config.PacmanSpeed;;

	/**
	 * Zmienna pomocnicza, s³u¿y do przechowywania zwiêkszonej prêdkosci Pacmana
	 * po uzyskaniu bonusu
	 */
	public static int pspeedup;

	/**
	 * Zmienna pomocnicza, s³u¿y do przechowywania pocz¹tkowej wartoœci
	 * po³o¿enia x Pacmana
	 */
	public int bx;

	/**
	 * Zmienna pomocnicza, s³u¿y do przechowywania pocz¹tkowej wartoœci
	 * po³o¿enia y Pacmana
	 */
	public int by;

	/**
	 * Zmienne obs³ugj¹ce ruch Pacmana
	 */
	public boolean right, left, down, up;

	/**
	 * Konstruktor obiektu Player - gracza
	 * 
	 * @param x
	 *            Pozycja x
	 * @param y
	 *            Pozycja y
	 * 
	 */
	public Player(int x, int y) {
		setBounds(x, y, 32, 32);

	}

	/**
	 * Funkcja odpowiedzialna za pobranie obrazka reprezentujacego obiekt klasy
	 * Player
	 * 
	 * @return player - obrazek reprezentujacy obiekt
	 */
	public Image getPlayer() {
		return player;
	}

	/**
	 * Metoda aktualizuj¹ca ruch Pacmana oraz interakcje z innymi obiektami
	 * 
	 * @param nick
	 *            Nick Gracza
	 */
	public void update(String nick) {

		if (!Game.paused) {
			if (!Game.canSpeedUp) {
				if (right && canMove(x + pspeed, y))
					x += pspeed;
				if (left && canMove(x - pspeed, y))
					x -= pspeed;
				if (up && canMove(x, y - pspeed))
					y -= pspeed;
				if (down && canMove(x, y + pspeed))
					y += pspeed;
			} else {
				if (right && canMove(x + pspeedup, y))
					x += pspeedup;
				if (left && canMove(x - pspeedup, y))
					x -= pspeedup;
				if (up && canMove(x, y - pspeedup))
					y -= pspeedup;
				if (down && canMove(x, y + pspeedup))
					y += pspeedup;
			}
		}
		Mapa mapa = Game.mapa;
		for (int i = 0; i < Game.mapa.ball.size(); i++) {
			if (this.intersects(Game.mapa.ball.get(i))) {
				Game.mapa.ball.remove(i);
				points += 1;
				System.out.println(points);
				break;
			}

		}

		for (int i = 0; i < Game.mapa.bonus.size(); i++) {
			if (this.intersects(Game.mapa.bonus.get(i))) {
				Game.mapa.bonus.remove(i);
				points += Config.bonus1Points;
				System.out.println(points);
				System.out.println("Bierz go!");
				Game.start = (int) System.currentTimeMillis();
				for (int k = 0; k < Game.mapa.enemies.size(); k++) {
					Game.mapa.enemies.get(k).setState(0);
				}
				Game.canEat = true;
				break;
			}

		}

		for (int i = 0; i < Game.mapa.cherry.size(); i++) {
			if (this.intersects(Game.mapa.cherry.get(i))) {
				Game.mapa.cherry.remove(i);
				points += Config.bonus2Points;
				System.out.println(points);
				break;
			}

		}

		for (int i = 0; i < Game.mapa.thunder.size(); i++) {
			if (this.intersects(Game.mapa.thunder.get(i))) {
				Game.mapa.thunder.remove(i);
				Game.start2 = (int) System.currentTimeMillis();
				Game.canSpeedUp = true;
				pspeedup = pspeed + 1;
				break;
			}

		}

		if (mapa.ball.size() == 0) {
			Game.indeks = (Game.indeks) + 1;
			if (Game.indeks < Config.filenameLevel.length) {
				a = Config.filenameLevel;
				b = a[Game.indeks];
				Game.canEat = false;
				Game.canSpeedUp = false;
				JOptionPane.showMessageDialog(null, "Nastêpna mapa!");
				JOptionPane.getRootFrame().dispose();
				Game.player = new Player(0, 0);
				Game.mapa = new Mapa(b);
				// Config.EnemySpeed1 = Config.EnemySpeed1 +1;
			} else {
				Game.indeks = 0;
				a = Config.filenameLevel;
				b = a[Game.indeks];
				win = true;
				Frame.game.stop();
				points = 0;
				life = 3;
				Game.canEat = false;
				Game.canSpeedUp = false;
				// JOptionPane.showMessageDialog(null, "Koniec gry ! Gratulacje
				// ! Wygra³eœ !");
				// JOptionPane.getRootFrame().dispose();
				// Game.player = new Player(0, 0);
				// Game.mapa = new Mapa(b);
			}
			return;
		}

		for (int k = 0; k < Game.mapa.enemies.size(); k++) {
			if (this.intersects(Game.mapa.enemies.get(k))) {
				if (!Game.canEat) {
					life--;
					JOptionPane.showMessageDialog(null, "Ups!");
					JOptionPane.getRootFrame().dispose();
					Game.player.left = false;
					Game.player.right = false;
					Game.player.up = false;
					Game.player.down = false;
					Game.player.moveTo(Game.player.bx, Game.player.by);
					for (int i = 0; i < Game.mapa.enemies.size(); i++)
						Game.mapa.enemies.get(i).moveTo(Game.mapa.enemies.get(i).bx, Game.mapa.enemies.get(i).by);
					if (life <= 0) {
						win = false;
						Frame.game.stop();
						Game.indeks = 0;
						points = Config.startScore;
						life = Config.lives;
						Game.canEat = false;
						Game.canSpeedUp = false;
						return;
					}
				} else {
					Game.mapa.enemies.remove(k);
					points += Config.enemyPoints;
					System.out.println("Zjedzono przeciwnika!");
				}
			}
		}

		if ((int) System.currentTimeMillis() - Game.start2 >= Config.thunderDuration)
			Game.canSpeedUp = false;

		if ((int) System.currentTimeMillis() - Game.start >= Config.bonus1Duration) {
			Game.canEat = false;
			for (int k = 0; k < Game.mapa.enemies.size(); k++) {
				Game.mapa.enemies.get(k).setState(1);
			}
		}
	}

	/**
	 * Metoda sprawdzajaca czy Pacman mo¿e poruszaæ siê w danym kierunku. Bada
	 * zderzenia Pacmana ze œcianami
	 * 
	 * @param nextx
	 *            Wartoœæ x w kierunku którego chce poruszyæ siê Pacman
	 * @param nexty
	 *            Wartoœæ y w kierunku którego chce poruszyæ siê Pacman
	 * @return Zmienna typu boolean - okreœla czy mo¿liwy jest ruch
	 */
	public boolean canMove(int nextx, int nexty) {
		Rectangle bounds = new Rectangle(nextx, nexty, 29, 29);
		Mapa mapa = Game.mapa;

		for (int x = 0; x < Mapa.MapSize; x++)
			for (int y = 0; y < Mapa.MapSize; y++) {
				if (mapa.tiles[x][y] != null) {
					if (bounds.intersects(mapa.tiles[x][y])) {
						return false;
					}
				}
			}

		return true;

	}

	/**
	 * Metoda rysuj¹ca gracza - Pacmana
	 * 
	 * @param g
	 *            Kontekst graficzny
	 */
	public void render(Graphics g) {

		if (!Game.canEat) {
			img = new ImageIcon("PacmanLEFT.png");
		} else {
			img = new ImageIcon("angryPacman.png");
		}
		player = img.getImage();
		g.drawImage(player, x, y, null);
	}

	/**
	 * Metoda zwracaj¹ca iloœæ uzyskanych punktów w rozgrywce
	 * 
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Metoda zwracaj¹ca iloœæ ¿yæ gracza
	 * 
	 */
	public int getLife() {
		return life;
	}

	/**
	 * Metoda zwracaj¹ca po³o¿enie x
	 * 
	 */
	public double getX() {
		return x;
	}

	/**
	 * Metoda zwracaj¹ca po³o¿enie y
	 * 
	 */
	public double getY() {
		return y;
	}

	/**
	 * Metoda przesuwaj¹ca Pacmana na konkretna pozycjê
	 * 
	 * @param x
	 *            Wartoœæ x w której umieszczony ma zostaæ Pacman
	 * @param y
	 *            Wartoœæ x w której umieszczony ma zostaæ Pacman
	 */
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
