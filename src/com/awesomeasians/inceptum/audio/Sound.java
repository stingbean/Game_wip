package com.awesomeasians.inceptum.audio;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	
	private String path;
	
	private AudioInputStream audioInputStream;
	
	private Clip clip;
	
	private FloatControl volume;
	
	private boolean canPlay = true;
	
	public Sound( String path ) {
		this.path = path;
		
		try {
			
			audioInputStream = AudioSystem.getAudioInputStream( this.getClass().getResource( path ) );
			clip = AudioSystem.getClip();
			clip.open( audioInputStream );
			
			volume = ( FloatControl ) clip.getControl( FloatControl.Type.MASTER_GAIN );
			
		} catch ( UnsupportedAudioFileException | IOException | LineUnavailableException e ) {
			e.printStackTrace();
		}

	}
	
	public void play() {
		if( canPlay ) {
			clip.stop();
			clip.setMicrosecondPosition( 0 );
			clip.start();
		}
	}
	
	public void loop() {
		clip.loop( Clip.LOOP_CONTINUOUSLY );
	}
	
	public void stop() {
		clip.stop();
	}
	
	public String getPath() {
		return path;
	}

	public boolean isCanPlay() {
		return canPlay;
	}

	public void setCanPlay( boolean canPlay ) {
		this.canPlay = canPlay;
	}
	
	public void setVolume( Float level ) {
		volume.setValue( level );
	}
	
}
