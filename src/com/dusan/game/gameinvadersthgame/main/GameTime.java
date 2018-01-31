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
	
//	Todo: One way to do it it make all tick() methods receive a parameter called deltaTime (which is difference between current and previous tick).
//	Another way (which Unity uses) is implement a static class or singleton called GameTime, with a field called deltaTime. Game class would set that value in each tick,
//	and whoever needs that value can just check the GameTime class.
	
}
