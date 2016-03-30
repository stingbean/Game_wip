package com.awesomeasians.inceptum.level.block;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block {

	private int x;
	private int y;
	
	public Block( int x, int y ) {
		this.x = x;
		this.y = y;
	}
	
	public void render( Graphics g ) {
		Graphics2D g2D = (Graphics2D) g;
		g.setColor( Color.white );
		g2D.drawRect( x, y, 32, 32 );
	}
	
	public Rectangle getBounds() {
		return new Rectangle( x, y, 32, 32 );
	}
	
	public String getType() {
		return "block";
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
}
