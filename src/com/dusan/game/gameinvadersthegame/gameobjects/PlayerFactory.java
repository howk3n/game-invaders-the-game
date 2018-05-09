package com.dusan.game.gameinvadersthegame.gameobjects;

import com.dusan.game.gameinvadersthegame.common.GOID;

public class PlayerFactory extends GameObjectFactory{
	
	public PlayerFactory(){}

	@Override
	protected GameObject createObject(int x, int y, GOID id) {
		
		return new Player(x, y, id);
		
	}


}
