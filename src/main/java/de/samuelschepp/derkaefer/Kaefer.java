package de.samuelschepp.derkaefer;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Kaefer {
	
	private Image animation0;
	private Image animation1;
	private Image animation2;
	private Image animation3;
	private Sound bumm;
	private Sound bing;
	Settings settings = new Settings();
	
	public int NitroTimer;
	public int X;
	public int Y;
	public int Height;
	public int width;
	public int cAnimation;
	public int Speed;
	public int Direction;
	public int Leben;
	public int Perlen;
	AppGameContainer container;
	
	public Kaefer(Settings _settings, AppGameContainer _container) throws SlickException {
		container = _container;
		settings = _settings;
		animation0 = new Image("res/k0.png");
		animation1 = new Image("res/k1.png");
		animation2 = new Image("res/k2.png");
		animation3 = new Image("res/k3.png");
		bumm = new Sound("res/bumm.wav");
		bing = new Sound("res/bing.wav");
		width = Height = 100;
		X = (container.getWidth() / 2) - (width / 2);
		Y = (container.getHeight() / 2) - (Height / 2);
		cAnimation = 0;
		Speed = CONS.KAEFER_SPEED;
		Leben = 3;
		Perlen = 0;
		NitroTimer = 0;
		container = _container;
	}
	
	public Image GetImage() {
		animation0.setRotation(Direction*45);
		animation1.setRotation(Direction*45);
		animation2.setRotation(Direction*45);
		animation3.setRotation(Direction*45);
		
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
	
	public void Update(Input input, int bombeX, int bombeY, int frozenTimer) {
		if(NitroTimer > 0) {
			Speed = CONS.KAEFER_SPEED*2;
		}
		else if(NitroTimer == 0) {
			Speed = CONS.KAEFER_SPEED;
		}
		int oldX;
		int oldY;
		if ((input.isKeyDown(Input.KEY_UP)||input.isKeyDown(Input.KEY_W)) && !(input.isKeyDown(Input.KEY_DOWN)||input.isKeyDown(Input.KEY_S)) && !(input.isKeyDown(Input.KEY_LEFT)||input.isKeyDown(Input.KEY_A)) && !(input.isKeyDown(Input.KEY_RIGHT)||input.isKeyDown(Input.KEY_D))) {
			oldY = Y;
			Y -= Speed;
			if(checkFrozenHit(bombeX, bombeY, frozenTimer) == true) {
				Y = oldY;
			}
			Direction = 0;
		}
		else if (!(input.isKeyDown(Input.KEY_UP)||input.isKeyDown(Input.KEY_W)) && (input.isKeyDown(Input.KEY_DOWN)||input.isKeyDown(Input.KEY_S)) && !(input.isKeyDown(Input.KEY_LEFT)||input.isKeyDown(Input.KEY_A)) && !(input.isKeyDown(Input.KEY_RIGHT)||input.isKeyDown(Input.KEY_D))) {
			oldY = Y;
			Y += Speed;
			if(checkFrozenHit(bombeX, bombeY, frozenTimer) == true) {
				Y = oldY;
			}
			Direction = 4;
		}
		else if (!(input.isKeyDown(Input.KEY_UP)||input.isKeyDown(Input.KEY_W)) && !(input.isKeyDown(Input.KEY_DOWN)||input.isKeyDown(Input.KEY_S)) && (input.isKeyDown(Input.KEY_LEFT)||input.isKeyDown(Input.KEY_A)) && !(input.isKeyDown(Input.KEY_RIGHT)||input.isKeyDown(Input.KEY_D))) {
			oldX = X;
			X -= Speed;
			if(checkFrozenHit(bombeX, bombeY, frozenTimer) == true) {
				X = oldX;
			}
			Direction = 6;
		}
		else if (!(input.isKeyDown(Input.KEY_UP)||input.isKeyDown(Input.KEY_W)) && !(input.isKeyDown(Input.KEY_DOWN)||input.isKeyDown(Input.KEY_S)) && !(input.isKeyDown(Input.KEY_LEFT)||input.isKeyDown(Input.KEY_A)) && (input.isKeyDown(Input.KEY_RIGHT)||input.isKeyDown(Input.KEY_D))) {
			oldX = X;
			X += Speed;
			if(checkFrozenHit(bombeX, bombeY, frozenTimer) == true) {
				X = oldX;
			}
			Direction = 2;
		}
		else if ((input.isKeyDown(Input.KEY_UP)||input.isKeyDown(Input.KEY_W)) && !(input.isKeyDown(Input.KEY_DOWN)||input.isKeyDown(Input.KEY_S)) &&(input.isKeyDown(Input.KEY_LEFT)||input.isKeyDown(Input.KEY_A))&& !(input.isKeyDown(Input.KEY_RIGHT)||input.isKeyDown(Input.KEY_D))) {
			oldX = X;
			oldY = Y;
			Y -= Math.sin(45) * Speed;
			X -= Math.sin(45) * Speed;
			if(checkFrozenHit(bombeX, bombeY, frozenTimer) == true) {
				Y = oldY;
				X = oldX;
			}
			Direction = 7;
		}
		else if ((input.isKeyDown(Input.KEY_UP)||input.isKeyDown(Input.KEY_W)) && !(input.isKeyDown(Input.KEY_DOWN)||input.isKeyDown(Input.KEY_S)) && !(input.isKeyDown(Input.KEY_LEFT)||input.isKeyDown(Input.KEY_A)) && (input.isKeyDown(Input.KEY_RIGHT)||input.isKeyDown(Input.KEY_D))) {
			oldX = X;
			oldY = Y;
			Y -= Math.sin(45) * Speed;
			X += Math.sin(45) * Speed;
			if(checkFrozenHit(bombeX, bombeY, frozenTimer) == true) {
				Y = oldY;
				X = oldX;
			}
			Direction = 1;
		}
		else if (!(input.isKeyDown(Input.KEY_UP)||input.isKeyDown(Input.KEY_W)) && (input.isKeyDown(Input.KEY_DOWN)||input.isKeyDown(Input.KEY_S)) && !(input.isKeyDown(Input.KEY_LEFT)||input.isKeyDown(Input.KEY_A)) && (input.isKeyDown(Input.KEY_RIGHT)||input.isKeyDown(Input.KEY_D))) {
			oldX = X;
			oldY = Y;
			X += Math.sin(45) * Speed;
			Y += Math.sin(45) * Speed;
			if(checkFrozenHit(bombeX, bombeY, frozenTimer) == true) {
				Y = oldY;
				X = oldX;
			}
			Direction = 3;
		}
		else if (!(input.isKeyDown(Input.KEY_UP)||input.isKeyDown(Input.KEY_W)) && (input.isKeyDown(Input.KEY_DOWN)||input.isKeyDown(Input.KEY_S)) && (input.isKeyDown(Input.KEY_LEFT)||input.isKeyDown(Input.KEY_A)) && !(input.isKeyDown(Input.KEY_RIGHT)||input.isKeyDown(Input.KEY_D))) {
			oldX = X;
			oldY = Y;
			X -= Math.sin(45) * Speed;
			Y += Math.sin(45) * Speed;
			if(checkFrozenHit(bombeX, bombeY, frozenTimer) == true) {
				Y = oldY;
				X = oldX;
			}
			Direction = 5;
		}
		else {
			cAnimation = 0;
		}
		if (Y < 130) { Y = 130; }
		if (Y > container.getHeight() - Height - 30) { Y = container.getHeight() - Height - 30; }
		if (X < 30) { X = 30; }
		if (X > container.getWidth()-width - 138) { X = container.getWidth()-width - 138; }
		
		cAnimation += 1;
		if(cAnimation >= 18) {
			cAnimation = 0;
		}
		
		if(NitroTimer > 0) {
			NitroTimer += 1;
			if(NitroTimer > CONS.KAEFER_NITROTIMER_MAX) {
					NitroTimer = 0;
			}
		}
	}
	
	public int PerleEingesammelt (int perleX, int perleY, PerlenTyp typ) {
		if(perleX + 30 > X + CONS.KAEFER_COLLISSION_X && perleX < X + 100 - CONS.KAEFER_COLLISSION_X) {
			if(perleY + 30 > Y + CONS.KAEFER_COLLISSION_Y && perleY < Y + 100 - CONS.KAEFER_COLLISSION_Y) {
				if(typ == PerlenTyp.Normal) {
					Perlen += 1;
					bing.play(1.0f, settings.soundVolume);
				}
				else if(typ == PerlenTyp.Herz) {
					Leben += 1;
					bing.play(1.0f, settings.soundVolume);
				}
				else if(typ == PerlenTyp.Nitro) {
					NitroTimer = 1;
					bing.play(1.0f, settings.soundVolume);
				}
				else if(typ == PerlenTyp._3Perle) {
					Perlen += 3;
					bing.play(1.0f, settings.soundVolume);
				}
				else if(typ == PerlenTyp.Frozen) {
					bing.play(1.0f, settings.soundVolume);
				}
				else if(typ == PerlenTyp.MonsterBombe) {
					Leben = 0;
					bumm.play(1.0f, settings.soundVolume);
				}
				return 1;
			}
		}
		return 0;
	}
	
	public int BombeHit (int bombeX, int bombeY, int frozenTimer, boolean removeLife) {
		if(frozenTimer == 0) {
			if(bombeX + 30 > X + CONS.KAEFER_COLLISSION_X && bombeX < X + 100 - CONS.KAEFER_COLLISSION_X) {
				if(bombeY + 30 > Y + CONS.KAEFER_COLLISSION_Y && bombeY < Y + 100 - CONS.KAEFER_COLLISSION_Y) {
					if(!removeLife) return 1;
					Leben -= 1;
					bumm.play(1.0f, settings.soundVolume);
					return 1;
				}
			}
		}
		return 0;
	}
	
	public void Render(GameContainer container, Graphics g) {
		g.drawImage(GetImage(), X, Y, Engine.getKaeferColor(settings.chosenKaefer));
	}
	
	boolean checkFrozenHit(int bombeX, int bombeY, int frozenTimer) {
		if(frozenTimer != 0) {
			if(bombeX + 30 > X && bombeX < X + 100) {
				if(bombeY + 30 > Y && bombeY < Y + 100) {
					return CONS.IS_FROZENCOLLSION_WORKING;
				}
			}
		}
		return false;
	}
}
