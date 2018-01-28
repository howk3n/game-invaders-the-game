package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.ID;

public abstract class Bullet extends GameObject{

	public Bullet(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected abstract void collision();

}
