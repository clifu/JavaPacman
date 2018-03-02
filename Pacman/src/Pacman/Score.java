package Pacman;

import java.io.Serializable;

/**
 * Klasa zawieraj¹ca informacjê o wyniku danej rozgrywki.
 * Posiada pole zawieraj¹ce nick gracza oraz sumaryczn¹ liczbê zebranych przez niego punktów.
 */
public class Score  implements Serializable {
   
	/**
	 * Numer seryjny
	 */
	private static final long serialVersionUID = -7369282666711932632L;
	
	/**
     * Sumaryczna liczba punktów uzyskanych przez gracza w czasie rozgrywki.
     */
	private int points;
	
	/**
     * Nazwa gracza
     */
    private String nick;
     
    /**
     * Metoda zwracaj¹ca liczbê punktów uzyskanych przez gracza.
     * 
     * @return liczba punktów
     */
    public int getPoints() {
        return points;
    }

    /**
     * Metoda zwracaj¹ca nick.
     *
     * @return nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * Konstruktor przyjmuje string zawieraj¹cy nazwê gracza i
     * liczbê uzyskanych przez niego punktów w czasie rozgrywki.
     *
     * @param nick  nazwa gracza
     * @param score liczba punktów uzyskanych podczas rozgrywki przez gracza
     */
    public Score(String nick, int points) {
        this.points = points;
        this.nick = nick;
    }
}
