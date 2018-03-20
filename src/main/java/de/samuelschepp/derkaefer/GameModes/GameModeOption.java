package de.samuelschepp.derkaefer.GameModes;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import de.samuelschepp.derkaefer.Engine;
import de.samuelschepp.derkaefer.Settings;

public class GameModeOption {
	private Image gras;
	public int y1;
	public int y2;
	public int y3;
	
	private Image saveButton;
	private Image saveButtonB;
	private Image backButton;
	private Image backButtonB;
	private Image optionText;
	private int selected;
	private Sound select;
	private Image animation0;
	private Image animation1;
	private Image animation2;
	private Image animation3;
	
	private Image musicProgressBar;
	private Image progressBarRegler;
	private float musicReglerValue;
	private boolean musicReglerSelected = false;
	
	private Image frame;
	private Image arrowR;
	private Image arrowL;
	
	private Image soundProgressBar;
	private float soundReglerValue;
	private boolean soundReglerSelected = false;
	public int selectedKaefer;
	
	private int cAnimation;
	public Settings settings = new Settings();;
	private boolean oldMouseStatusClicked = true;
	private boolean mouseStatusClicked;
	private boolean playSound = false;
	AppGameContainer container;

	public GameModeOption(Settings _setts, AppGameContainer _container) throws SlickException {
		container = _container; 
		gras = new Image("res/gras.png");
		settings = _setts;
		musicReglerValue = calcReglerValueFromFloat(settings.musicVolume, this.container);
		soundReglerValue = calcReglerValueFromFloat(settings.soundVolume, this.container);
		saveButton = new Image("res/speichernButton.png");
		saveButtonB = new Image("res/speichernButtonB.png");
		backButton = new Image("res/backButton.png");
		backButtonB = new Image("res/backButtonB.png");
		optionText = new Image("res/optionText.png");
		musicProgressBar = new Image("res/musicProgressBar.png");
		soundProgressBar = new Image("res/soundProgressBar.png");
		progressBarRegler = new Image("res/progressBarRegler.png");
		select = new Sound("res/button.wav");
		animation0 = new Image("res/k0.png");
		animation1 = new Image("res/k1.png");
		animation2 = new Image("res/k2.png");
		animation3 = new Image("res/k3.png");
		frame = new Image("res/rahmen.png");
		arrowL = new Image("res/pfeilLinks.png");
		arrowR = new Image("res/pfeilRechts.png");
		y1 = 0;
		y2 = -720;
		y3 = 720;
		selected = 0;
		selectedKaefer = 0;
		cAnimation = 0;
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
	
	public GameMode update(GameContainer container, int delta)  {
		Input input = container.getInput();
		mouseStatusClicked = input.isMouseButtonDown(0);
		selected = 0;
		playSound = false;
		
		if(input.getMouseX() > container.getWidth()/2 - 195 && input.getMouseX() < container.getWidth()/2 +195) {
			if(input.getMouseY() > container.getHeight() / 2 - 100 && input.getMouseY() < container.getHeight() / 2 - 100 + 50) {
				if(mouseStatusClicked && !soundReglerSelected && !oldMouseStatusClicked) {
					musicReglerSelected = true;
					playSound = true;
				}
			}
		}
		if(!mouseStatusClicked) {
			musicReglerSelected = false;
		}
		if(musicReglerSelected) {
				musicReglerValue = input.getMouseX() - 5;
				if(musicReglerValue < container.getWidth() / 2 - 200) musicReglerValue = container.getWidth() / 2 - 200;
				if(musicReglerValue > container.getWidth() / 2 +190) musicReglerValue = container.getWidth() / 2 +190;
		}
		
		if(input.getMouseX() > container.getWidth()/2 - 195 && input.getMouseX() < container.getWidth()/2 +195) {
			if(input.getMouseY() > container.getHeight() / 2 + 10 && input.getMouseY() < container.getHeight() / 2 + 10 + 50) {
				if(mouseStatusClicked && !musicReglerSelected && !oldMouseStatusClicked) {
					soundReglerSelected = true;
					playSound = true;
				}
			}
		}
		if(!mouseStatusClicked) {
			soundReglerSelected = false;
		}
		if(soundReglerSelected) {
				soundReglerValue = input.getMouseX() - 5;
				if(soundReglerValue < container.getWidth() / 2 - 200) soundReglerValue = container.getWidth() / 2 - 200;
				if(soundReglerValue > container.getWidth() / 2 +190) soundReglerValue = container.getWidth() / 2 +190;
		}
		if((input.getMouseX() > container.getWidth() - 500 && input.getMouseX() < container.getWidth() - 500 + 200) && (input.getMouseY() > container.getHeight() - 90 && input.getMouseY() < container.getHeight() - 90 + 50) && !musicReglerSelected && !soundReglerSelected) {
			selected = 1;
			if(input.isMousePressed(0)) {
				settings.musicVolume = calcFloatFromReglerValue(musicReglerValue);
				settings.soundVolume = calcFloatFromReglerValue(soundReglerValue);
				settings.chosenKaefer = selectedKaefer;
				select.stop();
				select.play(1.0f, settings.soundVolume);
				selected = 0;
				oldMouseStatusClicked = true;
				return GameMode.Menu;
			}
		}
		if((input.getMouseX() > container.getWidth() - 250 && input.getMouseX() < container.getWidth() - 250 + 200) && (input.getMouseY() > container.getHeight() - 90 && input.getMouseY() < container.getHeight() - 90 + 50) && !musicReglerSelected && !soundReglerSelected) {
			selected = 2;
			if(input.isMousePressed(0)) {
				select.stop();
				select.play(1.0f, settings.soundVolume);
				selected = 0;
				oldMouseStatusClicked = true;
				return GameMode.Menu;
			}
		}
		if((input.getMouseX() > container.getWidth() / 2 - 100/2 - 100 && input.getMouseX() < container.getWidth() / 2 - 100/2) && (input.getMouseY() > container.getHeight() / 2 + 100 && input.getMouseY() < container.getHeight() / 2 + 100 + 100) && !musicReglerSelected && !soundReglerSelected) {
			selected = 3;
			if(input.isMousePressed(0)) {
				select.stop();
				playSound = true;
				//selected = 0;
				oldMouseStatusClicked = true;
				selectedKaefer -= 1;
				if(selectedKaefer == -1) {
					selectedKaefer = 7;
				}
				System.out.println(selectedKaefer);
			}
		}
		if((input.getMouseX() > container.getWidth() / 2 - 100/2 + 100 && input.getMouseX() < container.getWidth() / 2 - 100/2 + 200) && (input.getMouseY() > container.getHeight() / 2 + 100 && input.getMouseY() < container.getHeight() / 2 + 100 + 100) && !musicReglerSelected && !soundReglerSelected) {
			selected = 4;
			if(input.isMousePressed(0)) {
				select.stop();
				playSound = true;
				//selected = 0;
				oldMouseStatusClicked = true;
				selectedKaefer += 1;
				if(selectedKaefer == 8) {
					selectedKaefer = 0;
				}
				System.out.println(selectedKaefer);
			}
		}

		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			select.stop();
			select.play(1.0f, settings.soundVolume);
			selected = 0;
			oldMouseStatusClicked = true;
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
		
		if(playSound) select.play(1.0f, calcFloatFromReglerValue(soundReglerValue));
		oldMouseStatusClicked = mouseStatusClicked;
		return GameMode.Option;
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawImage(gras, 0, y1);
		g.drawImage(gras, 0, y2);
		g.drawImage(gras, 0, y3);
		
		g.drawImage(gras, 1280, y1);
		g.drawImage(gras, 1280, y2);
		g.drawImage(gras, 1280, y3);
		
		g.drawImage(optionText, container.getWidth() / 2 - 640, 1);
		
		g.drawImage(musicProgressBar, container.getWidth() / 2 - 200, container.getHeight() / 2 - 100 - 50);
		g.drawImage(progressBarRegler, musicReglerValue, container.getHeight() / 2 - 100);
		g.drawImage(soundProgressBar, container.getWidth() / 2 - 200, container.getHeight() / 2 + 10 - 50);
		g.drawImage(progressBarRegler, soundReglerValue, container.getHeight() / 2 + 10);
		
		g.drawImage(frame, container.getWidth() / 2 - 100/2, container.getHeight() / 2 + 100);
		g.drawImage(GetKaeferImage(), container.getWidth() / 2 - 100/2, container.getHeight() / 2 + 100+1, Engine.getKaeferColor(selectedKaefer));
		if(selected == 3) g.drawImage(arrowL, container.getWidth() / 2 - 100/2 - 100, container.getHeight() / 2 + 100, Color.black); else g.drawImage(arrowL, container.getWidth() / 2 - 100/2 - 100, container.getHeight() / 2 + 100);
		if(selected == 4) g.drawImage(arrowR, container.getWidth() / 2 - 100/2 + 100, container.getHeight() / 2 + 100, Color.black); else g.drawImage(arrowR, container.getWidth() / 2 - 100/2 + 100, container.getHeight() / 2 + 100);
		
		if(selected != 1 && selected != 2) {
			g.drawImage(saveButton, container.getWidth() - 250 - 250, container.getHeight() - 90);
			g.drawImage(backButton, container.getWidth() - 250, container.getHeight() - 90);
		}
		else if(selected == 1) {
			g.drawImage(saveButtonB, container.getWidth() - 250 - 250, container.getHeight() - 90);
			g.drawImage(backButton, container.getWidth() - 250, container.getHeight() - 90);
		}
		else if(selected == 2) {
			g.drawImage(saveButton, container.getWidth() - 250 - 250, container.getHeight() - 90);
			g.drawImage(backButtonB, container.getWidth() - 250, container.getHeight() - 90);
		}
		
		g.drawImage(GetKaeferImage(), container.getWidth()/4 - 100, container.getHeight() / 2 - 100, Engine.getKaeferColor(settings.chosenKaefer));
		g.drawImage(GetKaeferImage(), (int)(container.getWidth() - container.getWidth()/4), container.getHeight() / 2 - 100, Engine.getKaeferColor(settings.chosenKaefer));
	}
	
	public void setRegler(float _music, float _sound, AppGameContainer _container) {
		musicReglerValue = calcReglerValueFromFloat(_music, _container);
		soundReglerValue = calcReglerValueFromFloat(_sound, _container);
	}
	
	float calcFloatFromReglerValue(float _regler) {
		float i = (_regler - (container.getWidth() / 2 - 200)) / 390f;
		if (i < 0f) return 0.0f;
		if (i > 1f) return 1.0f;
		return i;
	}
	int calcReglerValueFromFloat(float f, AppGameContainer _container) {
		container = _container;
		int i =  (int) ( ((f * 390f) + (container.getWidth() / 2 - 200)));  // ((f * 387f) + container.getWidth() / 2 - 200);
		return i;
	}
}
