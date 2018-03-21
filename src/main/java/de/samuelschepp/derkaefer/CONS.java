package de.samuelschepp.derkaefer;

import de.samuelschepp.derkaefer.GameModes.GameMode;

public interface CONS {
	public boolean SHOW_FPS = true;
	public boolean ALWAYS_RENDER = true;
	public boolean VSYNC = true;
	public boolean USE_FULL_RES = false;
	public static int X = 1280;
	public static int Y = 720;

	// Main
	public GameMode STARTING_MODE = GameMode.Menu;
	public String VERSION = "1.2 gradle";
	public String CHANGELOG = "";
	public String DATE = "22.07.12";
	public String SAVE_FILE_LOCATION = "Der Kaefer - Einstellungen.ini";
	public String HIGHSCORE_FILE_LOCATION = "Der Kaefer - Highscores.ini";
	
	// MainMenu
	public int B_MARGIN = 95;
	
	// HUD
	public int NO_FOCUS_TEXT_TIMER_MAX = 80;
	
	// Bombe
	public int BOMBE_SPEED = 1;
	public int BOMBE_ROTATE_SPEED = 3;
	public int BOMBE_FROZENTIMER_MAX = 600;
	public int LATE_FROZENTIMER_MAX = 200;
	public int BOMBE_MAXLEFT = -500;
	
	// Perle
	public boolean USE_FINAL_PERLENTYP = true;
	public PerlenTyp FINAL_PERLENTYP = PerlenTyp.Nitro;
	public int PERLEN_SPEED = 5;
	public int PERLEN_ROTATE_SPEED = 3;
	
	
	// K�fer
	public int KAEFER_COLLISSION_X = 20; // Kollisionsviereck Relativ zur K�ferposition
	public int KAEFER_COLLISSION_Y = 20;	// #
	public boolean IS_FROZENCOLLSION_WORKING = false;
	public int KAEFER_SPEED = 5;
	public int KAEFER_NITROTIMER_MAX = 600;
	
	// Kanone
	public int KANONE_SPEED = 5;
	
	// Pause
	public int PAUSE_B_M = 70;
}
