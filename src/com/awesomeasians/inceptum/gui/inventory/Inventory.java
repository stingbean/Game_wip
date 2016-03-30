package com.awesomeasians.inceptum.gui.inventory;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Inventory {
	
	private BufferedImage texture;
	
	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private InventorySlot[][] inventory;
	
	public Inventory( BufferedImage texture, int x, int y, int width, int height ) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		inventory = new InventorySlot[width][height];
		
		int posX = 6;
		int posY = 6;
		for( int yy = 0; yy < height; yy++ ) {
			
			posX = 6;
			
			for( int xx = 0; xx < width; xx++ ) {
				
				inventory[xx][yy] = new InventorySlot( x + posX, y + posY );
				
				posX += 60;
				
			}
			
			posY += 60;
			
		}
		
	}
	
	public void render( Graphics g ) {
		
		//Inventory Layout
		g.drawImage( texture, x, y, width * 60, height * 60, null );
		
		//Render Slots
		for( InventorySlot[] row: inventory ) {
			for( InventorySlot is: row ) {
				is.render(g);
			}
		}
		
	}
	
	public InventorySlot[][] getInventory() {
		return inventory;
	}
	
}
