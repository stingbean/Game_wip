package com.awesomeasians.inceptum.entity.player;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.awesomeasians.inceptum.gui.inventory.Inventory;
import com.awesomeasians.inceptum.item.Item;
import com.awesomeasians.inceptum.level.block.Block;
import com.awesomeasians.inceptum.lib.Reference;
import com.awesomeasians.inceptum.main.Game;
import com.awesomeasians.inceptum.render.Animation;
import com.awesomeasians.inceptum.texture.TextureManager;

public class Player {

	private Game game;
	private TextureManager tex = Game.tex;
	
	private Animation playerWalkRight;
	private Animation playerWalkLeft;
	
	private int facing = 1;
	
	private int x;
	private int y;
	
	private int velX = 0;
	private int velY = 0;
	
	private int speed = 7;
	private int jumpHeight = 17;
	private int speedMax = 15;
	private int gravity = 1;

	private boolean falling = true;
	private boolean jumping = false;
	
	private Inventory inventory;
	
	public Player ( int x, int y, Game game ) {
		this.x = x;
		this.y = y;
		this.game = game;
	
		playerWalkRight = new Animation( 5, tex.getPlayer(), 1, 6 );
		playerWalkLeft = new Animation( 5, tex.getPlayer(), 8, 13 );
		
		inventory = new Inventory( tex.getInventory(), Reference.width / 2 - 150, Reference.height / 2 - 90, 5, 3 );
		inventory.getInventory()[0][0].setItem( new Item( "swordTest", "Test Sword", tex.getSword() ) );
		
	}
	
	public void tick() {
		
		x += velX;
		y += velY;
		
		if( velX < 0 ) {
			facing = -1;
		} else if( velX > 0 ) {
			facing = 1;
		}
		
		if( falling || jumping) {
			velY += gravity;
			
			if( velY >= speedMax ) {
				velY = speedMax;
			}
			
		}
		
		playerWalkRight.runAnimation();
		playerWalkLeft.runAnimation();
		
		collision();
		
	}
	
	public void render( Graphics g ) {

		if( jumping ) {
			
			if( facing == 1 ) {
				g.drawImage( tex.getPlayer().get( 14 ), x, y, null );
			} else if( facing == -1 ) {
				g.drawImage( tex.getPlayer().get( 15 ), x, y, null );
			}
			
		} else {
			
			if( velX != 0 ) {
				if( facing == 1 ) {
					playerWalkRight.drawAnimation( g, x, y);
				} else if( facing == -1 ) {
					playerWalkLeft.drawAnimation( g, x, y);
				}
			} else {
				if( facing == 1 ) {
					g.drawImage( tex.getPlayer().get( 0 ), x, y, null );
				} else if( facing == -1 ) {
					g.drawImage( tex.getPlayer().get( 7 ), x, y, null );
				}
			}
			
		}
		
		/*
		//Player Bounds
		g.setColor( Color.RED );
		g2D.draw( getBoundsRight() );
		g2D.draw( getBoundsLeft() );
		g.setColor( Color.BLUE );
		g2D.draw( getBoundsTop() );
		g2D.draw( getBoundsBottom() );
		*/
		
	}
	
	public void renderHotbar( Graphics g ) {
		
		//g.drawImage( tex.getHUD(), 0, 0, null );
		
		g.drawImage( tex.getHotbar(), Reference.width / 2 - 150, Reference.height - 60 - 20, 300, 60, null );
		
	}
	
	public void renderInventory( Graphics g ) {
		inventory.render(g);
	}
	
	public Rectangle getBounds() {
		return new Rectangle( x, y, 64, 128 );
	}
	public Rectangle getBoundsRight() {
		return new Rectangle( x + 64 - 5, y + 31, 5, 66 );
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle( x, y + 31, 5, 66 );
	}
	public Rectangle getBoundsTop() {
		return new Rectangle( x + 10, y, 44, 64 );
	}
	public Rectangle getBoundsBottom() {
		return new Rectangle( x + 10, y + 64, 44, 64 );
	}
	
	private void collision() {
		
		for( Block b: game.getLevels().get( game.getLevel() ).get() ) {
			
			if( ( getBoundsRight().intersects( b.getBounds() ) ) && ( b.getType().equals( "structure" ) )  ) {
				x = b.getX() - 64;
			}
			if( ( getBoundsLeft().intersects( b.getBounds() ) ) && ( b.getType().equals( "structure" ) ) ) {
				x = b.getX() + 32;
			}
			if( ( getBoundsTop().intersects( b.getBounds() ) ) && ( b.getType().equals( "structure" ) ) ) {
				velY = 0;
				y = b.getY() + 32;
				falling = true;
			}
			if( ( getBoundsBottom().intersects( b.getBounds() ) ) && ( b.getType().equals( "structure" ) ) ) {
				velY = 0;
				y = b.getY() - 128;
				falling = false;
				jumping = false;
			} else {
				falling = true;
			}
			
			if( ( getBounds().intersects( b.getBounds() ) ) && ( b.getType().equals( "end" ) ) ) {
				game.nextLevel();
				break;
			}
			
		}
		
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setX( int x ) {
		this.x = x;
	}
	public void setY( int y ) {
		this.y = y;
	}
	
	public void setVelX( int velX ) {
		this.velX = velX;
	}
	public void setVelY( int velY ) {
		this.velY = velY;
	}

	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public boolean isFalling() {
		return falling;
	}
	public void setFalling( boolean falling ) {
		this.falling = falling;
	}
	
	public boolean isJumping() {
		return jumping;
	}
	public void setJumping( boolean jumping ) {
		this.jumping = jumping;
	}

	public int getJumpHeight() {
		return jumpHeight;
	}

	public void setJumpHeight( int jumpHeight ) {
		this.jumpHeight = jumpHeight;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
}
