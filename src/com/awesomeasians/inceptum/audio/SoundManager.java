package com.awesomeasians.inceptum.audio;

import java.util.ArrayList;

public class SoundManager {

	//http://www.bfxr.net/
	
	//Effects
	private ArrayList<Sound> effect = new ArrayList<Sound>();
	private Sound click = new Sound( "/audio/click.wav" );
	
	//Music
	private ArrayList<Sound> music = new ArrayList<Sound>();
	private Sound menuBackgroundMusic = new Sound( "/audio/menuBackgroundMusic.wav" );
	
	public SoundManager() {
		
		getSounds();
		
	}
	
	private void getSounds() {
		
		//Effects
		effect.add( click ); //0
		
		//Music
		music.add( menuBackgroundMusic ); //0
		
	}
	
	public ArrayList<Sound> getEffect() {
		return effect;
	}
	
	public ArrayList<Sound> getMusic() {
		return music;
	}
	
}
