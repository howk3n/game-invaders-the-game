package com.dusan.game.gameinvadersthegame.gameobjects;

import com.dusan.game.gameinvadersthegame.common.Constants;
import com.dusan.game.gameinvadersthegame.common.GOID;
import com.dusan.game.gameinvadersthegame.main.Game;
import com.dusan.game.gameinvadersthegame.main.GameObjectManager;

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
		if(y >= Game.getInstance().height){
			GameObjectManager.removeObject(this);
		}
		if(x >= Game.getInstance().width || x <= 0){
			velX *= -1;
		}
	}



}
