package com.awesomeasians.inceptum.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.awesomeasians.inceptum.audio.SoundManager;
import com.awesomeasians.inceptum.entity.player.Camera;
import com.awesomeasians.inceptum.entity.player.Player;
import com.awesomeasians.inceptum.input.KeyInput;
import com.awesomeasians.inceptum.input.MouseInput;
import com.awesomeasians.inceptum.input.MouseMotionInput;
import com.awesomeasians.inceptum.level.Level;
import com.awesomeasians.inceptum.level.block.BlockDirt;
import com.awesomeasians.inceptum.level.block.BlockEnd;
import com.awesomeasians.inceptum.level.block.BlockGrassTop;
import com.awesomeasians.inceptum.level.block.BlockStone;
import com.awesomeasians.inceptum.lib.Reference;
import com.awesomeasians.inceptum.texture.TextureManager;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = -4201984252499270892L;
	
	//Thread
	private Thread thread;
	private boolean running = false;

	//FPS
	//private int FPS;
	
	//Menu
	private Menu menu;
	
	//Player
	private Player p;
	
	//Camera
	private Camera cam;
	
	//Texture Manager
	public static TextureManager tex;
	
	//Sound Manager
	public static SoundManager sound;
	
	//Levels
	private ArrayList<Level> levels = new ArrayList<Level>();
	private static int level = 0;
	
	//Test
	private boolean canSwitchToGame = true;
	
	//Splash
	private Splash splash;
	
	//Game State
	public static GameState state = GameState.MENU;
	
	public void init() {
		
		requestFocus();
		
		//Texture Manager
		tex = new TextureManager();
		
		//Sound Manager
		sound = new SoundManager();
		
		//Main Menu
		menu = new Menu();
		
		//KeyListener
		addKeyListener( new KeyInput( this ) );
		
		//MouseListener
		addMouseListener( new MouseInput( this ) );
		
		//MouseMotionListener
		addMouseMotionListener( new MouseMotionInput( this ) );
		
		//Levels
		LoadImageLevel( tex.getLevel().get( 0 ) ); //Level 1
		LoadImageLevel( tex.getLevel().get( 1 ) ); //Level 2

		//Player
		p = new Player( 64, 64, this );
		
		//Camera
		cam = new Camera( 0, 0 );
		
		//Splash
		splash = new Splash();
		
	}
	
	private synchronized void start() {
		
		if( running )
			return;
		
		running = true;
		thread = new Thread( this );
		thread.start();
		
	}
	
	private synchronized void stop() {
		
		if( !running )
			return;
		
		running = false;
		try {
			thread.join();
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}
		System.exit( 1 );
		
	}
	
	public void run() {
		
		init();
		
		long lastTime = System.nanoTime();
		final double FPS = 60.0;
		double ns = 1000000000 / FPS;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while( running ) {
			
			long now = System.nanoTime();
			delta += ( now - lastTime ) / ns;
			lastTime = now;
			if( delta >= 1 ) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if( System.currentTimeMillis() - timer > 1000 ) {
				timer += 1000;
				System.out.println( "FPS: " + frames + ", Ticks: " + updates );
				//this.FPS = frames;
				updates = 0;
				frames = 0;
			}
			
		}
		
		stop();
		
	}
	
	private void tick() {

		if( state == GameState.MENU ) {
			menu.tickMenu();
		}
		
		if( state == GameState.OPTIONS ) {
			menu.tickOptions();
		}
		
		if( state == GameState.GAME || state == GameState.INVENTORY || state == GameState.PAUSE ) {
			p.tick();
			cam.tick(p);
		}
			
	}
	
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if( bs == null ) {
			
			createBufferStrategy( 3 );
			return;
			
		}
		
		Graphics g = bs.getDrawGraphics();
		
		Graphics2D g2D = (Graphics2D) g;
		///////////////////////////////////////
		
		//Splash
		if( state == GameState.SPLASH ) {
			
			g.setColor( Color.white );
			g.fillRect( 0, 0, getWidth(), getHeight() );
			
			splash.render( g );
			
		}
		
		//Main Menu
		if( state == GameState.MENU ) {
			
			g.drawImage( tex.getMainMenu().get( 0 ), 0, 0, getWidth(), getHeight(), this );
			
			menu.render(g);
			
		}
		
		//Options
		if( state == GameState.OPTIONS ) {
			
			g.drawImage( tex.getMainMenu().get( 0 ), 0, 0, getWidth(), getHeight(), this );
			
			menu.renderOptions(g);
			
		}
		
		//Game
		if( state == GameState.GAME || state == GameState.INVENTORY || state == GameState.PAUSE ) {
			
			//Background
			levels.get( level ).renderBackground(g);
			
		}
		
		g2D.translate( cam.getX(), cam.getY() );
		///////////////////////////////////////////////////////////////////////////////
		
		if( state == GameState.GAME || state == GameState.INVENTORY || state == GameState.PAUSE ) {
			
			//Level
			levels.get( level ).render(g);
			
			//Player
			p.render(g);
			
		}

		///////////////////////////////////////////////////////////////////////////////
		g2D.translate( -cam.getX(), -cam.getY() );
		
		///////////////////////////////////////
		
		//Hotbar
		if( state == GameState.GAME || state == GameState.INVENTORY ) {
			
			//Hotbar
			p.renderHotbar(g);
			
		}
		
		//Inventory
		if( state == GameState.INVENTORY ) {

			//Inventory
			p.renderInventory(g);
			
		}
		
		//Pause
		if( state == GameState.PAUSE ) {
			
			g.drawImage( tex.getMainMenu().get( 16 ), 0, 0, getWidth(), getHeight(), this );
			
		}
		
		//FPS
		/*
		Font fps = new Font( "arial", Font.BOLD, 30 );
		g.setFont( fps );
		g.setColor( Color.yellow );
		g.drawString( Integer.toString( FPS ), 10, 30);
		*/
		
		g.dispose();
		bs.show();
		
	}
		
	
	public void keyPressed( KeyEvent e ) {
		
		int key = e.getKeyCode();
		
		if( state == GameState.GAME ) {
			
			if( key == KeyEvent.VK_ESCAPE ) {
				state = GameState.PAUSE;
				test();
			}
			
			if( key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D ) {
				p.setVelX( p.getSpeed() );
			} else if( key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
				p.setVelX( -p.getSpeed() );
			} else if( key == KeyEvent.VK_SPACE && !( p.isJumping() ) ) {
				p.setJumping( true );
				p.setFalling( false );
				p.setVelY( -p.getJumpHeight() );
			}
			
			if( key == KeyEvent.VK_E ) {
				state = GameState.INVENTORY;
				canSwitchToGame = false;
			}
			
		}
		
		if( state == GameState.INVENTORY ) {
			
			if( key == KeyEvent.VK_E && canSwitchToGame ) {
				state = GameState.GAME;
			}
			
		}
		
		if( state == GameState.PAUSE ) {
			
			if( key == KeyEvent.VK_ESCAPE ) {
				state = GameState.GAME;
			}
			
		}
		
		canSwitchToGame = true;
		
	}
	public void keyReleased( KeyEvent e ) {
		
		int key = e.getKeyCode();
		
		if( key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D ) {
			p.setVelX(0);
		} else if( key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A ) {
			p.setVelX(0);
		} else if( key == KeyEvent.VK_SPACE ) {
			
		}
		
	}
	
	public void mousePressed( MouseEvent e ) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if( state == GameState.OPTIONS ) {
			
			//Master Volume Slider
			menu.getOptionSliders().get( 0 ).setMouse( mx, my );
			
			//Master Volume
			if( menu.getOptionSliders().get( 0 ).getBounds().contains( new Point( mx, my )) ) {
				menu.getOptionSliders().get( 0 ).setSelected( true );
			}
			
		}
		
	}
	
	public void mouseClicked( MouseEvent e ) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if( state == GameState.MENU ) {
			
			//Play button
			if( menu.getMenuButtons().get( 0 ).getBounds().contains( new Point( mx, my ) ) ) {
				state = GameState.GAME;
				menu.stopMusic();
			}
			//Options Button
			if( menu.getMenuButtons().get( 1 ).getBounds().contains( new Point( mx, my ) ) ) {
				state = GameState.OPTIONS;
			}
			//Help Button
			if( menu.getMenuButtons().get( 2 ).getBounds().contains( new Point( mx, my ) ) ) {
				test();
			}
			//Quit Button
			if( menu.getMenuButtons().get( 3 ).getBounds().contains( new Point( mx, my ) ) ) {
				System.exit( 1 );
			}
			
		}
		
		if( state == GameState.OPTIONS ) {
			
			//Done Button
			if( menu.getOptionButtons().get( 0 ).getBounds().contains( new Point( mx, my ) ) ) {
				state = GameState.MENU;
				menu.getOptionButtons().get( 0 ).setHover( false );
			}
			
		}
		
	}
	
	public void mouseReleased( MouseEvent e ) {
		
		if( state == GameState.OPTIONS ) {
			
			//Master Volume
			if( menu.getOptionSliders().get( 0 ).isSelected() ) {
				menu.getOptionSliders().get( 0 ).setSelected( false );
			}
			
		}
		
	}
	
	public void mouseMoved( MouseEvent e ) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if( state == GameState.MENU ) {
			
			//Play Button
			if( menu.getMenuButtons().get( 0 ).getBounds().contains( new Point( mx, my ) ) ) {
				menu.getMenuButtons().get( 0 ).setHover( true );
			} else {
				menu.getMenuButtons().get( 0 ).setHover( false );
			}
			//Options Button
			if( menu.getMenuButtons().get( 1 ).getBounds().contains( new Point( mx, my ) ) ) {
				menu.getMenuButtons().get( 1 ).setHover( true );
			} else {
				menu.getMenuButtons().get( 1 ).setHover( false );
			}
			//Help Button
			if( menu.getMenuButtons().get( 2 ).getBounds().contains( new Point( mx, my ) ) ) {
				menu.getMenuButtons().get( 2 ).setHover( true );
			} else {
				menu.getMenuButtons().get( 2 ).setHover( false );
			}
			//Quit Button
			if( menu.getMenuButtons().get( 3 ).getBounds().contains( new Point( mx, my ) ) ) {
				menu.getMenuButtons().get( 3 ).setHover( true );
			} else {
				menu.getMenuButtons().get( 3 ).setHover( false );
			}
			
		}
		
		if( state == GameState.OPTIONS ) {
			
			//Done Button
			if( menu.getOptionButtons().get( 0 ).getBounds().contains( new Point( mx, my ) ) ) {
				menu.getOptionButtons().get( 0 ).setHover( true );
			} else {
				menu.getOptionButtons().get( 0 ).setHover( false );
			}
			
		}
		
		if( state == GameState.INVENTORY ) {
			
			if( p.getInventory().getInventory()[0][0].getBounds().contains( new Point( mx, my ) ) ) {
				p.getInventory().getInventory()[0][0].setHover( true );
				p.getInventory().getInventory()[0][0].setHover( true );
				p.getInventory().getInventory()[0][0].setMouse( mx, my );
			} else {
				p.getInventory().getInventory()[0][0].setHover( false );
			}
			
		}
		
	}
	
	public void mouseDragged( MouseEvent e ) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if( state == GameState.OPTIONS ) {
			
			//Master Volume Slider
			menu.getOptionSliders().get( 0 ).setMouse( mx, my );
			
		}
		
	}
	
	private void LoadImageLevel( BufferedImage img ) {
		
		Level temp = new Level( this );
		
		int w = img.getWidth();
		int h = img.getHeight();
		
		for( int xx = 0; xx < w; xx++ ) {
			for( int yy = 0; yy < h; yy++ ) {
				
				int pixel = img.getRGB(xx, yy);
				int red = ( pixel >> 16 ) & 0xff;
				int green = ( pixel >> 8 ) & 0xff;
				int blue = ( pixel ) & 0xff;
				
				if( red == 255 && green == 255 && blue == 255) {
					temp.add( new BlockStone( xx * 32, yy * 32 ) ); //Stone
				}
				if( red == 102 && green == 51 && blue == 0) {
					temp.add( new BlockDirt( xx * 32, yy * 32 ) ); //Dirt
				}
				if( red == 0 && green == 255 && blue == 0) {
					temp.add( new BlockGrassTop( xx * 32, yy * 32 ) ); //Grass Top
				}
				if( red == 255 && green == 216 && blue == 0) {
					temp.add( new BlockEnd( xx * 32, yy * 32 ) ); //End
				}
				
			}
		}
		
		levels.add( temp );
		
	}
	
	public void nextLevel() {
		levels.get( level ).clear();
		level++;
		p = new Player( 64, 256, this );
	}
	
	public ArrayList<Level> getLevels() {
		return levels;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void test() {
		System.out.println("test");
	}
	
	public static void main( String[] args ) {

		Game game = new Game();
		
		game.setPreferredSize( new Dimension( Reference.width, Reference.height ) );
		game.setMaximumSize( new Dimension( Reference.width, Reference.height ) );
		game.setMinimumSize( new Dimension( Reference.width, Reference.height ) );
		
		JFrame frame = new JFrame( Reference.title );
		frame.add( game );
		frame.pack();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setResizable( false );
		frame.setLocationRelativeTo( null );
		frame.setVisible( true );
		
		game.start();
		
	}
	
}
