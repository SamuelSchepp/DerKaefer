package de.samuelschepp.derkaefer;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bombe {
	
	private Image bombe;
	private Image eis;
	
	public int X, Y;
	public int Speed;
	public int FrozenTimer;
	public int LateFrozenTimer;
	Settings settings = new Settings();
	AppGameContainer container;
	public boolean reShot;
	
	public Bombe(Settings _settings, AppGameContainer _container) throws SlickException {
		bombe = new Image("res/bombe.png");
		eis = new Image("res/eis.png");
		Speed = CONS.BOMBE_SPEED;
		X = -200;
		FrozenTimer = 0;
		LateFrozenTimer = 0;
		settings = _settings;
		container = _container;
		reShot = false;
	}
	
	public void Update(int kanoneY, int kanoneSpeed, int eingesammelt, int perlen) {
		reShot = false;
		if(eingesammelt == 1) {
			X = -60;
			LateFrozenTimer = 1;
		}
		if(FrozenTimer == 0) {
			bombe.rotate(CONS.BOMBE_ROTATE_SPEED);
		}
		if(FrozenTimer == 0 && LateFrozenTimer == 0) {
			X -= ((CONS.BOMBE_SPEED * (perlen / 4)) + 6);
		}
		
		if(LateFrozenTimer > 0) {
			LateFrozenTimer +=1;
			if(LateFrozenTimer > CONS.LATE_FROZENTIMER_MAX) {
				LateFrozenTimer = 0;
			}
		}

		if(X < CONS.BOMBE_MAXLEFT) {
			X = container.getWidth() - 160;
			Y = kanoneY + 40;
			reShot = true;
		}
	}
	
	public void Render(GameContainer container, Graphics g) {
		if(FrozenTimer > 1) {
			g.drawImage(eis, X - 6, Y);
		}
		g.drawImage(bombe, X, Y);
	}
}
