package com.dusan.game.gameinvadersthegame.gameobjects;

import java.util.Random;

import com.dusan.game.gameinvadersthegame.common.Constants;
import com.dusan.game.gameinvadersthegame.common.GOID;
import com.dusan.game.gameinvadersthegame.common.MyMath;
import com.dusan.game.gameinvadersthegame.main.Game;
import com.dusan.game.gameinvadersthegame.main.GameObjectManager;

public class FlyingSaucer extends GameObject{

	public static enum Direction {LEFT, RIGHT};
	
	public int points;
	private Direction direction;

	public FlyingSaucer(int x, int y, GOID id) {
		super(x, y, id);
		this.direction = randomizeDirection();
		if(this.getDirection() == Direction.LEFT){
			this.x = Game.getInstance().width + Constants.FLYING_SAUCER_WIDTH;
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
		
		if((velX >= 0 && x >= Game.getInstance().width) || (velX <=0 && x <= 0 - this.width)){
			GameObjectManager.removeObject(this);
		}
		y = MyMath.clamp(y, 0, Game.getInstance().height - 64);
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
