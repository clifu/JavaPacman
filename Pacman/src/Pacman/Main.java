package Pacman;

import Pacman.game.MainWindow;
import Pacman.util.ReadConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * Klasa Main - g³ówna klasa gry
 * 
 * @author Damian Fronia
 * @author Zuzanna Kap³on
 * @version 1.0
 */
public class Main {

	 /**
     * Flaga okreœlaj¹ca czy gra odbywa siê w trybie online czy offline
     */
    public static boolean online;

	/**
	 * Wczytanie pliku konfiguracyjnego config.properties Utworzenie okna
	 * g³ównego - MainWindow
	 */
	public static void main(String[] args) {

		 String configPath, gameConfigPath;

	        if (args.length < 2) {
	            configPath = "config.properties";
	            gameConfigPath = "gameconfig.properties";
	        } else {
	            configPath = args[0];
	            gameConfigPath = args[1];
	        }

	        ReadConfig readConfig = new ReadConfig();
	        final Properties config;

	        try {
	            config = readConfig.getProperties(gameConfigPath);
	            GameConfig.readConstants(config);

	            if (Client.canConnect()) {
	                online = true;
	            }

	            if(online){
	                Client.getConfig();
	                Client.getLevels();
	            }
	            else{
	                Properties prop = readConfig.getProperties(configPath);
	                Config.readConstants(prop);
	            }

	            MainWindow mainWindow = new MainWindow();
	            mainWindow.showWindow();
	        } catch (IOException e) {
	            System.out.println("Exception when loading properties: " + e);
	        }
	    }
	}