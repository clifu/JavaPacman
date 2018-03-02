package Pacman;

/**
 * Klasa zawiraj�ca sta�e potrzebne do pracy protoko�u sieciowego.
 * Poszczeg�lne pola to identyfikatory polece� sieciowych wysy�anych
 * przez klienta albo serwer. Identyfikatory musz� by� jednoznaczne
 * mi�dzy klientem a serwerem.
 *
 */
public class Protocol {

    public static final String GETCONFIG = "GET_CONFIG";
    public static final String GETHIGHSCORES = "GET_HIGHSCORES";
    public static final String GETLEVELS = "GET_LEVELS";
    public static final String GETLEVEL = "GET_LEVEL";
    public static final String GAMESCORE = "GAME_SCORE";
    public static final String PING = "PING";
    public static final String CONFIG = "CONFIG";
    public static final String HIGHSCORES = "HIGHSCORES";
    public static final String LEVELS = "LEVELS";
    public static final String LEVEL = "LEVEL";
    public static final String GAMESCOREACCEPTED = "GAME_SCORE_ACCEPTED";
    public static final String GAMESCOREREJECTED = "GAME_SCORE_REJECTED";
    public static final String ERROR = "ERROR";
}
