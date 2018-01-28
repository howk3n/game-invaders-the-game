package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.ID;

public class FlyingSaucerFactory extends GameObjectFactory{

	public FlyingSaucerFactory(){}
	
	@Override
	protected GameObject createObject(int x, int y, ID id) {
		return new FlyingSaucer(x, y, id);
	}

}
