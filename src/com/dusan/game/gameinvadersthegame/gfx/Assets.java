package com.dusan.game.gameinvadersthegame.gfx;

import java.awt.Graphics2D;
import java.awt.Image;
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
		buttonHeight = Game.getInstance().height / 4;
		buttonWidth = buttonHeight * Constants.ORIGINAL_MENU_BUTTON_WIDTH / Constants.ORIGINAL_MENU_BUTTON_HEIGHT;
		for(int i = 0; i < Constants.MENU_BUTTONS.length; i++){
			System.out.println("/textures/"+ Constants.MENU_BUTTONS[i].toLowerCase() +"button.png");
			BufferedImage resizedButton = resize(loadImage("/textures/"+ Constants.MENU_BUTTONS[i].toLowerCase() +"button.png"), buttonWidth, buttonHeight);
			menuButtons.put(Constants.MENU_BUTTONS[i], resizedButton);
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
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
}
