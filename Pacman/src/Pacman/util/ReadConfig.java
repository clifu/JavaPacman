package Pacman.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Metoda wczytywania zawarto�ci piku konfiguracyjnego.
 * <p>
 * Metoda jako argument przyjmuje nazw� pliku, a zwraca obiekt typu Properties
 * wraz z odczytanymi sta�ymi.
 *
 * @author Damian Fronia
 * @author Zuzanna Kap�on
 * @version 1.0
 */
public class ReadConfig {
	/**
	 * Metoda s�u��ca do wyczytania warto�ci z pliku.properties i umieszczenia
	 * ich w obiekcie klasy Properties.
	 *
	 * @param propFilePath
	 *            �cie�ka dostepu do .properties
	 * @return obiekt Properties zawieraj�cy dane z pliku .properties
	 * @throws IOException
	 *             je�eli otwarcie si� nie powiedzie
	 */
	public final Properties getProperties(String propFilePath) throws IOException {

		Properties prop = new Properties();
		try (InputStream inputStream = new FileInputStream(propFilePath)) {
			prop.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
}
