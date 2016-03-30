package com.awesomeasians.inceptum.entity.player;

import com.awesomeasians.inceptum.lib.Reference;

public class Camera {

	private int x;
	private int y;
	
	public Camera( int x, int y ) {
		this.x = x;
		this.y = y;
	}

	public void tick( Player p ) {
		x = -p.getX() + ( Reference.width ) / 2;
		y = -p.getY() + ( Reference.height ) / 2;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	
}
