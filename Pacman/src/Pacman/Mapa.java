package Pacman;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa Mapa reprezentuj�ca map� gry
 *
 * @author Damian Fronia
 * @author Zuzanna Kap�on
 * @version 1.0
 */
public class Mapa {

	/**
	 * Liczba rz�d�w mapy
	 */
	public int width = Config.notx;

	/**
	 * Liczba kolumn mapy
	 */
	public int height = Config.noty;

	/**
	 * Rozmiar mapy
	 */
	static public int MapSize;

	/**
	 * Tablica obiekt�w typu Tile
	 */
	public Tile[][] tiles;

	/**
	 * Lista obiekt�w typu Cherry - owoc�w
	 */
	public List<Cherry> cherry;

	/**
	 * Lista obiekt�w typu Thunder - b�yskawic
	 */
	public List<Thunder> thunder;

	/**
	 * Lista obiekt�w typu Bonus
	 */
	public List<Bonus> bonus;

	/**
	 * Lista obiekt�w typu Ball
	 */
	public List<Ball> ball;

	/**
	 * Lista obiekt�w typu Enemy
	 */
	public List<Enemy> enemies;

	/**
	 * Lista obiekt�w typu Ground
	 */
	public List<Ground> ground;

	public static Player player;

	public Scanner in;

	public String line;
	public String[] line2;

	public int z = 0;

	/**
	 * Konstruktor obiektu Mapa odczytuje dane z pliku tekstowego, tworzy
	 * plansz� gry i obiekty klasy (zgodnie z odczytan� z pliku literka):
	 * 
	 * w - Tile (pod�oga) b - Ball (kuleczka) g - Ground (pod�oga) e - Enemy
	 * (duszek) p - Player (Pacman) t - Thunder (b�yskawica) c - Cherry (owoc) o
	 * - Bonus (bonus)
	 * 
	 * @param path
	 *            �cie�ka do pliku tekstowego, zawieraj�cego plansz� gry
	 * 
	 */

	public Mapa(String path) {

		ball = new ArrayList<>();
		enemies = new ArrayList<>();
		ground = new ArrayList<>();
		cherry = new ArrayList<>();
		thunder = new ArrayList<>();
		bonus = new ArrayList<>();
		tiles = new Tile[Config.notx][Config.noty];

		try {

			in = new Scanner(new File(path));
			int yy = 0;
			int xx = 0;

			while (in.hasNextLine()) {
				String line = in.nextLine();
				MapSize = line.length();
				for (xx = 0; xx < line.length(); xx++) {
					if (line.charAt(xx) == 'w') {
						tiles[xx][yy] = new Tile(xx * Config.tileSize, yy * Config.tileSize);
					} else if (line.charAt(xx) == 'p') {
						// Game.player.x = xx * Config.tileSize;
						// Game.player.y = yy * Config.tileSize;
						Game.player.moveTo(xx * Config.tileSize, yy * Config.tileSize);
						Game.player.bx = xx * Config.tileSize;
						Game.player.by = yy * Config.tileSize;
						ground.add(new Ground(xx * Config.tileSize, yy * Config.tileSize));
					} else if (line.charAt(xx) == 'e') {
						enemies.add(new Enemy(xx * Config.tileSize, yy * Config.tileSize));
						ball.add(new Ball(xx * Config.tileSize, yy * Config.tileSize));
						ground.add(new Ground(xx * Config.tileSize, yy * Config.tileSize));
					} else if (line.charAt(xx) == 'b') {
						ball.add(new Ball(xx * Config.tileSize, yy * Config.tileSize));
						ground.add(new Ground(xx * Config.tileSize, yy * Config.tileSize));
					} else if (line.charAt(xx) == 'o') {
						bonus.add(new Bonus(xx * Config.tileSize, yy * Config.tileSize));
						ground.add(new Ground(xx * Config.tileSize, yy * Config.tileSize));
					} else if (line.charAt(xx) == 'c') {
						cherry.add(new Cherry(xx * Config.tileSize, yy * Config.tileSize));
						ground.add(new Ground(xx * Config.tileSize, yy * Config.tileSize));
					} else if (line.charAt(xx) == 't') {
						thunder.add(new Thunder(xx * Config.tileSize, yy * Config.tileSize));
						ground.add(new Ground(xx * Config.tileSize, yy * Config.tileSize));
					} else {
						ground.add(new Ground(xx * Config.tileSize, yy * Config.tileSize));
					}
				}
				yy++;
			}

			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
	}

	/**
	 * Metoda aktualizuj�ca przeciwnik�w - ich poruszanie si�
	 */
	public void tick() {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).tick();
		}
	}

	/**
	 * Metoda rysuj�ca map�, wywo�uje metody rysuj�ce poszczeg�lne obrazki
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void render(Graphics g) {

		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				if (tiles[x][y] != null)
					tiles[x][y].render(g);
			}

		for (int i = 0; i < ground.size(); i++) {
			ground.get(i).render(g);
		}
	}

}
