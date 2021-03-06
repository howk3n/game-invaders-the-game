package com.dusan.game.gameinvadersthegame.gameobjects;

import com.dusan.game.gameinvadersthegame.common.GOID;

public class BarrierFactory extends GameObjectFactory{

	public BarrierFactory(){}
	
	@Override
	protected GameObject createObject(int x, int y, GOID id) {
		return new Barrier(x,y,id);
	}

}
