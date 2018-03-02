package Pacman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Klasa reprezentuj�ca przeciwnika - duszka, dziedziczy po klasie Rectangle
 *
 * @author Damian Fronia
 * @author Zuzanna Kap�on
 * @version 1.0
 */
public class Enemy extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4005252828940562616L;

	/**
	 * Zmienna klasy Image, s�u�y prechowywaniu obrazka obiekt�w klasy Enemy
	 */
	private Image enemy;

	/**
	 * Zmienne okre�laj�ce parametry poruszania si� duszk�w
	 */
	private static int random = 0, smart = 1, find_path = 2;

	/**
	 * Zmienna pomocnicza s�u�y do ustawienia pocz�tkowego sposobu poruszania
	 * si� duszk�w
	 */
	public static int state = random;

	/**
	 * Zmienne okre�laj�ce ruch duszk�w
	 */
	private int right = 0, left = 1, up = 2, down = 3;
	/**
	 * Zmienna okreslajaca kierunek ruchu przeciwnika
	 */
	private int direction = -1;

	/**
	 * Obiekt klasy Random - s�u�y jako generator pseudolosowy
	 */
	public Random random2;

	/**
	 * Zmienna odliczajaca czas
	 */
	private int time = 0;

	/**
	 * Zmienna odpowiadajaca za przelaczanie stanu przeciwnikow z random na
	 * smart
	 */

	private int TargetTime = 60 * 4;

	/**
	 * Zmienna pami�taj�ca ostatni kierunek duszka
	 */
	private int lastdirection = -1;

	/*
	 * Zmienna s�u��ca zapami�taniu pozycji pocz�tkowej x duszka
	 */
	public int bx;

	/*
	 * Zmienna s�u��ca zapami�taniu pozycji pocz�tkowej y duszka
	 */
	public int by;

	/**
	 * Konstruktor obiektu Enemy - duszka
	 * 
	 * @param x
	 *            Pozycja x
	 * @param y
	 *            Pozycja y
	 * 
	 */
	public Enemy(int x, int y) {
		setBounds(x, y, 32, 32);
		ImageIcon img = new ImageIcon("enemy.png");
		enemy = img.getImage();
		random2 = new Random();
		direction = random2.nextInt(4);
		bx = x;
		by = y;
	}

	/**
	 * Funkcja odpowiedzialna za pobranie obrazka reprezentujacego obiekt klasy
	 * Enemy
	 * 
	 * @return enemy - obrazek reprezentujacy obiekt
	 */
	public Image getEnemy() {
		return enemy;
	}

	/**
	 * Funkcja odpowiedzialna za ustalenie kierunku duszka, ma stany random
	 * (poruszanie losowe) oraz smart (pod��anie za Pacmanem)
	 */
	public void tick() {

		/**
		 * W tym stanie przeciwnik porusza si� losowo po mapie
		 */
		if (state == random) {

			if (direction == right) {
				if (canMove(x + Config.EnemySpeed1, y)) {
					x += Config.EnemySpeed1;
				} else {
					direction = random2.nextInt(4);
				}
			}

			if (direction == left) {
				if (canMove(x - Config.EnemySpeed1, y)) {
					x -= Config.EnemySpeed1;
				} else {
					direction = random2.nextInt(4);
				}
			}

			if (direction == up) {
				if (canMove(x, y - Config.EnemySpeed1)) {
					y -= Config.EnemySpeed1;
				} else {
					direction = random2.nextInt(4);
				}
			}

			if (direction == down) {
				if (canMove(x, y + Config.EnemySpeed1)) {
					y += Config.EnemySpeed1;
				} else {
					direction = random2.nextInt(4);
				}
				time++;

				if (time == TargetTime) {
					state = random;
					time = 0;
				}
			}
			/**
			 * W tym stanie przeciwnik goni Pacmana
			 */
		} else if (state == smart) {

			boolean move = false;

			if (x < Game.player.x) {
				if (canMove(x + Config.EnemySpeed1, y)) {
					x += Config.EnemySpeed1;
					move = true;
					lastdirection = right;
				}
			}
			if (x > Game.player.x) {
				if (canMove(x - Config.EnemySpeed1, y)) {
					x -= Config.EnemySpeed1;
					move = true;
					lastdirection = left;
				}
			}
			if (y < Game.player.y) {
				if (canMove(x, y + Config.EnemySpeed1)) {
					y += Config.EnemySpeed1;
					move = true;
					lastdirection = down;
				}
			}
			if (y > Game.player.y) {
				if (canMove(x, y - Config.EnemySpeed1)) {
					y -= Config.EnemySpeed1;
					move = true;
					lastdirection = up;
				}
			}

			if (x == Game.player.x && y == Game.player.y)
				move = true;

			if (!move) {
				state = find_path;
			}

		} else if (state == find_path) {
			if (lastdirection == right) {

				if (y < Game.player.y) {
					if (canMove(x, y + Config.EnemySpeed1)) {
						y += Config.EnemySpeed1;
						state = smart;
					}
				} else {
					if (canMove(x, y - Config.EnemySpeed1)) {
						y -= Config.EnemySpeed1;
						state = smart;
					}
				}
				if (canMove(x + Config.EnemySpeed1, y)) {
					x += Config.EnemySpeed1;
				}
			} else if (lastdirection == left) {
				if (y < Game.player.y) {
					if (canMove(x, y + Config.EnemySpeed1)) {
						y += Config.EnemySpeed1;
						state = smart;
					}
				} else {
					if (canMove(x, y - Config.EnemySpeed1)) {
						y -= Config.EnemySpeed1;
						state = smart;
					}
				}
				if (canMove(x - Config.EnemySpeed1, y)) {
					x -= Config.EnemySpeed1;
				}
			} else if (lastdirection == up) {
				if (x < Game.player.x) {
					if (canMove(x + Config.EnemySpeed1, y)) {
						x += Config.EnemySpeed1;
						state = smart;
					}
				} else {
					if (canMove(x - Config.EnemySpeed1, y)) {
						x -= Config.EnemySpeed1;
						state = smart;
					}
				}
				if (canMove(y - Config.EnemySpeed1, y)) {
					y -= Config.EnemySpeed1;
				}
			}

			else if (lastdirection == down) {
				if (x < Game.player.x) {
					if (canMove(x + Config.EnemySpeed1, y)) {
						x += Config.EnemySpeed1;
						state = smart;
					}
				} else {
					if (canMove(x - Config.EnemySpeed1, y)) {
						x -= Config.EnemySpeed1;
						state = smart;
					}
				}
				if (canMove(y + Config.EnemySpeed1, y)) {
					y += Config.EnemySpeed1;
				}
			}
		}
	}

	/**
	 * Funkcja odpowiedzialna za sprawdzenie kolizji duszk�w ze �cianami
	 * 
	 * @param nextx
	 *            Warto�� x na kt�r� kieruje si� duszek
	 * @param nexty
	 *            Warto�� y na kt�r� kieruje si� duszek
	 * @return Warto�� boolean - czy wyst�pi�a kolizja
	 */
	public boolean canMove(int nextx, int nexty) {
		Rectangle bounds = new Rectangle(nextx, nexty, 30, 30);
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
	 * Funkcja pozwalaj�ca ustawi� stan Inteligencji poruszania si� duszk�w
	 * 
	 * @param a
	 *            zmienna wp�ywaj�ca na zmiane stanu
	 */
	public void setState(int a) {
		if (a == 0) {
			state = random;
		} else {
			state = smart;
		}
	}

	/**
	 * Funkcja odpowiadaj�ca za wyrenderowanie obrazka reprezentujacego obiekt
	 * klasy Enemy
	 * 
	 * @param g
	 *            Kontekst graficzny
	 */
	public void render(Graphics g) {

		g.drawImage(enemy, x, y, null);
	}

	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
