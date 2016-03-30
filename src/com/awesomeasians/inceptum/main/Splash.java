package com.awesomeasians.inceptum.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.awesomeasians.inceptum.lib.Reference;

public class Splash {
	
	public Splash() {
		
	}
	
	public void render( Graphics g ) {
		
		fadeIO( g, Game.tex.getSplash().get( 0 ) );
		//g.drawImage( Game.tex.getSplash().get( 0 ), Reference.width / 2 - 256, Reference.height / 2 - 256, 512, 512, null );
		
	}
	
	public void fadeIO( Graphics g, BufferedImage img ) {
		
		
		
	}
	
}
