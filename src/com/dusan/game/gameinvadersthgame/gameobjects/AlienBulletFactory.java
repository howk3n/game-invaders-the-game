package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.GOID;

public class AlienBulletFactory extends BulletFactory{

	@Override
	protected GameObject createObject(int x, int y, GOID id) {
		return new AlienBullet(x, y, id);
	}

}
