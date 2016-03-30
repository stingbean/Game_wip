package com.awesomeasians.inceptum.render;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {

	private int speed;
	private int frames;
	
	private int index = 0;
	private int count = 0;
	
	private ArrayList<BufferedImage> images;
	private BufferedImage currentImage;
	
	public Animation( int speed, ArrayList<BufferedImage> imgs, int startIdx, int endIdx ) {
		this.speed = speed;
		images = new ArrayList<BufferedImage>();
		for ( int i = startIdx; i < endIdx + 1; i++ ) {
			images.add( imgs.get( i ) );
		}
		frames = endIdx - startIdx + 1;
		
	}
	
	public void runAnimation() {
		index++;
		if( index > speed  ) {
			index = 0;
			nextFrame();
		}
	}
	
	private void nextFrame() {
		for( int i = 0; i < frames; i ++ ) {
			if( count == i ) {
				currentImage = images.get( i );
			}
		}
		
		count++;
		
		if( count > frames ) {
			count = 0;
		}
	}
	
	public void drawAnimation( Graphics g, int x, int y ) {
		g.drawImage( currentImage, x, y, null );
	}
	
}
