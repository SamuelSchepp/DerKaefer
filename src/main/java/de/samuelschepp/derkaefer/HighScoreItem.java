package de.samuelschepp.derkaefer;

public class HighScoreItem implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	public String Name;
	public int Score;
	
	public HighScoreItem (String _name, int _score) {
		Name = _name;
		Score = _score;
	}
}
