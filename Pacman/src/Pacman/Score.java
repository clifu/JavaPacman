package Pacman;

import java.io.Serializable;

/**
 * Klasa zawieraj�ca informacj� o wyniku danej rozgrywki.
 * Posiada pole zawieraj�ce nick gracza oraz sumaryczn� liczb� zebranych przez niego punkt�w.
 */
public class Score  implements Serializable {
   
	/**
	 * Numer seryjny
	 */
	private static final long serialVersionUID = -7369282666711932632L;
	
	/**
     * Sumaryczna liczba punkt�w uzyskanych przez gracza w czasie rozgrywki.
     */
	private int points;
	
	/**
     * Nazwa gracza
     */
    private String nick;
     
    /**
     * Metoda zwracaj�ca liczb� punkt�w uzyskanych przez gracza.
     * 
     * @return liczba punkt�w
     */
    public int getPoints() {
        return points;
    }

    /**
     * Metoda zwracaj�ca nick.
     *
     * @return nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * Konstruktor przyjmuje string zawieraj�cy nazw� gracza i
     * liczb� uzyskanych przez niego punkt�w w czasie rozgrywki.
     *
     * @param nick  nazwa gracza
     * @param score liczba punkt�w uzyskanych podczas rozgrywki przez gracza
     */
    public Score(String nick, int points) {
        this.points = points;
        this.nick = nick;
    }
}
