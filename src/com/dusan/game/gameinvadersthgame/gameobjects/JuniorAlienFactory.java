package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.GOID;

public class JuniorAlienFactory extends AlienFactory{

	public JuniorAlienFactory(){}
	
	@Override
	protected GameObject createObject(int x, int y, GOID id) {
		return new JuniorAlien(x, y, id);
	}

}
