package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.ID;

public class BarrierFactory extends GameObjectFactory{

	public BarrierFactory(){}
	
	@Override
	protected GameObject createObject(int x, int y, ID id) {
		return new Barrier(x,y,id);
	}

}
