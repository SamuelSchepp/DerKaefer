package de.samuelschepp.derkaefer;

public class Settings implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public float musicVolume;
	public float soundVolume;
	public int chosenKaefer;
	
	public boolean IS_FULLSCREEN;
	
	public Settings() { 
		musicVolume = 1f;
		soundVolume = 1f;
		chosenKaefer = 0;
	}
	
	public Settings(float _musicVolume, float _soundVolume, int _chosenKaefer) {
		musicVolume = _musicVolume;
		soundVolume = _soundVolume;
	}
}
