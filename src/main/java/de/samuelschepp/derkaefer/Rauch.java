package de.samuelschepp.derkaefer;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Rauch {
	private Image rauch;
	private Image funke;
	Settings settings = new Settings();
	AppGameContainer container;
	public int rauchTimer;
	int x;
	int y;
	int xf;
	int yf;
	public boolean isBombKanone;
	int way;
	int frameLimiter;
	
	public Rauch(Settings _settings, AppGameContainer _container, boolean _bombe) throws SlickException {
		settings = _settings;
		container = _container;
		x = 0;
		y = 0;
		rauchTimer = 0;
		rauch = new Image("res/rauch.png");
		funke = new Image("res/funke.png");
		isBombKanone = _bombe;
	}
	
	public void Update(int _x, int _y) {
		updateFrameLimiter();
		if(!isBombKanone) {
			if(way == 0) {
				x += 1;
			}
			else if (way == 1) {
				x -= 1;
			}
		}
		else if(isBombKanone) {
			if(way == 0) {
				y += 1;
			}
			else if (way == 1) {
				y -= 1;
			}
		}
		if(this.rauchTimer > 0) {
			rauchTimer += 1;
			rauch.setRotation(rauch.getRotation() + 3);
			rauch.setAlpha(1f - (float)rauchTimer / 40);
			if(rauchTimer == 40) {
				rauchTimer = 0;
				rauch.setAlpha(1);
			}
		}
		xf = _x;
		yf = _y;
		if(frameLimiter == 0) funke.setRotation((float) (Math.random() * 360));
	}
	
	void updateFrameLimiter() {
		frameLimiter += 1;
		if(frameLimiter > 7) {
			frameLimiter = 0;
		}
	}
	
	public void startRauch(int _x, int _y, int _way) {
		rauchTimer = 1;
		x = _x;
		y = _y;
		way = _way;
	}
	
	public void Render(GameContainer container, Graphics g) {
		if (rauchTimer > 1) {
			if(isBombKanone) {
				g.drawImage(rauch, x-80, y+25);
			}
			else {
				g.drawImage(rauch, x, y+100);
			}
		}
		if(isBombKanone) {
			g.drawImage(funke, xf + 60, yf+55);
		}
		else {
			g.drawImage(funke, xf+30, yf+35);
		}
	}
}
