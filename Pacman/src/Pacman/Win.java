package Pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Win extends JFrame implements ActionListener {

	/**
	 * Numer seryjny
	 */
	private static final long serialVersionUID = 5118931618674081973L;

	/**
	 * Deklaracje przycisków umieszczonych w podsumowaniu
	 */
	JButton end;

	/**
	 * Dekralacje komponentów klasy JLabel
	 */
	JLabel nicklabel, punktylabel;

	/**
	 * Zmienna statyczna klasy JFrame, s³u¿y do odwo³ywania siê do okna
	 * podsumownia
	 */
	public static JFrame okno;

	/**
	 * Zmienna okreœlaj¹ca czcionkê - Font
	 */
	private Font Userfont;

	/**
	 * Konstruktor obiektu klasy WIn
	 * 
	 * @param nickname
	 *            - nazwa gracza
	 * @param points
	 *            - iloœæ uzyskanych punktów
	 */
	public Win(String nickname, int points) {

		okno = new JFrame();
		okno.setTitle("Podsumowanie wygranej!");
		okno.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		okno.setResizable(true);
		okno.setLayout(new GridLayout(3, 1));
		okno.setSize(500, 300);

		punktylabel = new JLabel("Punkty:" + points, SwingConstants.CENTER);
		okno.add(punktylabel);
		punktylabel.setFont(Userfont);
		punktylabel.setForeground(Color.BLACK);

		nicklabel = new JLabel(Config.defaultNickname + ":" + nickname, SwingConstants.CENTER);
		okno.add(nicklabel);
		nicklabel.setFont(Userfont);
		nicklabel.setForeground(Color.BLACK);

		end = new JButton("Wróæ do menu!");
		okno.add(end);
		end.addActionListener(this);
		okno.setLocationRelativeTo(null);
		okno.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == end)
			okno.dispose();
	}

}