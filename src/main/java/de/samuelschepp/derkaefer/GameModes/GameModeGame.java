package de.samuelschepp.derkaefer.GameModes;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import de.samuelschepp.derkaefer.Bombe;
import de.samuelschepp.derkaefer.CONS;
import de.samuelschepp.derkaefer.HUD;
import de.samuelschepp.derkaefer.Kaefer;
import de.samuelschepp.derkaefer.Kanone;
import de.samuelschepp.derkaefer.Perle;
import de.samuelschepp.derkaefer.Rauch;
import de.samuelschepp.derkaefer.Scene;
import de.samuelschepp.derkaefer.Settings;

	public class GameModeGame  {
	public HUD hud;
	public Scene scene;
	public Kaefer Kaefer;
	public Kanone kanone1;
	public Kanone kanone2;
	public Perle perle;
	public Bombe bombe;
	public Settings settings = new Settings();;
	public Music SaxGuy;
	public Rauch rauch1;
	public Rauch rauch2;
	AppGameContainer container;
	Input input;
	
	public GameModeGame(Settings _settings, AppGameContainer _container) throws SlickException {
		container = _container;
		hud = new HUD(_settings, container);
		scene = new Scene(_settings, container);
	    Kaefer = new Kaefer(_settings, container);
	    kanone1= new Kanone(0, 0, _settings, container);
	    kanone2 = new Kanone(1, 1, _settings, container);
	    kanone2.Speed = 5;
	    perle = new Perle(_settings, container);
	    bombe = new Bombe(_settings, container);
        SaxGuy = new Music("res/saxGuy.wav");
        rauch1 = new Rauch(settings, container, false);
        rauch2 = new Rauch(settings, container, true);
        settings = _settings;
        input = container.getInput();
	}

	public GameMode update(GameContainer container, int delta) throws SlickException, IOException {
		input = container.getInput();
		if(Kaefer.Leben == 0) {
			this.WriteHighscoreFile();
			return GameMode.GameOver;
		}
		perle.Update(kanone1.X, kanone1.Way, kanone1.Speed, Kaefer.PerleEingesammelt(perle.X, perle.Y, perle.Typ));
		kanone2.FrozenTimer = bombe.FrozenTimer = perle.FrozenTimer;
		bombe.Update(kanone2.Y, kanone2.Speed, Kaefer.BombeHit(bombe.X, bombe.Y, bombe.FrozenTimer, true), Kaefer.Perlen);
		scene.Update();
		Kaefer.Update(input, bombe.X, bombe.Y, bombe.FrozenTimer);
		kanone1.Update(Kaefer.X, Kaefer.Y);
		kanone2.Update(Kaefer.X, Kaefer.Y);
		if(Kaefer.Leben == 0) hud.Update(Kaefer.Leben, Kaefer.Perlen, 1);
		hud.Update(Kaefer.Leben, Kaefer.Perlen, Kaefer.BombeHit(bombe.X, bombe.Y, bombe.FrozenTimer, false));
		if(bombe.reShot) {
			rauch2.startRauch(kanone2.X, kanone2.Y, kanone2.Way);
		}
		if(perle.reShot) {
			rauch1.startRauch(kanone1.X, kanone1.Y, kanone1.Way);
		}
		rauch1.Update(kanone1.X, kanone1.Y);
		rauch2.Update(kanone2.X, kanone2.Y);
		
		return GameMode.Game;
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		scene.Render(container, g);
		perle.Render(container, g);
		bombe.Render(container, g);
		Kaefer.Render(container, g);
		kanone1.Render(container, g);
		kanone2.Render(container, g);
		rauch1.Render(container, g);
		rauch2.Render(container, g);
		hud.Render(container, g, bombe.FrozenTimer, Kaefer.NitroTimer);
	}
	
	void WriteHighscoreFile() throws IOException {
		System.out.println("Start to read and write highscorefile");
		File f = new File(CONS.HIGHSCORE_FILE_LOCATION);
		if(!f.exists()) {
			f.createNewFile();
			System.out.println(CONS.HIGHSCORE_FILE_LOCATION + " created!");
		}
		
		ArrayList<String> hs = new ArrayList<String>();
		FileInputStream fstream;
		DataInputStream in;
		try {
			System.out.println("Reading file...");
			fstream = new FileInputStream(CONS.HIGHSCORE_FILE_LOCATION);
			in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				hs.add(strLine);
			}
			hs.add(Kaefer.Perlen+"");
			in.close();
			System.out.println("Writing file...");
			FileWriter outFile = new FileWriter(CONS.HIGHSCORE_FILE_LOCATION);
			PrintWriter out = new PrintWriter(outFile);
			for (int i = 0; i < hs.size(); i++) {
				out.println(hs.get(i));
			}
			out.close();
			System.out.println("Highscorefile done!");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
