package de.samuelschepp.derkaefer.GameModes;

import java.util.ArrayList;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;

import de.samuelschepp.derkaefer.HighScoreItem;
import de.samuelschepp.derkaefer.Settings;

public class GameModeGameOver  {
		
	private Image background;
	private Image neuButton;
	private Image menuButton;
	private Image neuButtonB;
	private Image menuButtonB;
	private Font font;
	private Image gameOverText;
	private int selected;
	private Sound select;
	private Settings settings = new Settings();;
	private ArrayList<HighScoreItem> highScores;
	
	public int Score = -1;

	public GameModeGameOver(Settings _settings, ArrayList<HighScoreItem> _highScores) throws SlickException {
		highScores = new ArrayList<HighScoreItem>();
		settings = _settings;
		highScores = _highScores;
		background = new Image("res/SchwarzTrans.png");
		font = new TrueTypeFont(new java.awt.Font("Comic Sans MS", java.awt.Font.PLAIN, 60), true);
		gameOverText = new Image("res/gameOverText.png");
		neuButton = new Image("res/neuButton.png");
		menuButton = new Image("res/menuButton.png");
		neuButtonB = new Image("res/neuButtonB.png");
		menuButtonB = new Image("res/menuButtonB.png");
		select = new Sound("res/button.wav");
		selected = 0;
	}

	public GameMode update(GameContainer container, int delta, int _score) throws SlickException {
		Score = _score;
		HighScoreItem hsi = new HighScoreItem("", Score);
		highScores.add(hsi);
		Input input = container.getInput();
		selected = 0;
		if((input.getMouseX() > container.getWidth() / 2 - 100 && input.getMouseX() < container.getWidth() / 2 - 100 + 200) && (input.getMouseY() > container.getHeight() / 2 + 40 && input.getMouseY() < container.getHeight() / 2 + 40 + 50)) {
			selected = 1;
			if(input.isMousePressed(0)) {
				select.play(1.0f, settings.soundVolume);
				selected = 0;
				return GameMode.Game;
			}
		}
		if((input.getMouseX() > container.getWidth() / 2 - 100 && input.getMouseX() < container.getWidth() / 2 - 100 + 200) && (input.getMouseY() > container.getHeight() / 2 + 110 && input.getMouseY() < container.getHeight() / 2 + 110 + 50)) {
			selected = 2;
			if(input.isMousePressed(0)) {
				select.play(1.0f, settings.soundVolume);
				selected = 0;
				return GameMode.Menu;
			}
		}
		if(input.isKeyPressed(Input.KEY_ENTER)) {
			select.play(1.0f, settings.soundVolume);
			selected = 0;
			return GameMode.Game;
		}
		
		return GameMode.GameOver;
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		if(Score != -1) {
			g.drawImage(background, 0, 0);
			g.drawImage(gameOverText, container.getWidth() / 2 - 210, container.getHeight() / 2 - 190);
			font.drawString(container.getWidth() / 2 +143, container.getHeight() / 2 - 144, "" + Score);
			if(selected == 0) {
				g.drawImage(neuButton, container.getWidth() / 2 - 100, container.getHeight() / 2 + 40);
				g.drawImage(menuButton, container.getWidth() / 2 - 100, container.getHeight() / 2 + 110);
			}
			else if(selected == 1) {
				g.drawImage(neuButtonB, container.getWidth() / 2 - 100, container.getHeight() / 2 + 40);
				g.drawImage(menuButton, container.getWidth() / 2 - 100, container.getHeight() / 2 + 110);
			}
			else if(selected == 2) {
				g.drawImage(neuButton, container.getWidth() / 2 - 100, container.getHeight() / 2 + 40);
				g.drawImage(menuButtonB, container.getWidth() / 2 - 100, container.getHeight() / 2 + 110);
			}
		}
	}
}
