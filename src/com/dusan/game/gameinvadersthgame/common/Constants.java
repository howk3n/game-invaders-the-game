package com.dusan.game.gameinvadersthgame.common;

import java.awt.Color;

import com.dusan.game.gameinvadersthgame.main.Game;

public class Constants {
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;
	public static final int DEFAULT_SCREEN_WIDTH = 640;
	public static final int DEFAULT_SCREEN_HEIGHT = 640;
	public static final int HUD_HEIGHT = 60;
	
	public static final int JUNIOR = 0;
	public static final int MEDIOR = 1;
	public static final int SENIOR = 2;
	public static final int [][] ALIENS_PER_LEVEL = {
			{0,0,0},
			{22,22,11},
			{22,22,22},
			{22,33,11},
			{11,33,22}
	};
	
	public static final int NUMBER_OF_BARRIERS = 4;
	
	public static final int DEFAULT_BASIC_BARRIER_WIDTH = DEFAULT_SCREEN_WIDTH / 9; //70
	public static final int DEFAULT_BASIC_BARRIER_HEIGHT = DEFAULT_SCREEN_HEIGHT / 20; //32
	
	public static final int DEFAULT_PLAYER_WIDTH = DEFAULT_SCREEN_WIDTH * 3 / 40; //48
	public static final int DEFAULT_PLAYER_HEIGHT = DEFAULT_SCREEN_WIDTH / 32; //20
	public static final Color DEFAULT_PLAYER_COLOR = Color.WHITE;
	public static final int PLAYER_STARTING_X = DEFAULT_BASIC_BARRIER_WIDTH + (DEFAULT_BASIC_BARRIER_WIDTH - DEFAULT_PLAYER_WIDTH) / 2;
	public static final int PLAYER_STARTING_Y = Game.HEIGHT - DEFAULT_PLAYER_HEIGHT;
	public static final int PLAYER_STARTING_LIVES = 3;
	public static final int PLAYER_BULLET_WIDTH = 5;
	public static final int PLAYER_BULLET_HEIGHT = 10;
	public static final Color PLAYER_BULLET_COLOR = Color.WHITE;
	
	public static final int ALIEN_COLUMN_COUNT = 11;
	
	public static final Color ALIEN_COLOR = Color.GREEN;
	public static final int ALIEN_HEIGHT = 30;
	public static final int ALIEN_BULLET_WIDTH = 5;
	public static final int ALIEN_BULLET_HEIGHT = 10;
	public static final Color ALIEN_BULLET_COLOR = ALIEN_COLOR;
	public static final int ALIEN_VERTICAL_SPEED = 20;
	public static final int ALIEN_HORIZONTAL_SPEED = 5;

	public static final int [] ALIEN_WIDTH = { 30, 28, 26 };
	
	public static final Color FLYING_SAUCER_COLOR = Color.RED;
	public static final int FLYING_SAUCER_WIDTH = 64;
	public static final int FLYING_SAUCER_HEIGHT = 16;
	
	public static int getTotalAliensPerLevel(int level){
		return ALIENS_PER_LEVEL[level][JUNIOR] + ALIENS_PER_LEVEL[level][MEDIOR] + ALIENS_PER_LEVEL[level][SENIOR];
	}
	
}
