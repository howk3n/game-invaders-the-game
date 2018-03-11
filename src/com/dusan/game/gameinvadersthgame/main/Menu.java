package com.dusan.game.gameinvadersthgame.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.dusan.game.gameinvadersthegame.gfx.Assets;
import com.dusan.game.gameinvadersthgame.common.Constants;

public class Menu {
	
	public static Rectangle[] buttons;
	
	public static void init(){
		buttons = new Rectangle[Constants.MENU_BUTTONS.length];
		for(int i = 0; i < Constants.MENU_BUTTONS.length; i++){
			buttons[i] = new Rectangle((Game.getInstance().width - Assets.buttonWidth) / 2, 100 + (Assets.buttonHeight + 10) * i, Assets.buttonWidth, Assets.buttonHeight);
		}
	}
	
	
	public static void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED);
		g.setFont(new Font("arial", Font.BOLD, 28));
		g.drawString("Game Invaders - The Game", Game.getInstance().width / 5, Constants.HUD_HEIGHT);
		Rectangle button;
		for(int i = 0; i < Constants.MENU_BUTTONS.length; i++){
			button = buttons[i];
			g2d.drawImage(Assets.menuButtons.get(Constants.MENU_BUTTONS[i]), button.x, button.y, button.width, button.height, Game.getInstance());
		}
	}
	
}
