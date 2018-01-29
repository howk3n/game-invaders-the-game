package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.GOID;

public class SeniorAlienFactory extends AlienFactory {
	
	public SeniorAlienFactory(){}
	
	@Override
	protected GameObject createObject(int x, int y, GOID id) {
		return new SeniorAlien(x, y, id);
	}
}
