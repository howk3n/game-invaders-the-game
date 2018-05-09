package com.dusan.game.gameinvadersthegame.gameobjects;

import com.dusan.game.gameinvadersthegame.common.GOID;

public class PlayerBulletFactory extends BulletFactory {

	@Override
	protected GameObject createObject(int x, int y, GOID id) {
		return new PlayerBullet(x, y, id);
	}

}
