package com.dusan.game.gameinvadersthgame.main;

import java.awt.Color;
import java.awt.Graphics;

import com.dusan.game.gameinvadersthgame.common.Constants;

public class HUD {
	
	private static HUD instance;
	
	private int score;
	private int lives;
	
	private HUD(){}
	
	public static HUD getInstance(){
		if(instance == null){
			instance = new HUD();
			instance.score = 0;
			instance.lives = 3;
		}
		return instance;
	}
	
	public static void incrementScore(int points){
		instance.score += points;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		g.setColor(Constants.DEFAULT_BACKGROUND_COLOR);
		g.fillRect(0, 0, Game.WIDTH, Constants.HUD_HEIGHT);
		g.setColor(Color.white);
		g.drawString("Score: " + score, Constants.HUD_HEIGHT/2, Constants.HUD_HEIGHT/2);
		g.drawString("Lives left: " + lives, Game.WIDTH-100, Constants.HUD_HEIGHT/2);
	}

}
