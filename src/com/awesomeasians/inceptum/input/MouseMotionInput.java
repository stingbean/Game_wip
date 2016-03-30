package com.awesomeasians.inceptum.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import com.awesomeasians.inceptum.main.Game;

public class MouseMotionInput extends MouseMotionAdapter {

	private Game game;
	
	public MouseMotionInput( Game game ) {
		this.game = game;
	}
	
	public void mouseMoved( MouseEvent e ) {
		game.mouseMoved(e);
	}
	
	public void mouseDragged( MouseEvent e ) {
		game.mouseDragged(e);
	}
	
}
