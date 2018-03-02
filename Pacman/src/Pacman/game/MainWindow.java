package Pacman.game;

import Pacman.*;
import Pacman.Frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Klasa okna g��wnego
 * <p>
 * Jedna z instacji tej klasy tworzona jest podczas uruchamiania programu
 * 
 * @author Damian Fronia
 * @author Zuzanna Kap�on
 * @version 1.0
 */
public class MainWindow extends JFrame {

	/**
	 * Numer seryjny
	 */
	private static final long serialVersionUID = 3548723877379361285L;

	/**
	 * Identyfikator panelu menuPanel
	 */
	final static String MENUPANEL = "menuPanel";

	/**
	 * Identyfikator panelu hifhScoresPanel
	 */
	final static String HIGHSCORESPANEL = "highScoresPanel";

	/**
	 * Identyfikator panelu startPanel
	 */
	final static String STARTPANEL = "startPanel";

	/**
	 * Identyfikator panelu gamePanel
	 */
	final static String GAMEPANEL = "gamePanel";

	/**
	 * Identyfikator panelu rulesPANEL
	 */
	final static String RULESPANEL = "rulesPanel";

	/**
	 * Panel g�owny layoutu CardLayout
	 */
	private JPanel cards;

	/**
	 * Panel z zawartoscia menu glownego
	 */
	private JPanel menuPanel;

	/**
	 * Panel wyswietlajacy Liste Wynikow
	 */
	private JPanel highScoresPanel;

	/**
	 * Panel w ktorym podajemy nick gracza
	 */
	private JPanel startPanel;

	/**
	 * Pole tekstowe, do kt�rego wprowadzana jest nazwa gracza
	 */
	private JTextField nicknameTextField;

	/**
	 * Panel wy�wietlaj�cy zasady gry
	 */
	private JPanel rulesPanel;

	/**
	 * Pole tekstowe wyswietlaj�ce liste najlepszych wynik�w
	 */
	private JTextArea hsTextArea;

	/**
	 * Zmienna s�u��ca do odwo�ywania si� do menad�era najlepszych wynik�w
	 */
	public HighScoreManager hm;

	/**
	 * Konstruktor Menu g��wnego
	 * <p>
	 * Inicjalizuje jedn� z instancji przez metod� init()
	 */
	public MainWindow() {
		init();
	}

	/**
	 * Tworzy okno g��wne i wy�wietla odpowiednie panele menu
	 */
	private void init() {

		this.setTitle(Config.appName);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setSize(Config.windowWidth, Config.windowHeight);

		// Za pomoc� tego wy�rodkujemy elementy znajduj�ce si� na ekranie
		this.setLocationRelativeTo(null);
		cards = new JPanel(new CardLayout());

		createMenu();
		createStart();
		createHighScores();
		createRulesPanel();

		cards.add(menuPanel, MENUPANEL);
		cards.add(highScoresPanel, HIGHSCORESPANEL);
		cards.add(startPanel, STARTPANEL);
		cards.add(rulesPanel, RULESPANEL);

		if (Main.online) {

			// hsTextArea.setText(Client.getHighscores());

		} else {

			hsTextArea.setText(hm.getHighscoreString());
		}

		CardLayout cardLayout = (CardLayout) cards.getLayout();
		cardLayout.show(cards, MENUPANEL);

		// Dodajemy stworzone elementy do ogl�danej warstwy, jednej z wielu z
		// kontenera SWING
		this.getContentPane().add(cards);

	}

	/**
	 * Wy�wietla okno g��wne.
	 */
	public void showWindow() {

		this.setVisible(true);
	}

	/**
	 * Metoda odpowiedzialna za stowrzenie panelu menu g��wnego
	 */
	private void createMenu() {

		menuPanel = new JPanel((new GridBagLayout()));

		GridBagConstraints a = new GridBagConstraints();
		a.gridx = 0;
		a.gridy = 0;
		a.insets = new Insets(10, 10, 10, 10);
		JButton startButton = new JButton(Config.menuStart);
		startButton.addActionListener(new StartButtonListener());
		menuPanel.add(startButton, a);

		a.gridy = 1;
		JButton rulesButton = new JButton(Config.menuRules);
		rulesButton.addActionListener(new RulesButtonListener());
		menuPanel.add(rulesButton, a);

		a.gridy = 2;
		JButton highScoresButton = new JButton(Config.menuHighscore);
		highScoresButton.addActionListener(new HighScoresButtonListener());
		menuPanel.add(highScoresButton, a);

		a.gridy = 3;
		JButton exitButton = new JButton(Config.menuExit);
		exitButton.addActionListener(new ExitButtonListener());
		menuPanel.add(exitButton, a);

		a.gridy = 4;
		JLabel connectionInfo = new JLabel();
		if (Main.online) {
			connectionInfo.setText("Tryb: online");
			connectionInfo.setForeground(Color.GREEN);
		} else {
			connectionInfo.setText("Tryb: offline");
			connectionInfo.setForeground(Color.RED);
		}
		menuPanel.add(connectionInfo, a);

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				int x = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz opu�cic program?", "Ostrze�enie!",
						JOptionPane.YES_NO_OPTION);
				if (x == JOptionPane.YES_OPTION) {
					e.getWindow().dispose();
				} else if (x == JOptionPane.NO_OPTION) {
				} else if (x == JOptionPane.CLOSED_OPTION) {
					JOptionPane.showMessageDialog(null, "Nast�pi zamkni�cie programu!", "Uwaga!",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
	}

	/**
	 * Metoda odpowiedzialna za stowrzenie panelu startu gry
	 */
	private void createStart() {
		startPanel = new JPanel(new BorderLayout());
		JPanel buttons = new JPanel(new GridBagLayout());
		startPanel.add(buttons);

		GridBagConstraints a = new GridBagConstraints();
		a.gridx = 0;
		a.gridy = 0;
		a.insets = new Insets(10, 10, 10, 10);

		JLabel nickLabel = new JLabel(Config.defaultNickname);
		buttons.add(nickLabel, a);

		a.gridy = 1;
		a.insets = new Insets(1, 10, 30, 10);
		nicknameTextField = new JTextField(Config.defaultNickname, 20);
		buttons.add(nicknameTextField, a);

		nicknameTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				nicknameTextField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (nicknameTextField.getText().trim().equals("")) {
					nicknameTextField.setText(Config.defaultNickname);
					nicknameTextField.setForeground(Color.BLACK);
				}
			}
		});

		a.gridy = 2;
		a.insets = new Insets(10, 10, 10, 10);
		JButton runButton = new JButton(Config.menuStart);
		runButton.addActionListener(new RunButtonListener());
		buttons.add(runButton, a);

		JButton backButton = new JButton(Config.menuStartBack);
		backButton.addActionListener(new BackButton2Listener());
		startPanel.add(backButton, BorderLayout.SOUTH);
	}

	/**
	 * Metoda odpowiedzialna za stowrzenie panelu zasad
	 */
	private void createRulesPanel() {
		rulesPanel = new JPanel(new BorderLayout());

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);

		try {
			BufferedReader reader = new BufferedReader(new FileReader("rules.txt"));
			textArea.read(reader, null);
			reader.close();
		} catch (Exception e) {
		}

		rulesPanel.add(textArea, BorderLayout.CENTER);

		JButton back = new JButton(Config.menuHighscoreBack);
		back.addActionListener(new BackButton1Listener());
		rulesPanel.add(back, BorderLayout.SOUTH);
	}

	/**
	 * Metoda odpowiedzialna za stowrzenie panelu najlepszych wynik�w
	 */
	private void createHighScores() {

		hm = new HighScoreManager();
		highScoresPanel = new JPanel(new BorderLayout());
		JPanel gbl = new JPanel(new GridBagLayout());
		highScoresPanel.add(gbl);

		GridBagConstraints a = new GridBagConstraints();
		a.gridx = 0;
		a.gridy = 0;
		a.insets = new Insets(10, 10, 10, 10);

		hsTextArea = new JTextArea(Config.highScoresRows, Config.highScoresColumns);
		hsTextArea.setText(hm.getHighscoreString());
		hsTextArea.setEditable(false);
		hsTextArea.setBackground(highScoresPanel.getBackground());
		gbl.add(hsTextArea, a);

		JButton backButton = new JButton(Config.menuHighscoreBack);
		backButton.addActionListener(new BackButton1Listener());
		highScoresPanel.add(backButton, BorderLayout.SOUTH);
	}

	/**
	 * S�uchacz przycisku Start w menu g��wnym
	 * <p>
	 * Wy�wietla panel z miejscem do wpisania nazwy u�ytkownika
	 */
	public class StartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			CardLayout cardLayout = (CardLayout) cards.getLayout();
			cardLayout.show(cards, STARTPANEL);
		}
	}

	/**
	 * S�uchacz przycisku Jak grac w menu g��wnym
	 * <p>
	 * Wy�wietla panel z zasadami gry
	 */
	public class RulesButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = (CardLayout) cards.getLayout();
			cardLayout.show(cards, RULESPANEL);
		}
	}

	/**
	 * S�uchacz przycisku Najlepsze wyniki w menu g��wnym
	 * <p>
	 * Wyswietla panel z najlepszymi wynikami
	 */
	public class HighScoresButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			hsTextArea.setText(hm.getHighscoreString());
			// Client.getHighscores();
			CardLayout cardLayout = (CardLayout) cards.getLayout();
			cardLayout.show(cards, HIGHSCORESPANEL);
		}
	}

	/**
	 * S�uchacz przycisku Cofnij do menu g��wnego
	 * <p>
	 * Cofa do menu g��wnego
	 */
	public class BackButton1Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = (CardLayout) cards.getLayout();
			cardLayout.show(cards, MENUPANEL);
		}
	}

	/**
	 * S�uchacz przycisku Powr�t do menu g��wnego z panelu Start
	 * <p>
	 * Powraca do menu g��wnego i cofa wprowadzony nickname
	 */
	public class BackButton2Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			nicknameTextField.setText(Config.defaultNickname);
			nicknameTextField.setForeground(Color.BLACK);
			CardLayout cardLayout = (CardLayout) cards.getLayout();
			cardLayout.show(cards, MENUPANEL);
		}
	}

	/**
	 * S�uchacz przycisku start w panelu Start
	 * <p>
	 * Rozpoczyna rozgrywk�
	 */
	public class RunButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!nicknameTextField.getText().trim().equals(Config.defaultNickname)
					&& (!nicknameTextField.getText().contains("/") && !nicknameTextField.getText().contains("#"))) {

				String nicktext = nicknameTextField.getText();
				Frame frame = new Frame(nicktext);
				CardLayout cardLayout = (CardLayout) cards.getLayout();
				cardLayout.show(cards, MENUPANEL);

			} else {
				nicknameTextField.setForeground(Color.RED);
			}
		}
	}

	/**
	 * Sluchacz przycisku Koniec w menu glownym
	 * <p>
	 * Zamyka program
	 */
	public class ExitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.exit(0);
		}
	}

}
