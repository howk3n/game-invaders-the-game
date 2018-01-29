package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.GOID;

public class FlyingSaucerFactory extends GameObjectFactory{

	public FlyingSaucerFactory(){}
	
	@Override
	protected GameObject createObject(int x, int y, GOID id) {
		return new FlyingSaucer(x, y, id);
	}

}
