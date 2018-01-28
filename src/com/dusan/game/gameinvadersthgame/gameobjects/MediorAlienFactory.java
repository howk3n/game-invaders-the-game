package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.ID;

public class MediorAlienFactory extends AlienFactory{

	public MediorAlienFactory(){}
	
	@Override
	protected GameObject createObject(int x, int y, ID id) {
		return new MediorAlien(x, y, id);
	}
	
}
