package com.awesomeasians.inceptum.texture;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.awesomeasians.inceptum.render.SpriteSheet;

public class TextureManager {

	//Block Sheet
	private BufferedImage sheetBlock = new Texture( "/texture/sprite/sheetBlock.png" ).getTexture();
	SpriteSheet bs = new SpriteSheet( sheetBlock );
	private ArrayList<BufferedImage> block = new ArrayList<BufferedImage>();
	
	//Player Sheet
	private BufferedImage sheetPlayer = new Texture( "/texture/sprite/sheetPlayer.png" ).getTexture();
	SpriteSheet ps = new SpriteSheet( sheetPlayer );
	private ArrayList<BufferedImage> player = new ArrayList<BufferedImage>();
	
	//Levels
	private ArrayList<BufferedImage> level = new ArrayList<BufferedImage>();
	private BufferedImage levelLayout1 = new Texture( "/texture/level/layout/levelLayout1.png" ).getTexture();
	private BufferedImage levelLayout2 = new Texture( "/texture/level/layout/levelLayout2.png" ).getTexture();
	
	//Main Menu
	private ArrayList<BufferedImage> menuMain = new ArrayList<BufferedImage>();
	private BufferedImage menuBackground = new Texture( "/texture/menu/backgroundMenu.png" ).getTexture();
	private BufferedImage title = new Texture( "/texture/menu/title.png" ).getTexture();
	private BufferedImage buttonPlay = new Texture( "/texture/menu/buttonPlay.png" ).getTexture();
	private BufferedImage buttonOptions = new Texture( "/texture/menu/buttonOptions.png" ).getTexture();
	private BufferedImage buttonHelp = new Texture( "/texture/menu/buttonHelp.png" ).getTexture();
	private BufferedImage buttonQuit = new Texture( "/texture/menu/buttonQuit.png" ).getTexture();
	private BufferedImage buttonPlayHover = new Texture( "/texture/menu/buttonPlayHover.png" ).getTexture();
	private BufferedImage buttonOptionsHover = new Texture( "/texture/menu/buttonOptionsHover.png" ).getTexture();
	private BufferedImage buttonHelpHover = new Texture( "/texture/menu/buttonHelpHover.png" ).getTexture();
	private BufferedImage buttonQuitHover = new Texture( "/texture/menu/buttonQuitHover.png" ).getTexture();
	private BufferedImage buttonSound = new Texture( "/texture/menu/buttonSound.png" ).getTexture();
	private BufferedImage buttonMute = new Texture( "/texture/menu/buttonMute.png" ).getTexture();
	private BufferedImage buttonDone = new Texture( "/texture/menu/buttonDone.png" ).getTexture();
	private BufferedImage buttonDoneHover = new Texture( "/texture/menu/buttonDoneHover.png" ).getTexture();
	private BufferedImage frameMasterVolume = new Texture( "/texture/menu/frameMasterVolume.png" ).getTexture();
	private BufferedImage sliderMasterVolume = new Texture( "/texture/menu/sliderMasterVolume.png" ).getTexture();
	private BufferedImage menuPause = new Texture( "/texture/menu/menuPause.png" ).getTexture();
	
	//Meteors
	private ArrayList<BufferedImage> meteors = new ArrayList<BufferedImage>();
	private BufferedImage meteor1 = new Texture( "/texture/menu/meteor1.png" ).getTexture();
	private BufferedImage meteor2 = new Texture( "/texture/menu/meteor2.png" ).getTexture();
	
	private BufferedImage HUD = new Texture( "/texture/HUD.png" ).getTexture();
	private BufferedImage hotbar = new Texture( "/texture/hotbar.png" ).getTexture();
	private BufferedImage inventory = new Texture( "/texture/inventory.png" ).getTexture();
	private BufferedImage sword = new Texture( "/texture/sword.png" ).getTexture();
	
	//Splash
	private ArrayList<BufferedImage> splash = new ArrayList<BufferedImage>();
	private BufferedImage logo = new Texture( "/texture/splash/logo.png" ).getTexture();
	
	public TextureManager() {
		
		getTextures();
		
	}
	
	private void getTextures() {
		
		//Blocks
		block.add( bs.grabImage( 1, 1, 32, 32 ) ); //Stone
		block.add( bs.grabImage( 1, 2, 32, 32) ); //Dirt
		block.add( bs.grabImage( 1, 3, 32, 32) ); //Grass
		
		//Player Right
		player.add( ps.grabImage( 1, 1, 64, 128) ); //0
		player.add( ps.grabImage( 3, 1, 64, 128) ); //1
		player.add( ps.grabImage( 5, 1, 64, 128) ); //2
		player.add( ps.grabImage( 7, 1, 64, 128) ); //3
		player.add( ps.grabImage( 1, 1, 64, 128) ); //5
		player.add( ps.grabImage( 9, 1, 64, 128) ); //5
		player.add( ps.grabImage( 11, 1, 64, 128) ); //6
		
		//Player Left
		player.add( ps.grabImage( 1, 5, 64, 128) ); //7
		player.add( ps.grabImage( 3, 5, 64, 128) ); //8
		player.add( ps.grabImage( 5, 5, 64, 128) ); //9
		player.add( ps.grabImage( 7, 5, 64, 128) ); //10
		player.add( ps.grabImage( 1, 5, 64, 128) ); //11
		player.add( ps.grabImage( 9, 5, 64, 128) ); //12
		player.add( ps.grabImage( 11, 5, 64, 128) ); //13
		
		//Player Jumping Right
		player.add( ps.grabImage( 15, 1, 64, 128) ); //14
		
		//Player Jumping Left
		player.add( ps.grabImage( 15, 5, 64, 128) ); //15
		
		//Levels
		level.add( levelLayout1 ); //0
		level.add( levelLayout2 ); //1
		
		//Main Menu
		menuMain.add( menuBackground ); //0
		menuMain.add( title ); //1
		menuMain.add( buttonPlay ); //2
		menuMain.add( buttonOptions ); //3
		menuMain.add( buttonHelp ); //4
		menuMain.add( buttonQuit ); //5
		menuMain.add( buttonPlayHover ); //6
		menuMain.add( buttonOptionsHover ); //7
		menuMain.add( buttonHelpHover ); //8
		menuMain.add( buttonQuitHover ); //9
		menuMain.add( buttonSound ); //10
		menuMain.add( buttonMute ); //11
		menuMain.add( buttonDone ); //12
		menuMain.add( buttonDoneHover ); //13
		menuMain.add( frameMasterVolume ); //14
		menuMain.add( sliderMasterVolume ); //15
		menuMain.add( menuPause ); //16
		
		//Meteors
		meteors.add( meteor1 ); //0
		meteors.add( meteor2 ); //1
		
		//Splash
		splash.add( logo );
		
	}
	
	public ArrayList<BufferedImage> getPlayer() {
		return player;
	}
	
	public ArrayList<BufferedImage> getBlock() {
		return block;
	}
	
	public ArrayList<BufferedImage> getLevel() {
		return level;
	}
	
	public ArrayList<BufferedImage> getMainMenu() {
		return menuMain;
	}
	
	public ArrayList<BufferedImage> getMeteors() {
		return meteors;
	}
	
	public ArrayList<BufferedImage> getSplash() {
		return splash;
	}
	
	public BufferedImage getHUD() {
		return HUD;
	}
	
	public BufferedImage getHotbar() {
		return hotbar;
	}
	
	public BufferedImage getInventory() {
		return inventory;
	}
	
	public BufferedImage getSword() {
		return sword;
	}
	
}
