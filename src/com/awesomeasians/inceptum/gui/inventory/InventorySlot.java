package com.awesomeasians.inceptum.gui.inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.awesomeasians.inceptum.item.Item;

public class InventorySlot {

	private int x;
	private int y;
	
	private int mx;
	private int my;
	
	private String name;
	
	private boolean containsItem;
	
	private BufferedImage texture;
	
	private boolean hover;
	
	public InventorySlot( int x, int y ) {
		this.x = x;
		this.y = y;
	}
	
	public void render( Graphics g ) {
	
		if( containsItem ) {
			
			g.drawImage( texture, x - 3, y - 3, 54, 54, null );
			
		}
		
		g.setColor( Color.white );
		Font f2 = new Font( "arial", Font.BOLD, 20 );
		g.setFont( f2 );
		if( hover && containsItem ) {
			g.drawString( name, mx - 5, my - 5 );
		}
		
	}
	
	 public void setItem( Item item ) {
		 this.texture = item.getTexture();
		 containsItem = true;
		 this.name = item.getName();
	 }
	 
	 public void removeItem() {
		 this.texture = null;
		 this.containsItem = false;
	 }
	 
	 public Rectangle getBounds() {
		 return new Rectangle( x, y, 48, 48 );
	 }

	public void setHover( boolean hover ) {
		this.hover = hover;
	}
	
	public void setMouse( int mx, int my ) {
		this.mx = mx;
		this.my = my;
	}
	
}