package com.awesomeasians.inceptum.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.awesomeasians.inceptum.lib.Reference;

public class MenuSlider {

	private BufferedImage frame;
	private BufferedImage slider;
	
	private int mx;
	private int my;
	
	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private int sX;
	
	private int sWidth;
	
	private boolean hover;
	private boolean selected;
	
	public MenuSlider( BufferedImage frame, BufferedImage slider, int x, int y, int width, int height, int sWidth ) {
		this.frame = frame;
		this.slider = slider;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sWidth = sWidth;
		
		sX = x + width - sWidth;
	}
	
	public void tick() {

		//System.out.println( getPercentage() + "%");
		
		if( selected ) {
			sX = mx - sWidth / 2;
			
			if( sX < x ) {
				sX = x;
			}
			if( sX > x + width - sWidth ) {
				sX = x + width - sWidth;
			}
			
		}
		
	}
	
	public void render( Graphics g ) {
		
		g.drawImage( frame, x, y, width, height, null );
		g.drawImage( slider, sX, y, sWidth, height, null );
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle( x, y, width, height );
	}
	
	public Rectangle getSliderBounds() {
		return new Rectangle( sX, y, sWidth, height );
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected( boolean selected ) {
		this.selected = selected;
	}
	
	public void setMouse( int mx, int my ) {
		this.mx = mx;
		this.my = my;
	}
	
	public double getPercentage() {
		return ( sX - ( ( Reference.width - width) / 2 ) ) / ( double ) ( width - sWidth );
	}
	
}
