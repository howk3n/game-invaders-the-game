package com.dusan.game.gameinvadersthegame.gameobjects;

import com.dusan.game.gameinvadersthegame.common.GOID;

public class MediorAlienFactory extends AlienFactory{

	public MediorAlienFactory(){}
	
	@Override
	protected GameObject createObject(int x, int y, GOID id) {
		return new MediorAlien(x, y, id);
	}
	
}
