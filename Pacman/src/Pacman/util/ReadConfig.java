package Pacman.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Metoda wczytywania zawartoœci piku konfiguracyjnego.
 * <p>
 * Metoda jako argument przyjmuje nazwê pliku, a zwraca obiekt typu Properties
 * wraz z odczytanymi sta³ymi.
 *
 * @author Damian Fronia
 * @author Zuzanna Kap³on
 * @version 1.0
 */
public class ReadConfig {
	/**
	 * Metoda s³u¿¹ca do wyczytania wartoœci z pliku.properties i umieszczenia
	 * ich w obiekcie klasy Properties.
	 *
	 * @param propFilePath
	 *            œcie¿ka dostepu do .properties
	 * @return obiekt Properties zawieraj¹cy dane z pliku .properties
	 * @throws IOException
	 *             je¿eli otwarcie siê nie powiedzie
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
