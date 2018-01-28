package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.ID;

public class PlayerFactory extends GameObjectFactory{
	
	public PlayerFactory(){}

	@Override
	protected GameObject createObject(int x, int y, ID id) {
		
		return Player.getInstance();
		
	}


}
