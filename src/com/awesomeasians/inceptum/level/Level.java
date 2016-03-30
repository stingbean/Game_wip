package com.awesomeasians.inceptum.level;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.awesomeasians.inceptum.level.block.Block;
import com.awesomeasians.inceptum.main.Game;

public class Level {
	
	private Game game;
	
	private ArrayList<Block> level;
	
	public Level( Game game ) {
		this.game = game;
		level = new ArrayList<Block>();
	}
	
	public void renderBackground( Graphics g ) {
		
		g.setColor( new Color( 54, 189, 218 ) );
		g.fillRect( 0, 0, game.getWidth(), game.getHeight() );
		
	}
	
	public void render( Graphics g ) {
		
			for ( Block b: level ) {
				b.render(g);
			}
		
	}
	
	public void clear() {
		
		level.clear();
		
	}
	
	public ArrayList<Block> get() {
		return level;
	}
	
	public void add( Block block ) {
		level.add( block );
	}
	
}
