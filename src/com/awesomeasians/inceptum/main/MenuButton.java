package com.awesomeasians.inceptum.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.awesomeasians.inceptum.audio.Sound;
import com.awesomeasians.inceptum.audio.SoundManager;

public class MenuButton {

	private SoundManager sound = Game.sound;
	
	private BufferedImage button;
	private BufferedImage buttonHover;
	
	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private boolean hover = false;
	
	private Sound click = new Sound( sound.getEffect().get( 0 ).getPath() );
	
	public MenuButton( BufferedImage button, BufferedImage buttonHover, int x, int y, int width, int height ) {
		this.button = button;
		this.buttonHover = buttonHover;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	public void tick() {
		
		if( !hover ) {
			
			click.setCanPlay( true );
			
		} else if( hover ) {
			
			click.play();
			click.setCanPlay( false );
			
		}
		
	}
	
	public void render( Graphics g ) {
		
		if( !hover ) {
			
			g.drawImage( button, x, y, width, height, null );
			
		} else if( hover ) {
			
			g.drawImage( buttonHover, x, y, width, height, null );
			
		}
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle( x, y, width, height );
	}
	
	public boolean getHover() {
		return hover;
	}
	
	public void setHover( Boolean hover ) {
		this.hover = hover;
	}
	
}
