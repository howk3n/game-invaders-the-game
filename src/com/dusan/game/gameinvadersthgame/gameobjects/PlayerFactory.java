package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.GOID;

public class PlayerFactory extends GameObjectFactory{
	
	public PlayerFactory(){}

	@Override
	protected GameObject createObject(int x, int y, GOID id) {
		
		return new Player(x, y, id);
		
	}


}
