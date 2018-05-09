package com.dusan.game.gameinvadersthegame.gameobjects;

import com.dusan.game.gameinvadersthegame.common.GOID;

public class AlienBulletFactory extends BulletFactory{

	@Override
	protected GameObject createObject(int x, int y, GOID id) {
		return new AlienBullet(x, y, id);
	}

}
