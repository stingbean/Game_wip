package com.awesomeasians.inceptum.level.block;

import java.awt.Graphics;

import com.awesomeasians.inceptum.main.Game;
import com.awesomeasians.inceptum.texture.TextureManager;

public class BlockStone extends BlockStructure {

	private TextureManager tex = Game.tex;
	
	private int x;
	private int y;
	
	public BlockStone( int x, int y ) {
		super(x, y);
		this.x = x;
		this.y = y;
	}
	
	public void render( Graphics g ) {
		g.drawImage( tex.getBlock().get( 0 ), x, y, null );
	}

}
