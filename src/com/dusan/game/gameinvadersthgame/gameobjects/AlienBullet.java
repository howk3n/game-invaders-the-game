package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.ID;
import com.dusan.game.gameinvadersthgame.main.Game;
import com.dusan.game.gameinvadersthgame.main.Handler;

public class AlienBullet extends Bullet{

	public AlienBullet(int x, int y, ID id) {
		super(x, y, id);
		this.velY = 3;
		this.color = Constants.ALIEN_BULLET_COLOR;
		this.width = Constants.ALIEN_BULLET_WIDTH;
		this.height = Constants.ALIEN_BULLET_HEIGHT;
	}
	
	@Override
	public void tick(){
		x+=velX;
		y+=velY;
		if(y >= Game.HEIGHT){
			Handler.removeObject(this);
		}
		
		x = Game.clamp(x, 0, Game.WIDTH);
		
		collision();
	}
	
	@Override
	protected void collision(){
		for(int i = 0; i < Handler.allObjectsSize(); i++){
			GameObject tempObject = Handler.getObjectAt(i);
			if(tempObject instanceof Player){
				if(getBounds().intersects(tempObject.getBounds())){
					Handler.removeObject(tempObject);
					Handler.removeObject(this);
				}
			}
			if(tempObject instanceof Barrier){
				if(getBounds().intersects(tempObject.getBounds())){
					((Barrier)tempObject).takeDamage();
					Handler.removeObject(this);
				}
			}
		}
	}


}
