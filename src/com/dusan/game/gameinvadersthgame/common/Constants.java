package com.dusan.game.gameinvadersthgame.common;

import java.awt.Color;

public class Constants {
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;
	public static final int DEFAULT_SCREEN_WIDTH = 640;
	public static final int DEFAULT_SCREEN_HEIGHT = 640;
	public static final int HUD_HEIGHT = 60;
	
	public static final int [] ALIENS_PER_LEVEL = {0, 55, 66, 66, 66};
	public static final int [] BASIC_ALIENS_PER_LEVEL = {0, 22, 22, 22, 11};
	public static final int [] MEDIOR_ALIENS_PER_LEVEL = {0, 22, 22, 33, 33};
	public static final int [] SENIOR_ALIENS_PER_LEVEL = {0, 11, 22, 11, 22};
	
	public static final int NUMBER_OF_BARRIERS = 4;
	
	public static final int DEFAULT_BASIC_BARRIER_WIDTH = ((DEFAULT_SCREEN_WIDTH/10)/9)*10; //70
	public static final int DEFAULT_BASIC_BARRIER_HEIGHT = DEFAULT_SCREEN_HEIGHT/20; //32
	
	public static final int DEFAULT_PLAYER_WIDTH = (DEFAULT_SCREEN_WIDTH * 3)/40; //48
	public static final int DEFAULT_PLAYER_HEIGHT = DEFAULT_SCREEN_WIDTH/32; //20
	public static final Color DEFAULT_PLAYER_COLOR = Color.WHITE;
	public static final int PLAYER_BULLET_WIDTH = 5;
	public static final int PLAYER_BULLET_HEIGHT = 10;
	public static final Color PLAYER_BULLET_COLOR = Color.WHITE;
	
	
	public static final Color ALIEN_COLOR = Color.GREEN;
	public static final int ALIEN_HEIGHT = 30;
	public static final int ALIEN_BULLET_WIDTH = 5;
	public static final int ALIEN_BULLET_HEIGHT = 10;
	public static final Color ALIEN_BULLET_COLOR = ALIEN_COLOR;
	
	public static final int BASIC_ALIEN_WIDTH = 30;
	public static final int MEDIOR_ALIEN_WIDTH = 28;
	public static final int SENIOR_ALIEN_WIDTH = 26;
	
	public static final Color FLYING_SAUCER_COLOR = Color.RED;
	public static final int FLYING_SAUCER_WIDTH = 64;
	public static final int FLYING_SAUCER_HEIGHT = 16;
	
}
