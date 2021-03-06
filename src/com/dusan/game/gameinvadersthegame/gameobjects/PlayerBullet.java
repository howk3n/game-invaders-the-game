package com.dusan.game.gameinvadersthegame.gameobjects;

import com.dusan.game.gameinvadersthegame.common.Constants;
import com.dusan.game.gameinvadersthegame.common.GOID;
import com.dusan.game.gameinvadersthegame.common.MyMath;
import com.dusan.game.gameinvadersthegame.main.Game;
import com.dusan.game.gameinvadersthegame.main.GameObjectManager;

public class PlayerBullet extends Bullet {

	public PlayerBullet(int x, int y, GOID id) {
		super(x, y, id);
		this.velY = -7;
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
		
		x = MyMath.clamp(x, 0, Game.getInstance().width);
		
	}
	

}
