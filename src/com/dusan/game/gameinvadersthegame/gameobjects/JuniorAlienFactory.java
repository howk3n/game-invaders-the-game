package com.dusan.game.gameinvadersthegame.gameobjects;

import com.dusan.game.gameinvadersthegame.common.GOID;

public class JuniorAlienFactory extends AlienFactory{

	public JuniorAlienFactory(){}
	
	@Override
	protected GameObject createObject(int x, int y, GOID id) {
		return new JuniorAlien(x, y, id);
	}

}
