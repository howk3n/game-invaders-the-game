package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.ID;

public class BasicAlienFactory extends AlienFactory{

	public BasicAlienFactory(){}
	
	@Override
	protected GameObject createObject(int x, int y, ID id) {
		return new BasicAlien(x, y, id);
	}

}
