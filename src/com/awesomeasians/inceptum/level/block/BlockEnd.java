package com.awesomeasians.inceptum.level.block;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BlockEnd extends Block {

	private int x;
	private int y;
	
	public BlockEnd(int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
	}
	
	public void render( Graphics g ) {
		Graphics2D g2D = (Graphics2D) g;
		g.setColor( Color.yellow );
		g2D.fillRect( x, y, 32, 32);
	}
	
	public String getType() {
		return "end";
	}
	
}
