package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.ID;
import com.dusan.game.gameinvadersthgame.main.Game;
import com.dusan.game.gameinvadersthgame.main.Handler;

public class PlayerBullet extends Bullet {

	public PlayerBullet(int x, int y, ID id) {
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
			Handler.makeObject(this.getX(), this.getY(), ID.AlienBullet);
			Handler.removeObject(this);
		}
		
		x = Game.clamp(x, 0, Game.WIDTH);
		
		collision();
		
	}
	
	@Override
	protected void collision(){
		for(int i = 0; i < Handler.allObjectsSize(); i++){
			GameObject tempObject = Handler.getObjectAt(i);
			if(tempObject instanceof Alien || tempObject instanceof FlyingSaucer){
				if(getBounds().intersects(tempObject.getBounds())){
					if(tempObject instanceof Alien){
						Handler.getPoints((Alien)tempObject);
					} else{
						Handler.getPoints((FlyingSaucer)tempObject);
					}
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
