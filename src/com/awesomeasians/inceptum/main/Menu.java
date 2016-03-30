package com.awesomeasians.inceptum.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import com.awesomeasians.inceptum.audio.Sound;
import com.awesomeasians.inceptum.audio.SoundManager;
import com.awesomeasians.inceptum.lib.Reference;
import com.awesomeasians.inceptum.texture.TextureManager;

public class Menu {
	
	private TextureManager tex = Game.tex;
	private SoundManager sound = Game.sound;
	
	private ArrayList<MenuButton> menuButtons = new ArrayList<MenuButton>();
	private MenuButton buttonPlay = new MenuButton( tex.getMainMenu().get( 2 ), tex.getMainMenu().get( 6 ), Reference.width / 2 - 150, 350, 300, 60 );
	private MenuButton buttonOptions = new MenuButton( tex.getMainMenu().get( 3 ), tex.getMainMenu().get( 7 ), Reference.width / 2 - 150, 440, 300, 60 );
	private MenuButton buttonHelp = new MenuButton( tex.getMainMenu().get( 4 ), tex.getMainMenu().get( 8 ), Reference.width / 2 - 150, 530, 300, 60 );
	private MenuButton buttonQuit = new MenuButton( tex.getMainMenu().get( 5 ), tex.getMainMenu().get( 9 ), Reference.width / 2 - 150, 620, 300, 60 );
	
	private ArrayList<MenuButton> optionButtons = new ArrayList<MenuButton>();
	private MenuButton buttonDone = new MenuButton( tex.getMainMenu().get( 12 ), tex.getMainMenu().get( 13 ), Reference.width / 2 - 150, 620, 300, 60 );
	
	private ArrayList<MenuSlider> optionSliders = new ArrayList<MenuSlider>();
	private MenuSlider sliderMasterVolume = new MenuSlider( tex.getMainMenu().get( 14 ), tex.getMainMenu().get( 15 ), Reference.width / 2 - 300, 200, 600, 50, 18 );

	private ArrayList<Meteor> meteorShower = new ArrayList<Meteor>();
	
	private Sound backgroundMusic = sound.getMusic().get( 0 );
	
	public Menu() {
		menuButtons.add( buttonPlay );
		menuButtons.add( buttonOptions );
		menuButtons.add( buttonHelp );
		menuButtons.add( buttonQuit );

		optionButtons.add( buttonDone );
		
		optionSliders.add( sliderMasterVolume );
		
		for( int i = 0; i < 3; i++ ) {
			meteorShower.add( new Meteor( tex.getMeteors().get( 0 ) ) );
			meteorShower.add( new Meteor( tex.getMeteors().get( 1 ) ) );
		}
		
	}

	public void tickMenu() {
	
		backgroundMusic.loop();
		
		//Menu Button Sounds
		for( MenuButton b: menuButtons ) {
			b.tick();
		}
		
	}
	
	public void tickOptions() {
		
		//
		backgroundMusic.setVolume( (float) ( ( optionSliders.get( 0 ).getPercentage() * 40 ) - 40 ) );
		if( optionSliders.get( 0 ).getPercentage() == 0 ) {
			backgroundMusic.setVolume( -80F );
		}
		
		//Option Button Sounds
		for( MenuButton b: optionButtons ) {
			b.tick();
		}
		
		//Option Slider
		for( MenuSlider s: optionSliders ) {
			s.tick();
		}
		
	}
	
	public void render( Graphics g ) {

		//Meteor Shower Animation
		for( Meteor m: meteorShower ) {
			m.render(g);
		}
		
		//Title
		g.drawImage( tex.getMainMenu().get( 1 ), Reference.width / 2 - 350, 200, 700, 120, null );
		
		//Menu Buttons
		for( MenuButton b: menuButtons ) {
			b.render(g);
		}
		
		//Version
		g.setColor( Color.white );
		Font f2 = new Font( "arial", Font.BOLD, 20 );
		g.setFont( f2 );
		g.drawString( Reference.version, 30, Reference.height - 20 );

	}

	public void renderOptions( Graphics g ) {
		
		//Option Buttons
		for( MenuButton b: optionButtons ) {
			b.render(g);
		}
		
		//Option Sliders
		for( MenuSlider s: optionSliders ) {
			s.render(g);
		}
		
	}
	
	public void stopMusic() {
		backgroundMusic.stop();
	}
	
	public ArrayList<MenuButton> getMenuButtons() {
		return menuButtons;
	}
	
	public ArrayList<MenuButton> getOptionButtons() {
		return optionButtons;
	}
	
	public ArrayList<MenuSlider> getOptionSliders() {
		return optionSliders;
	}
	
}
