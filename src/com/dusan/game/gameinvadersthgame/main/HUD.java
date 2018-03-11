package com.dusan.game.gameinvadersthgame.main;

import java.awt.Color;
import java.awt.Graphics;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.gameobjects.Player;

public class HUD {
	
	private static HUD instance;
	
	private HUD(){}
	
	public static HUD getInstance(){
		if(instance == null){
			instance = new HUD();
		}
		return instance;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		g.setColor(Constants.DEFAULT_BACKGROUND_COLOR);
		g.fillRect(0, 0, Game.getInstance().width, Constants.HUD_HEIGHT);
		g.setColor(Color.white);
		g.drawString("Score: " + Game.getInstance().score, Constants.HUD_HEIGHT / 2, Constants.HUD_HEIGHT / 2);
		g.drawString("Lives left: " + Player.lives, Game.getInstance().width - Constants.HUD_HEIGHT * 2, Constants.HUD_HEIGHT / 2);
	}

}
