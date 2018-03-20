package de.samuelschepp.derkaefer;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Perle {
	
	private Image perle;
	private Image herz;
	private Image nitro;
	private Image frozen;
	private Image _3perle;
	private Image monsterBombe;
	private int way;
	private int random;
	Settings settings = new Settings();
	public int X, Y;
	public int Speed;
	public PerlenTyp Typ;
	public int FrozenTimer;
	AppGameContainer container;
	public boolean reShot;
	
	public Perle(Settings _settings, AppGameContainer _container) throws SlickException {
		settings = _settings;
		container = _container;
		perle = new Image("res/perle.png");
		herz = new Image("res/herz.png");
		nitro = new Image("res/nitro.png");
		frozen = new Image("res/frozen.png");
		_3perle = new Image("res/3perle.png");
		monsterBombe = new Image("res/monsterBombe.png");
		Speed = CONS.PERLEN_SPEED / 2;
		Y = container.getHeight();
		Typ = PerlenTyp.Normal;
		FrozenTimer = 0;
		container = _container;
	}
	
	public void Update(int kanoneX, int kanoneWay, int kanoneSpeed, int eingesammelt) {
		reShot = false;
		if(eingesammelt == 1) {
			Y = container.getHeight();
			if(Typ == PerlenTyp.Frozen) {
				FrozenTimer = 1;
			}
		}
		if(FrozenTimer > 0) {
			FrozenTimer +=1;
			if(FrozenTimer > CONS.KAEFER_NITROTIMER_MAX) {
					FrozenTimer = 0;
			}
		}
			perle.rotate(CONS.PERLEN_ROTATE_SPEED);
			herz.rotate(CONS.PERLEN_ROTATE_SPEED);
			nitro.rotate(CONS.PERLEN_ROTATE_SPEED);
			frozen.rotate(CONS.PERLEN_ROTATE_SPEED);
			_3perle.rotate(CONS.PERLEN_ROTATE_SPEED);
			monsterBombe.rotate(CONS.PERLEN_ROTATE_SPEED);
		
		Y+=Speed;
		if(way == 0) {
			X += kanoneSpeed;
			if (X > container.getWidth() - 138) { X = container.getWidth() - 138; way = 1;}
		}
		else if(way == 1) {
			X -= kanoneSpeed;
			if (X < 30) { X = 30; way = 0;}
		}
		if(Y > container.getHeight() + 280) {
			Y = 125;
			X = kanoneX + 28;
			way = kanoneWay;
			reShot = true;
			random = (int) (Math.random() * (100 - 0) + 0);
			if (random >= 1 && random <= 5) { // 5
				Typ = PerlenTyp.Nitro;
			}
			else if(random >= 6 && random <= 10) { // 6- 10
				Typ = PerlenTyp.Frozen;
			}
			else if(random >= 11 && random <= 15) {
				Typ = PerlenTyp.Herz;
			}
			else if(random >= 16 && random <= 20) {
				Typ = PerlenTyp._3Perle;
			}
			else if(random >= 21 && random <= 22) {
				Typ = PerlenTyp.MonsterBombe;
			}
			else {
				Typ = PerlenTyp.Normal;
			}
		}
	}
	
	public void Render(GameContainer container, Graphics g) {
		if(Typ == PerlenTyp.Normal) {
			g.drawImage(perle, X, Y);
		}
		else if(Typ == PerlenTyp.Herz) {
			g.drawImage(herz, X, Y);
		}
		else if(Typ == PerlenTyp.Nitro) {
			g.drawImage(nitro, X, Y);
		}
		else if(Typ == PerlenTyp.Frozen) {
			g.drawImage(frozen, X, Y);
		}
		else if(Typ == PerlenTyp._3Perle) {
			g.drawImage(_3perle, X, Y);
		}
		else if(Typ == PerlenTyp.MonsterBombe) {
			g.drawImage(monsterBombe, X-20, Y-20);
		}
	}
}