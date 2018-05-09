package com.dusan.game.gameinvadersthegame.gameobjects;

import com.dusan.game.gameinvadersthegame.common.GOID;

public class SeniorAlienFactory extends AlienFactory {
	
	public SeniorAlienFactory(){}
	
	@Override
	protected GameObject createObject(int x, int y, GOID id) {
		return new SeniorAlien(x, y, id);
	}
}
