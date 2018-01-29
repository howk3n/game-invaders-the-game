package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.GOID;

public class PlayerBulletFactory extends BulletFactory {

	@Override
	protected GameObject createObject(int x, int y, GOID id) {
		return new PlayerBullet(x, y, id);
	}

}
