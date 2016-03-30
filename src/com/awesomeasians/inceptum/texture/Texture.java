package com.awesomeasians.inceptum.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.awesomeasians.inceptum.render.BufferedImageLoader;

public class Texture {

	private BufferedImage texture;
	
	public Texture( String path ) {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			texture = loader.loadImage( path );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
	
}
