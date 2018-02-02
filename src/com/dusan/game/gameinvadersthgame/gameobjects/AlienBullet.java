package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.GOID;
import com.dusan.game.gameinvadersthgame.common.Math;
import com.dusan.game.gameinvadersthgame.main.Game;
import com.dusan.game.gameinvadersthgame.main.GameObjectManager;

public class AlienBullet extends Bullet{

	public AlienBullet(int x, int y, GOID id) {
		super(x, y, id);
		this.velY = 5;
		this.color = Constants.ALIEN_BULLET_COLOR;
		this.width = Constants.ALIEN_BULLET_WIDTH;
		this.height = Constants.ALIEN_BULLET_HEIGHT;
	}
	
	@Override
	public void tick(){
		x+=velX;
		y+=velY;
		if(y >= Game.HEIGHT){
			GameObjectManager.removeObject(this);
		}
		x = Math.clamp(x, 0, Game.WIDTH);
	}



}
