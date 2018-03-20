

package de.samuelschepp.derkaefer;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Kanone {
	
	private Image texture;
	
	public int Way;
	public int X;
	public int Y;
	public int Height;
	public int width;
	public int Speed;
	public int Direction;
	public int MoveToPlayer;
	public int FrozenTimer;
	Settings settings = new Settings();
	AppGameContainer container;
	
	public Kanone(int _direction, int _moveToPlayer, Settings _settings, AppGameContainer _container) throws SlickException {
		settings = _settings;
		container = _container;
		texture = new Image("res/kanone.png");
		width = 58;
		Height = 145;
		if(_direction == 0) {
			X = 31;
			Y = 0;
		}
		if(_direction == 1) {
			X = container.getWidth() - 120;
			Y = 100;
		}
		Speed = CONS.KANONE_SPEED / 2;
		Direction = _direction;
		MoveToPlayer = _moveToPlayer;
		Way = 0;
		FrozenTimer = 0;
		container = _container;
	}
	
	public Image GetImage() {
		if(Direction == 0) {
			texture.setRotation(180);
		}
		else if(Direction == 1) {
			texture.setRotation(270);
		}
		return texture;
	}
	
	public void Update(int kaeferX, int kaeferY) {
		if(Direction == 1) X = container.getWidth() - 120;
		if(FrozenTimer == 0) {
			if (MoveToPlayer == 0) {
				if(Direction == 0) {
					if(Way == 0) {
						X += Speed;
						if(X> container.getWidth() - 270) {
							Way = 1;
						}
					}
					else if(Way == 1) {
						X -= Speed;
						if(X<30) {
							Way = 0;
						}
					}
				}
				else if(Direction == 1) {
					if(Way == 0) {
						Y += Speed;
						if(Y > container.getHeight() - 150) {
							Way = 1;
						}
					}
					else if(Way == 1) {
						Y -= Speed;
						if(Y<100) {
							Way = 0;
						}
					}
				}
			}
			else if(MoveToPlayer == 1) {
				if(Direction == 0) {
					if(X < container.getWidth() - 270 && X > 30) {
						if(X+43 < kaeferX + 50) {
							X -= Speed;
							Way = 1;
						}
						if(X+43 > kaeferX + 50) {
							X += Speed;
							Way = 0;
						}
						else {
							Way = 2;
						}
					}
				}
				else if(Direction == 1) {
					if(Y+68 > kaeferY + 50) {
						Y -= CONS.KANONE_SPEED / 2;
						Way = 1;
					}
					if(Y+68 < kaeferY + 50) {
						Y += CONS.KANONE_SPEED / 2;
						Way = 0;
					}
	
				}
				else {
					Y = 400;
				}
			}
			else {
				Y = 400;
			}
		}
	}
	
	public void Render(GameContainer container, Graphics g) {
		g.drawImage(GetImage(), X, Y);
	}
}
