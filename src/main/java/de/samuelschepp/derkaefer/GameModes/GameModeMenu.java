package de.samuelschepp.derkaefer.GameModes;

import java.awt.Toolkit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import de.samuelschepp.derkaefer.CONS;
import de.samuelschepp.derkaefer.Engine;
import de.samuelschepp.derkaefer.Settings;

public class GameModeMenu {
	private Image gras;
	public int y1;
	public int y2;
	public int y3;
	
	private int b1;
	private int b2;
	private int b3;
	private int b4;
	
	private Image startButton;
	private Image startButtonB;
	private Image beendenButton;
	private Image beendenButtonB;
	private Image highScoreButton;
	private Image highScoreButtonB;
	private Image optionButton;
	private Image optionButtonB;
	private Image fullscreen;
	private Image fullscreenB;
	private int selected;
	private Music select;
	private Image splash;
	
	private Image animation0;
	private Image animation1;
	private Image animation2;
	private Image animation3;
	private int cAnimation;
	public Settings settings = new Settings();
	AppGameContainer container;
	
	public int AppShouldQuit;

	public GameModeMenu(Settings _settings, AppGameContainer _container) throws SlickException {
		gras = new Image("res/gras.png");
		y1 = 0;
		y2 = 720;
		y3 = -720;
		container = _container;
		
		
		new java.awt.Font("Arial", java.awt.Font.PLAIN, 100);
		startButton = new Image("res/startButton.png");
		startButtonB = new Image("res/startButtonB.png");
		beendenButton = new Image("res/beendenButton.png");
		beendenButtonB = new Image("res/beendenButtonB.png");
		highScoreButton = new Image("res/highScoreButton.png");
		highScoreButtonB = new Image("res/highScoreButtonB.png");
		optionButton = new Image("res/optionButton.png");
		optionButtonB = new Image("res/optionButtonB.png");
		fullscreen = new Image("res/vollbild.png");
		fullscreenB = new Image("res/vollbildB.png");
		splash = new Image("res/splash.png");
		select = new Music("res/button.wav");
		animation0 = new Image("res/k0.png");
		animation1 = new Image("res/k1.png");
		animation2 = new Image("res/k2.png");
		animation3 = new Image("res/k3.png");
		selected = 0;
		AppShouldQuit = 0;
		cAnimation = 0;
		settings = _settings;
	}

	public Image GetKaeferImage() {
			switch (cAnimation / 4) {
			case 0:
				return animation0;
			case 1:
				return animation1;
			case 2:
				return animation2;
			case 3:
				return animation3;
			}
		return animation0;
	}
	
	public GameMode update(AppGameContainer container, int delta) throws SlickException, InterruptedException {
		b1 = (container.getHeight() / 2) - 150;
		b2 = b1 + CONS.B_MARGIN;
		b3 = b2 + CONS.B_MARGIN;
		b4 = b3 + CONS.B_MARGIN;
		int x = container.getWidth()/2-400/2;
		Input input = container.getInput();
		selected = 0;
		if((input.getMouseX() > x && input.getMouseX() < x + 400) && (input.getMouseY() > b1 && input.getMouseY() < b1 + 75)) {
			selected = 1;
			if(input.isMousePressed(0)) {
				select.play(1.0f, settings.soundVolume);
				selected = 0;
				Thread.sleep(200);
				return GameMode.Game;
			}
		}
		if((input.getMouseX() > x && input.getMouseX() < x + 400) && (input.getMouseY() > b2 && input.getMouseY() < b2 + 75)) {
			selected = 2;
			if(input.isMousePressed(0)) {
				select.play(1.0f, settings.soundVolume);
				selected = 0;
				return GameMode.HighScore;
			}
		}
		if((input.getMouseX() > x && input.getMouseX() < x + 400) && (input.getMouseY() > b3 && input.getMouseY() < b3 + 75)) {
			selected = 3;
			if(input.isMousePressed(0)) {
				select.play(1.0f, settings.soundVolume);
				selected = 0;
				return GameMode.Option;
			}
		}
		if((input.getMouseX() > x && input.getMouseX() < x + 400) && (input.getMouseY() > b4 && input.getMouseY() < b4 + 75)) {
			selected = 4;
			if(input.isMousePressed(0)) {
				select.play(1.0f, settings.soundVolume);
				AppShouldQuit = 1;
				Thread.sleep(400);
			}
		}
		if(input.isKeyPressed(Input.KEY_F11) || ((input.getMouseX() > 50 && input.getMouseX() < 50 + 200) && (input.getMouseY() > container.getHeight() - 90 && input.getMouseY() < container.getHeight() - 90 + 50))) {
			selected = 5;
			if(input.isMousePressed(0) || input.isKeyDown(Input.KEY_F11)) {
				select.play(1.0f, settings.soundVolume);
				if(container.isFullscreen()) {
					container.setDisplayMode(CONS.X, CONS.Y, false);
				}
				else {
					container.setDisplayMode(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, true);
				}
			}
		}
		if(input.isKeyPressed(Input.KEY_ENTER)) {
			select.play(1.0f, settings.soundVolume);
			return GameMode.Game;
		}
		
		cAnimation += 1;
		if(cAnimation >= 18) {
			cAnimation = 0;
		}
		
		y1+=5 / 2;
		y2+=5 / 2;
		y3+=5 / 2;
		
		if(y1 >= 1440) {
			y1 = -720;
		}
		if(y2 >= 1440) {
			y2 = -720;
		}
		if(y3 >= 1440) {
			y3 = -720;
		}
		
		return GameMode.Menu;
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		int x = container.getWidth()/2-400/2;
		g.drawImage(gras, 0, y1);
		g.drawImage(gras, 0, y2);
		g.drawImage(gras, 0, y3);
		
		g.drawImage(gras, 1280, y1);
		g.drawImage(gras, 1280, y2);
		g.drawImage(gras, 1280, y3);
		
		g.drawImage(splash, container.getWidth() / 2 - 640, 1);
		
		if(selected == 1) {
			g.drawImage(startButtonB, x, b1);
			g.drawImage(highScoreButton, x, b2);
			g.drawImage(optionButton, x, b3);
			g.drawImage(beendenButton, x, b4);
		}
		else if(selected == 2) {
			g.drawImage(startButton, x, b1);
			g.drawImage(highScoreButtonB, x, b2);
			g.drawImage(optionButton, x, b3);
			g.drawImage(beendenButton, x, b4);
		}
		else if(selected == 3) {
			g.drawImage(startButton, x, b1);
			g.drawImage(highScoreButton, x, b2);
			g.drawImage(optionButtonB, x, b3);
			g.drawImage(beendenButton, x, b4);
		}
		else if(selected == 4) {
			g.drawImage(startButton, x, b1);
			g.drawImage(highScoreButton, x, b2);
			g.drawImage(optionButton, x, b3);
			g.drawImage(beendenButtonB, x, b4);
		}
		else {
			g.drawImage(startButton, x, b1);
			g.drawImage(highScoreButton, x, b2);
			g.drawImage(optionButton, x, b3);
			g.drawImage(beendenButton, x, b4);
		}
		
		if(selected == 5) { g.drawImage(fullscreenB, 50, container.getHeight() - 90); }
		else { g.drawImage(fullscreen, 50, container.getHeight() - 90); }
		
		g.drawImage(GetKaeferImage(), container.getWidth()/4 - 100, container.getHeight() / 2 - 100, Engine.getKaeferColor(settings.chosenKaefer));
		g.drawImage(GetKaeferImage(), (int)(container.getWidth() - container.getWidth()/4), container.getHeight() / 2 - 100, Engine.getKaeferColor(settings.chosenKaefer));
	}
}
