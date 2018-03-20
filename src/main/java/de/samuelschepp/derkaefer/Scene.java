package de.samuelschepp.derkaefer;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Scene {
	private Image gras;
	private Image gleise;
	private Image rand;
	Settings settings = new Settings();
	AppGameContainer container;
	
	public Scene(Settings _settings, AppGameContainer _container) throws SlickException {
		gras = new Image("res/gras.png");
		gleise = new Image("res/gleis.png");
		rand = new Image("res/rand.png");
		settings = _settings;
		container = _container;
	}
	
	public void Update () {
	}
	
	public void Render (GameContainer container, Graphics g) {
		g.drawImage(gras, 0, 0);
		g.drawImage(gras, 1280, 0);
		g.drawImage(gras, 0, 720);
		g.drawImage(gras, 1280, 720);
		
		g.drawImage(gleise, container.getWidth()-2560, 0);
		
		rand.setRotation(0);
		g.drawImage(rand, 0,0);
		rand.setRotation(-90);
		rand.setCenterOfRotation(0, 0);
		g.drawImage(rand, 0,container.getHeight());
		
	}
}
