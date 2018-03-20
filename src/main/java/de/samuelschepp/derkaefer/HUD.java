package de.samuelschepp.derkaefer;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class HUD {
	private int leben;
	private int perlen;
	private Image herz;
	private Image perle;
	private Image frozen;
	private Image nitro;
	private Font font;
	private int blinkTimer;
	Settings settings = new Settings();
	AppGameContainer container;
	public int blodTimer;
	private Image blod;
	
	public HUD(Settings _settings, AppGameContainer _container) throws SlickException {
		herz = new Image("res/herz.png");
		perle = new Image("res/perle.png");
		frozen = new Image("res/frozen.png");
		nitro = new Image("res/nitro.png");
		blod = new Image("res/blod.png");
		font = new TrueTypeFont(new java.awt.Font("Comic Sans MS", java.awt.Font.PLAIN, 28), true);
		blinkTimer = 0;
		settings = _settings;
		container = _container;
	}
	
	public void Update(int _leben, int _perlen, int _blodTimer) {
		leben = _leben;
		perlen = _perlen;
		blinkTimer += 1;
		if(blinkTimer >= 19) {
			blinkTimer = 0;
		}
		if(_blodTimer == 1) {
			blodTimer = 1;
		}
		if(this.blodTimer > 0) {
			blodTimer += 1;
			if(blodTimer == 200) { blodTimer = 0; }
		}
	}
	
	public void Render(GameContainer container, Graphics g, int frozenTimer, int nitroTimer) {
		if(frozenTimer > 0 && nitroTimer == 0) {
			g.drawImage(frozen, 55, container.getHeight() - 165);
			
			font.drawString(93, container.getHeight() - 170, ((CONS.BOMBE_FROZENTIMER_MAX-frozenTimer) / (CONS.BOMBE_FROZENTIMER_MAX / 10) + 1)+"");
		}
		else if(frozenTimer > 0 && nitroTimer > 0) {
			g.drawImage(frozen, 55, container.getHeight() - 165);
			font.drawString(93, container.getHeight() - 170, ((CONS.BOMBE_FROZENTIMER_MAX-frozenTimer) / (CONS.BOMBE_FROZENTIMER_MAX / 10) + 1)+"");
			g.drawImage(nitro, 55, container.getHeight() - 200);
			font.drawString(93, container.getHeight() - 205, ((CONS.KAEFER_NITROTIMER_MAX-nitroTimer) / (CONS.KAEFER_NITROTIMER_MAX / 10) + 1)+"");
		}
		else if(frozenTimer == 0 && nitroTimer > 0) {
			g.drawImage(nitro, 55, container.getHeight() - 165);
			font.drawString(93, container.getHeight() - 165, ((CONS.KAEFER_NITROTIMER_MAX-nitroTimer) / (CONS.KAEFER_NITROTIMER_MAX / 10) + 1)+"");
		}
		
		g.drawImage(perle, 55, container.getHeight() - 130);
		font.drawString(93, container.getHeight() - 135, ""+perlen);
		if(!(leben == 1 && blinkTimer >= 1 && blinkTimer <=10)) {
			for (int i = 0; i< leben; i++) {
				g.drawImage(herz, 38*i+50, container.getHeight() - 90);
			}
		}
		
		if(blodTimer > 0) {
			blod.setAlpha(0.7f - (float)blodTimer / 200f);
			blod.setRotation(0);
			g.drawImage(blod, 0,0);
			blod.setRotation(90);
			g.drawImage(blod, container.getWidth() - 2560/2 - 100,1280-200);
			blod.setRotation(180);
			g.drawImage(blod, 0,container.getHeight() - 200);
			blod.setRotation(-90);
			g.drawImage(blod, - 1280 + 100, 1280-200);
		}
	}
}
