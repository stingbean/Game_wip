package com.awesomeasians.inceptum.render;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet( BufferedImage spritesheet ) {
		this.image = spritesheet;
	}
	
	public BufferedImage grabImage( int col, int row, int width, int height ) {
		
		BufferedImage img = image.getSubimage(  (col * 32) - 32, (row * 32) - 32, width, height );
		return img;
		
	}
	
}
