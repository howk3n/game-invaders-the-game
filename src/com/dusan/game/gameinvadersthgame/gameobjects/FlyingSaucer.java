package com.dusan.game.gameinvadersthgame.gameobjects;

import java.util.Random;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.GOID;
import com.dusan.game.gameinvadersthgame.common.MyMath;
import com.dusan.game.gameinvadersthgame.main.Game;
import com.dusan.game.gameinvadersthgame.main.GameObjectManager;

public class FlyingSaucer extends GameObject{

	public static enum Direction {LEFT, RIGHT};
	
	public int points;
	private Direction direction;

	public FlyingSaucer(int x, int y, GOID id) {
		super(x, y, id);
		this.direction = randomizeDirection();
		if(this.getDirection() == Direction.LEFT){
			this.x = Game.WIDTH + Constants.FLYING_SAUCER_WIDTH;
			this.velX = -1 * Constants.FLYING_SAUCER_BASE_VELOCITY;
		}
		else{
			this.x = 0 - Constants.FLYING_SAUCER_WIDTH;
			this.velX = Constants.FLYING_SAUCER_BASE_VELOCITY;
		}
		this.width = Constants.FLYING_SAUCER_WIDTH;
		this.height = Constants.FLYING_SAUCER_HEIGHT;
		this.color = Constants.FLYING_SAUCER_COLOR;
		this.points = 100;
	}

	@Override
	public String toString() {
		return "Large Enemy\nX position: "+x+"\nY position: "+y+"\nWidth: "+width+"\nHeight: "+height+"\nID: "+id;
	}
	
	@Override
	public void tick(){
		x+=velX;
		y+=velY;
		
		if((velX >= 0 && x >= Game.WIDTH) || (velX <=0 && x <= 0 - this.width)){
			GameObjectManager.removeObject(this);
		}
		y = MyMath.clamp(y, 0, Game.HEIGHT - 64);
	}
	
	private Direction randomizeDirection(){
		if(new Random().nextInt(2) == 0){
			return Direction.LEFT;
		}
		else {
			return Direction.RIGHT;
		}
	}
	
	public Direction getDirection() {
		return direction;
	}


}
