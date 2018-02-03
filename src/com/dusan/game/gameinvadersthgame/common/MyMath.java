package com.dusan.game.gameinvadersthgame.common;

public class MyMath {

	public final static int clamp(int var, int min, int max){
		
		if(var >= max){
			return var = max;
		}
		else if(var <= min){
			return var = min;
		}
		else return var;
		
	}
	
}
