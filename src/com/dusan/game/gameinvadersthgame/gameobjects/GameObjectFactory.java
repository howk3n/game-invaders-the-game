package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.GOID;

public abstract class GameObjectFactory {
	
	public GameObject getObject(int x, int y, GOID id){
		GameObject tempObject;
		
		tempObject = createObject(x, y, id);
		
		return tempObject;
	}
	
	protected abstract GameObject createObject(int x, int y, GOID id);
	
}
