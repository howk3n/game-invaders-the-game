package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.GOID;

public abstract class Bullet extends GameObject{

	public Bullet(int x, int y, GOID id) {
		super(x, y, id);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected abstract void collision();
//	Eventually remove collision from Bullet and all inheriting classes, and instead create a CollisionManager which answers only to Handler class.

}
