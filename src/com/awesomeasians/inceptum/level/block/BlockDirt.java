package com.awesomeasians.inceptum.level.block;

import java.awt.Graphics;

import com.awesomeasians.inceptum.main.Game;
import com.awesomeasians.inceptum.texture.TextureManager;

public class BlockDirt extends BlockStructure {

	private TextureManager tex = Game.tex;
	
	private int x;
	private int y;

	public BlockDirt( int x, int y ) {
		super(x, y);
		this.x = x;
		this.y = y;
	}
	
	public void render( Graphics g ) {
		g.drawImage( tex.getBlock().get( 1 ), x, y, null );
	}

}
