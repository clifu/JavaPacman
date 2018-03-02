package Pacman;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 * Klasa Frame tworzy okno wy�wietlaj�ce si� gdy u�ytkownik b�dzie pr�bowa�
 * opu�ci� program.
 */
public class Frame extends JFrame {

	/**
	 * Numer seryjny
	 */
	private static final long serialVersionUID = 5896634232476845942L;

	/**
	 * Zmienna statyczna klasy Game, s�u�y odwo�ywaniu si� do parametr�w
	 * rozgrywki
	 */
	public static Game game;

	/**
	 * Zmienna statyczna klasy JFrame, s�u�y odwo�ywaniu si� do okna rozgrywki
	 */
	public static JFrame okno;

	public Frame(String nickname) {

		okno = new JFrame();
		game = new Game(nickname);
		okno.setTitle(Config.appName);
		okno.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		okno.add(game);
		okno.setResizable(true);
		okno.pack();
		okno.setLocationRelativeTo(null);
		okno.setVisible(true);

		game.run();
		okno.addWindowListener(new WindowAdapter() {

			/**
			 * Metoda wywo�uj�ca si�, gdy u�ytkownik pr�buje opu�ci� program lub
			 * zrobi to przez przypadek.
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				game.togglePause();
				int x = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz opu�cic program?", "Ostrze�enie!",
						JOptionPane.YES_NO_OPTION);
				if (x == JOptionPane.YES_OPTION) {
					game.stop();
					Game.indeks = 0;
					Player.points = Config.startScore;
					Player.life = Config.lives;
					Game.canEat = false;
					Game.canSpeedUp = false;
					e.getWindow().dispose();
				} else if (x == JOptionPane.NO_OPTION) {
					game.togglePause();
				} else if (x == JOptionPane.CLOSED_OPTION) {
					JOptionPane.showMessageDialog(null, "Nast�pi zamkni�cie programu!", "Uwaga!",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});

	}

}
