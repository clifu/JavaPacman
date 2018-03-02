package Pacman;

/**
 * Klasa zawiraj¹ca sta³e potrzebne do pracy protoko³u sieciowego.
 * Poszczególne pola to identyfikatory poleceñ sieciowych wysy³anych
 * przez klienta albo serwer. Identyfikatory musz¹ byæ jednoznaczne
 * miêdzy klientem a serwerem.
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
