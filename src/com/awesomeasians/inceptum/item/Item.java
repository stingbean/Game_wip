package com.awesomeasians.inceptum.item;

import java.awt.image.BufferedImage;

public class Item {

	private String unlocalizedName;
	private String name;
	
	private BufferedImage texture;
	
	public Item( String unlocalizedName, String name, BufferedImage texture ) {
		this.unlocalizedName = unlocalizedName;
		this.name = name;
		this.texture = texture;
	}
	
	public String getUnlocalizedName() {
		return unlocalizedName;
	}
	
	public String getName() {
		return name;
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
	
}
