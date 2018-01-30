package com.dusan.game.gameinvadersthgame.main;

public class GameTime {

	private static GameTime instance;
	private GameTime(){}
	public static GameTime getInstance(){
		if(instance == null){
			instance = new GameTime();
		}
		return instance;
	}
	
}
