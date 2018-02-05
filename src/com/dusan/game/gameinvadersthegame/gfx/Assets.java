package com.dusan.game.gameinvadersthegame.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.main.Game;


public class Assets {
	
	public static HashMap<String, BufferedImage> menuButtons = new HashMap<String, BufferedImage>();
	public static int buttonWidth;
	public static int buttonHeight;
	public static void init(){
		buttonWidth = Game.WIDTH / 2;
		buttonHeight = Game.HEIGHT / 6;
		for(int i = 0; i < Constants.MENU_BUTTONS.length; i++){
			System.out.println("/textures/"+ Constants.MENU_BUTTONS[i].toLowerCase() +"button.png");
			menuButtons.put(Constants.MENU_BUTTONS[i], loadImage("/textures/"+ Constants.MENU_BUTTONS[i].toLowerCase() +"button.png"));
			
		}
	}
	
	private static BufferedImage loadImage(String path) {
		
		try {
			return ImageIO.read(Assets.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	
	}
	
}
