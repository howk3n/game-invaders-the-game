package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.GOID;
import com.dusan.game.gameinvadersthgame.common.Math;
import com.dusan.game.gameinvadersthgame.main.Game;
import com.dusan.game.gameinvadersthgame.main.GameObjectManager;

public class PlayerBullet extends Bullet {

	public PlayerBullet(int x, int y, GOID id) {
		super(x, y, id);
		this.velY = -5;
		this.color = Constants.PLAYER_BULLET_COLOR;
		this.width = Constants.PLAYER_BULLET_WIDTH;
		this.height = Constants.PLAYER_BULLET_HEIGHT;
	}
	
	@Override
	public void tick(){
		x+=velX;
		y+=velY;
		if(y <= Constants.HUD_HEIGHT){
			try {
				GameObjectManager.makeObject(this.getX(), this.getY(), GOID.AlienBullet);
			} catch (Exception e) {
				e.printStackTrace();
			}
			GameObjectManager.removeObject(this);
		}
		
		x = Math.clamp(x, 0, Game.WIDTH);
		
	}
	

}
