package de.samuelschepp.derkaefer;

import org.newdawn.slick.Color;

public class Engine {
	public static Color getKaeferColor (int selection) {
		switch (selection) {
			case 0: return Color.red;
			case 1: return Color.yellow;
			case 2: return Color.cyan;
			case 3: return Color.gray;
			case 4: return Color.green;
			case 5: return Color.magenta;
			case 6: return Color.orange;
			case 7: return Color.white;
			default: return Color.red;
		}
	}	
}
