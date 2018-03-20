package de.samuelschepp.derkaefer.GameModes;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import de.samuelschepp.derkaefer.CONS;
import de.samuelschepp.derkaefer.Engine;
import de.samuelschepp.derkaefer.HighScoreItem;
import de.samuelschepp.derkaefer.Settings;

public class GameModeHighScore {
	private Image gras;
	public int y1;
	public int y2;
	public int y3;
	
	private Image highScoreText;
	private Image backButton;
	private Image backButtonB;
	private int selected;
	private Sound select;
	private Image animation0;
	private Image animation1;
	private Image animation2;
	private Image animation3;
	private int cAnimation;
	private Settings settings = new Settings();;
	public boolean firstUpdate;
	private ArrayList<String> hs;
	private int[] highscores;
	AppGameContainer container;
	private Font font;

	public GameModeHighScore(Settings _settings, AppGameContainer _container) throws SlickException {
		settings = _settings;
		container = _container;
		gras = new Image("res/gras.png");
		y1 = 0;
		y2 = -720;
		y3 = 720;
		font = new TrueTypeFont(new java.awt.Font("Comic Sans MS", java.awt.Font.PLAIN, 40), true);
		backButton = new Image("res/backButton.png");
		backButtonB = new Image("res/backButtonB.png");
		highScoreText = new Image("res/highScoreText.png");
		select = new Sound("res/button.wav");
		animation0 = new Image("res/k0.png");
		animation1 = new Image("res/k1.png");
		animation2 = new Image("res/k2.png");
		animation3 = new Image("res/k3.png");
		selected = 0;
		cAnimation = 0;
		firstUpdate = true;
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
	
	public GameMode update(GameContainer container, int delta, ArrayList<HighScoreItem> _highScores)  {
		Input input = container.getInput();
		selected = 0;
		if((input.getMouseX() > container.getWidth() - 250 && input.getMouseX() < container.getWidth() - 250 + 200) && (input.getMouseY() > container.getHeight() - 90 && input.getMouseY() < container.getHeight() - 90 + 50)) {
			selected = 1;
			if(input.isMousePressed(0)) {
				select.play(1.0f, settings.soundVolume);
				selected = 0;
				return GameMode.Menu;
			}
		}

		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			select.play(1.0f, settings.soundVolume);
			selected = 0;
			return GameMode.Menu;
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
		
		if(firstUpdate) {
			this.readHighScores();
			firstUpdate = false;
		}
		return GameMode.HighScore;
	}

	private void readHighScores() {
		File f = new File(CONS.HIGHSCORE_FILE_LOCATION);
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		hs = new ArrayList<String>();
		FileInputStream fstream;
		DataInputStream in;
		try {
			fstream = new FileInputStream(CONS.HIGHSCORE_FILE_LOCATION);
			in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				hs.add(strLine);
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		try {
			highscores = new int[hs.size()];
			for(int i = 0; i < hs.size(); i++) {
				highscores[i] = Integer.parseInt(hs.get(i));
			}
			Arrays.sort(highscores);
			
		} catch (Exception ex) {
			File f2 = new File(CONS.HIGHSCORE_FILE_LOCATION);
			f2.delete();
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(gras, 0, y1);
		g.drawImage(gras, 0, y2);
		g.drawImage(gras, 0, y3);
		
		g.drawImage(gras, 1280, y1);
		g.drawImage(gras, 1280, y2);
		g.drawImage(gras, 1280, y3);
		
		g.drawImage(highScoreText, container.getWidth() / 2 - 640, 1);
		if(selected == 0) {
			g.drawImage(backButton, container.getWidth() - 250, container.getHeight() - 90);
		}
		else if(selected == 1) {
			g.drawImage(backButtonB, container.getWidth() - 250, container.getHeight() - 90);
		}
		try {
			for(int i = 0; i < 10; i++) {
				String number = 10+"";
				if(i<9)number = "0"+(i+1);
				try {
					font.drawString(container.getWidth()/2 - 100,i * 40+ (container.getHeight() / 2) - 150, number+":");
					font.drawString(container.getWidth()/2 + 20,i * 40+ (container.getHeight() / 2) - 150, highscores[highscores.length - 1 - i]+"");
					
				}
				catch (Exception ex) {
					font.drawString(container.getWidth()/2 - 100,i * 40+ (container.getHeight() / 2) - 150, number);
					font.drawString(container.getWidth()/2 + 20,i * 40+ (container.getHeight() / 2) - 150, "0");				} 
			}
		} catch (Exception ex) { System.out.println(ex.toString()); }
		g.drawImage(GetKaeferImage(), container.getWidth()/4 - 100, container.getHeight() / 2 - 100, Engine.getKaeferColor(settings.chosenKaefer));
		g.drawImage(GetKaeferImage(), (int)(container.getWidth() - container.getWidth()/4), container.getHeight() / 2 - 100, Engine.getKaeferColor(settings.chosenKaefer));
	}
}
