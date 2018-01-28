package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.ID;

public class AlienBulletFactory extends BulletFactory{

	@Override
	protected GameObject createObject(int x, int y, ID id) {
		return new AlienBullet(x, y, id);
	}

}
