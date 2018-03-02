package Pacman;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa Mapa reprezentuj¹ca mapê gry
 *
 * @author Damian Fronia
 * @author Zuzanna Kap³on
 * @version 1.0
 */
public class Mapa {

	/**
	 * Liczba rzêdów mapy
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
	 * Tablica obiektów typu Tile
	 */
	public Tile[][] tiles;

	/**
	 * Lista obiektów typu Cherry - owoców
	 */
	public List<Cherry> cherry;

	/**
	 * Lista obiektów typu Thunder - b³yskawic
	 */
	public List<Thunder> thunder;

	/**
	 * Lista obiektów typu Bonus
	 */
	public List<Bonus> bonus;

	/**
	 * Lista obiektów typu Ball
	 */
	public List<Ball> ball;

	/**
	 * Lista obiektów typu Enemy
	 */
	public List<Enemy> enemies;

	/**
	 * Lista obiektów typu Ground
	 */
	public List<Ground> ground;

	public static Player player;

	public Scanner in;

	public String line;
	public String[] line2;

	public int z = 0;

	/**
	 * Konstruktor obiektu Mapa odczytuje dane z pliku tekstowego, tworzy
	 * planszê gry i obiekty klasy (zgodnie z odczytan¹ z pliku literka):
	 * 
	 * w - Tile (pod³oga) b - Ball (kuleczka) g - Ground (pod³oga) e - Enemy
	 * (duszek) p - Player (Pacman) t - Thunder (b³yskawica) c - Cherry (owoc) o
	 * - Bonus (bonus)
	 * 
	 * @param path
	 *            œcie¿ka do pliku tekstowego, zawieraj¹cego planszê gry
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
	 * Metoda aktualizuj¹ca przeciwników - ich poruszanie siê
	 */
	public void tick() {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).tick();
		}
	}

	/**
	 * Metoda rysuj¹ca mapê, wywo³uje metody rysuj¹ce poszczególne obrazki
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
