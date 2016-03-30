package com.awesomeasians.inceptum.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.awesomeasians.inceptum.main.Game;

public class MouseInput extends MouseAdapter {

	private Game game;
	
	public MouseInput( Game game ) {
		this.game = game;
	}
	
	public void mousePressed( MouseEvent e ) {
		game.mousePressed(e);
	}
	
	public void mouseClicked( MouseEvent e ) {
		game.mouseClicked(e);
	}
	
	public void mouseReleased( MouseEvent e ) {
		game.mouseReleased(e);
	}
	
}
