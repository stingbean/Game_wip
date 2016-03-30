package com.awesomeasians.inceptum.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.awesomeasians.inceptum.lib.Reference;

public class Meteor {

	Random rand = new Random();
	
	private int x;
	private int y;

	private int speed = rand.nextInt( 100 ) + 200;
	private int counter = 0;
	
	private BufferedImage meteor;
	
	public Meteor( BufferedImage meteor, int x, int y ) {
		this.x = x;
		this.y = y;
		this.meteor = meteor;
	}
	public Meteor( BufferedImage meteor ) {
		this.meteor = meteor;
	
		reset();
		
	}
	
	public void reset() {
		
		x = rand.nextInt( Reference.width * 2 );
		
		if( x <= 1440 ) {
			y = -meteor.getHeight();
		} else {
			x = 1440;
			y = rand.nextInt( Reference.height );
		}
		
		speed = rand.nextInt( 100 ) + 200;
		
	}
	
	private void animate() {
		
		if( counter < speed ) {
			counter++;
		} else {
			x -= 4;
			y += 2;
			counter = 0;
		}
		
		if( ( x <= ( -meteor.getWidth() ) || ( y >= Reference.height ) ) ) {
			
			reset();
			
		}

	}
	
	public void render( Graphics g ) {
		animate();
		g.drawImage( meteor, x, y, null );
	}
}
