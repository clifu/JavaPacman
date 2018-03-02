package Pacman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Klasa reprezentuj¹ca przeciwnika - duszka, dziedziczy po klasie Rectangle
 *
 * @author Damian Fronia
 * @author Zuzanna Kap³on
 * @version 1.0
 */
public class Enemy extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4005252828940562616L;

	/**
	 * Zmienna klasy Image, s³u¿y prechowywaniu obrazka obiektów klasy Enemy
	 */
	private Image enemy;

	/**
	 * Zmienne okreœlaj¹ce parametry poruszania siê duszków
	 */
	private static int random = 0, smart = 1, find_path = 2;

	/**
	 * Zmienna pomocnicza s³u¿y do ustawienia pocz¹tkowego sposobu poruszania
	 * siê duszków
	 */
	public static int state = random;

	/**
	 * Zmienne okreœlaj¹ce ruch duszków
	 */
	private int right = 0, left = 1, up = 2, down = 3;
	/**
	 * Zmienna okreslajaca kierunek ruchu przeciwnika
	 */
	private int direction = -1;

	/**
	 * Obiekt klasy Random - s³u¿y jako generator pseudolosowy
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
	 * Zmienna pamiêtaj¹ca ostatni kierunek duszka
	 */
	private int lastdirection = -1;

	/*
	 * Zmienna s³u¿¹ca zapamiêtaniu pozycji pocz¹tkowej x duszka
	 */
	public int bx;

	/*
	 * Zmienna s³u¿¹ca zapamiêtaniu pozycji pocz¹tkowej y duszka
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
	 * (poruszanie losowe) oraz smart (pod¹¿anie za Pacmanem)
	 */
	public void tick() {

		/**
		 * W tym stanie przeciwnik porusza siê losowo po mapie
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
	 * Funkcja odpowiedzialna za sprawdzenie kolizji duszków ze œcianami
	 * 
	 * @param nextx
	 *            Wartoœæ x na któr¹ kieruje siê duszek
	 * @param nexty
	 *            Wartoœæ y na któr¹ kieruje siê duszek
	 * @return Wartoœæ boolean - czy wyst¹pi³a kolizja
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
	 * Funkcja pozwalaj¹ca ustawiæ stan Inteligencji poruszania siê duszków
	 * 
	 * @param a
	 *            zmienna wp³ywaj¹ca na zmiane stanu
	 */
	public void setState(int a) {
		if (a == 0) {
			state = random;
		} else {
			state = smart;
		}
	}

	/**
	 * Funkcja odpowiadaj¹ca za wyrenderowanie obrazka reprezentujacego obiekt
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
