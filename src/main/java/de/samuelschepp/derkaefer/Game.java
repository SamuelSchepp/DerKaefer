package de.samuelschepp.derkaefer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import de.samuelschepp.derkaefer.GameModes.GameMode;
import de.samuelschepp.derkaefer.GameModes.GameModeGame;
import de.samuelschepp.derkaefer.GameModes.GameModeGameOver;
import de.samuelschepp.derkaefer.GameModes.GameModeHighScore;
import de.samuelschepp.derkaefer.GameModes.GameModeMenu;
import de.samuelschepp.derkaefer.GameModes.GameModeOption;
import de.samuelschepp.derkaefer.GameModes.GameModePause;

public class Game extends BasicGame {
	private GameModeGame gameModeGame;
	private GameModeGameOver gameModeGameOver;
	private GameModeMenu gameModeMenu;
	private GameMode gameMode;
	private GameMode oldGameMode;
	private GameModePause gameModePause;
	private GameModeHighScore gameModeHighScore;
	private GameModeOption gameModeOption;
	private Settings settings = new Settings();
	private ArrayList<HighScoreItem> highScores;
	private Image noFocusText;
	static AppGameContainer container;
	private boolean showDebugInfo;
	private int noFocusTextTimer;
	
	public Game() {
		super("Der Käfer " + CONS.VERSION);
		settings = new Settings();
	}
	
	public static void main(String[] args) throws SlickException {
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		System.out.println("java.library.path = " + System.getProperty("java.library.path"));
		container = new AppGameContainer(new Game());
		container.setDisplayMode(CONS.X, CONS.Y, false);
		container.setSmoothDeltas(true);
		container.setTargetFrameRate(60);
		container.setShowFPS(false);
		container.setAlwaysRender(CONS.ALWAYS_RENDER);
		container.setVSync(CONS.VSYNC);
		container.setIcon("res/icon.png");
		System.out.println("Savefile: /" + CONS.SAVE_FILE_LOCATION);
		container.start();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
        settings = new Settings();
        highScores = new ArrayList<HighScoreItem>();
        Load();
        noFocusText = new Image("res/keinFokus.png");
		gameModeGame = new GameModeGame(settings, Game.container);
		gameModeGameOver = new GameModeGameOver(settings, highScores);
		gameModeMenu = new GameModeMenu(settings, Game.container);
        gameModePause = new GameModePause(settings, Game.container);
        gameModeHighScore = new GameModeHighScore(settings, Game.container);
        gameModeOption = new GameModeOption(settings, Game.container);
		showDebugInfo = false;
        gameMode = CONS.STARTING_MODE;
        System.out.println(System.getProperty("java.library.path"));
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input in = container.getInput();
		if(in.isKeyPressed(Input.KEY_ESCAPE)) {
			if(gameMode == GameMode.Game) {
				gameMode = GameMode.Pause;
			}
		}
		if(in.isKeyPressed(Input.KEY_F3)) {
			if(this.showDebugInfo) {
				showDebugInfo = false;
				container.setShowFPS(false);
			}
			else if(!this.showDebugInfo) {
				showDebugInfo = true;
				container.setShowFPS(true);
			}
		}
		oldGameMode = gameMode;
		if(gameMode == GameMode.Game) {
			if(gameModePause.newGame == 1) {
				gameModeGame = new GameModeGame(settings, Game.container);
			}
			try {
				gameMode = gameModeGame.update(container, delta);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(!container.hasFocus()) gameMode = GameMode.Pause;
		}
		else if(gameMode == GameMode.Pause) {
			gameMode = gameModePause.update(container, delta);
		}
		else if(gameMode == GameMode.GameOver) {
			gameMode = gameModeGameOver.update(container, delta, gameModeGame.Kaefer.Perlen);
			gameModeHighScore.firstUpdate=true;
		}
		else if(gameMode == GameMode.Menu) {
			try {
				gameMode = gameModeMenu.update(this.container, delta);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(gameModeMenu.AppShouldQuit == 1) {
				container.exit();
			}
			gameModeHighScore.y1 = gameModeMenu.y1;
			gameModeHighScore.y2 = gameModeMenu.y2;
			gameModeHighScore.y3 = gameModeMenu.y3;
			gameModeOption.y1 = gameModeMenu.y1;
			gameModeOption.y2 = gameModeMenu.y2;
			gameModeOption.y3 = gameModeMenu.y3;
		}
		else if(gameMode == GameMode.HighScore) {
			gameMode = gameModeHighScore.update(container, delta, highScores);
			gameModeMenu.y1 = gameModeHighScore.y1;
			gameModeMenu.y2 = gameModeHighScore.y2;
			gameModeMenu.y3 = gameModeHighScore.y3;

		}
		else if(gameMode == GameMode.Option) {
			gameMode = gameModeOption.update(container, delta);
			gameModeMenu.y1 = gameModeOption.y1;
			gameModeMenu.y2 = gameModeOption.y2;
			gameModeMenu.y3 = gameModeOption.y3;
			this.settings = gameModeOption.settings;

		}
		if(oldGameMode != GameMode.Option && gameMode == GameMode.Option) {
			gameModeOption.setRegler(settings.musicVolume, settings.soundVolume, Game.container);
			gameModeOption.selectedKaefer = settings.chosenKaefer;
		}
		if((oldGameMode == GameMode.GameOver || oldGameMode == GameMode.Menu || gameModePause.newGame == 1)
				&& gameMode == GameMode.Game) {
			gameModeGame.SaxGuy.stop();
			gameModeGame = new GameModeGame(settings, Game.container);
			gameModeGame.SaxGuy.loop(1.0f, settings.musicVolume);
			gameModePause.newGame = 0;
		}
		if(gameMode != GameMode.Game) {
			gameModeGame.SaxGuy.setVolume(0);
		} else { gameModeGame.SaxGuy.setVolume(settings.musicVolume); }
		
		this.noFocusTextTimer += 1;
		if(this.noFocusTextTimer >= CONS.NO_FOCUS_TEXT_TIMER_MAX) this.noFocusTextTimer = 0;
		if(container.hasFocus()) this.noFocusTextTimer = 0;
		
		if((oldGameMode == GameMode.Option && this.gameMode != GameMode.Option)
				|| (oldGameMode == GameMode.GameOver && this.gameMode != GameMode.GameOver)) {
			Save();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		if(gameMode == GameMode.Game || gameMode == GameMode.GameOver || gameMode == GameMode.Pause) {
			gameModeGame.render(container, g);
		}
		if(gameMode == GameMode.GameOver) {
			gameModeGameOver.render(container, g);
		}
		if(gameMode == GameMode.Pause) {
			gameModePause.render(container, g);
		}
		if(gameMode == GameMode.Menu) {
			gameModeMenu.render(container, g);
		}
		if(gameMode == GameMode.HighScore) {
			gameModeHighScore.render(container, g);
		}
		if(gameMode == GameMode.Option) {
			gameModeOption.render(container, g);
		}
		if(!container.hasFocus() && noFocusTextTimer >= 1 && noFocusTextTimer <= CONS.NO_FOCUS_TEXT_TIMER_MAX/2) {
			g.drawImage(noFocusText, 0, 0);
		}
		if(this.showDebugInfo) g.drawString("Der K�fer " + CONS.VERSION + " von Samuel Schepp | " + CONS.DATE, 5, container.getHeight()-20);
	}
	
	private void Save() {                                         
        try {
            FileOutputStream file = new FileOutputStream(CONS.SAVE_FILE_LOCATION);
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(this.settings);
            stream.close();
        } catch (IOException e) { System.out.println(e.toString());} 
    }  
	
	private void Load() throws SlickException {                                  
        try {
            FileInputStream file = new FileInputStream(CONS.SAVE_FILE_LOCATION);
            ObjectInputStream stream = new ObjectInputStream(file);
            this.settings = (Settings) stream.readObject();
            stream.close();
        } catch (Exception ex) { settings = new Settings(); }
        if(!CONS.USE_FULL_RES) {
	        container.setDisplayMode(CONS.X, CONS.Y, false);
        }
        else {
	        container.setDisplayMode(container.getWidth(),  container.getHeight(), true);
        }
	}
}
