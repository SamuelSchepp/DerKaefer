package de.samuelschepp.derkaefer.GameModes;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import de.samuelschepp.derkaefer.CONS;
import de.samuelschepp.derkaefer.Settings;

public class GameModePause  {
		
	private Image background;
	private Image neuButton;
	private Image menuButton;
	private Image weiterButton;
	private Image neuButtonB;
	private Image menuButtonB;
	private Image weiterButtonB;
	private Image pauseText;
	private int selected;
	private Sound select;
	private Settings settings = new Settings();
	AppGameContainer container;
	
	public int newGame;
	public int Score;

	public GameModePause(Settings _settings, AppGameContainer _container) throws SlickException {
		settings = _settings;
		container = _container;
		background = new Image("res/SchwarzTrans.png");
		pauseText = new Image("res/pauseText.png");
		neuButton = new Image("res/neuButton.png");
		menuButton = new Image("res/menuButton.png");
		weiterButton = new Image("res/weiterButton.png");
		neuButtonB = new Image("res/neuButtonB.png");
		menuButtonB = new Image("res/menuButtonB.png");
		weiterButtonB = new Image("res/weiterButtonB.png");
		select = new Sound("res/button.wav");
		selected = 0;
		newGame = 0;
	}

	public GameMode update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		selected = 0;
		newGame = 0;
		if((input.getMouseX() > container.getWidth()/ 2 - 100 && input.getMouseX() < container.getWidth()/ 2 - 100 + 200) && (input.getMouseY() > container.getHeight()/2 - 90 && input.getMouseY() < container.getHeight()/2 - 90 + 50)) {
			selected = 1;
			if(input.isMousePressed(0)) {
				select.play(1.0f, settings.soundVolume);
				selected = 0;
				newGame = 0;
				return GameMode.Game;
			}
		}
		if((input.getMouseX() > container.getWidth()/ 2 - 100 && input.getMouseX() < container.getWidth()/ 2 - 100 + 200) && (input.getMouseY() > container.getHeight()/2 - 90 + CONS.PAUSE_B_M && input.getMouseY() < container.getHeight()/2 - 90 + CONS.PAUSE_B_M + 50)) {
			selected = 2;
			if(input.isMousePressed(0)) {
				select.play(1.0f, settings.soundVolume);
				selected = 0;
				newGame = 1;
				return GameMode.Game;
			}
		}
		if((input.getMouseX() > container.getWidth()/ 2 - 100 && input.getMouseX() < container.getWidth()/ 2 - 100 + 200) && (input.getMouseY() > container.getHeight()/2 - 90 + CONS.PAUSE_B_M*2 && input.getMouseY() < container.getHeight()/2 - 90 + CONS.PAUSE_B_M*2 + 50)) {
			selected = 3;
			if(input.isMousePressed(0)) {
				select.play(1.0f, settings.soundVolume);
				selected = 0;
				return GameMode.Menu;
			}
		}
		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			select.play(1.0f, settings.soundVolume);
			selected = 0;
			newGame = 0;
			return GameMode.Game;
		}
		
		return GameMode.Pause;
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		g.drawImage(pauseText, container.getWidth() / 2 - 320, 120);
		if(selected == 0) {
			g.drawImage(weiterButton, container.getWidth()/ 2 - 100, container.getHeight()/2 - 90);
			g.drawImage(neuButton, container.getWidth()/ 2 - 100, container.getHeight()/2 - 90 + CONS.PAUSE_B_M);
			g.drawImage(menuButton, container.getWidth()/ 2 - 100, container.getHeight()/2 - 90 + CONS.PAUSE_B_M*2);
		}
		else if(selected == 1) {
			g.drawImage(weiterButtonB, container.getWidth()/ 2 - 100, container.getHeight()/2 - 90);
			g.drawImage(neuButton, container.getWidth()/ 2 - 100, container.getHeight()/2 - 90 + CONS.PAUSE_B_M);
			g.drawImage(menuButton, container.getWidth()/ 2 - 100, container.getHeight()/2 - 90 + CONS.PAUSE_B_M*2);
		}
		else if(selected == 2) {
			g.drawImage(weiterButton, container.getWidth()/ 2 - 100, container.getHeight()/2 - 90);
			g.drawImage(neuButtonB, container.getWidth()/ 2 - 100, container.getHeight()/2 - 90 + CONS.PAUSE_B_M);
			g.drawImage(menuButton, container.getWidth()/ 2 - 100, container.getHeight()/2 - 90 + CONS.PAUSE_B_M*2);
		}
		else if(selected == 3) {
			g.drawImage(weiterButton, container.getWidth()/ 2 - 100, container.getHeight()/2 - 90);
			g.drawImage(neuButton, container.getWidth()/ 2 - 100, container.getHeight()/2 - 90 + CONS.PAUSE_B_M);
			g.drawImage(menuButtonB, container.getWidth()/ 2 - 100, container.getHeight()/2 - 90 + CONS.PAUSE_B_M*2);
		}
	}
}
