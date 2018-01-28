package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.ID;

public abstract class Alien extends GameObject implements CanShoot {
	
	public enum AlienMove {DOWN, LEFT, RIGHT };
	
	private float movementCooldown;
	public int points;
	
	public Alien(int x, int y, ID id) {
		super(x, y, id);
		this.height = Constants.ALIEN_HEIGHT;
		this.color = Constants.ALIEN_COLOR;
		this.movementCooldown = movementCooldown;
	}
	
	public float getMovementCooldown() {
		return movementCooldown;
	}
	public void setMovementCooldown(float movementCooldown) {
		this.movementCooldown = movementCooldown;
	}
	
	@Override
	public void tick(){
		x+=velX;
		y+=velY;
	}
	
	public void move(AlienMove direction){
		if(direction == AlienMove.DOWN){
			this.y+=20;
		}
		else if(direction == AlienMove.LEFT){
			this.x-=5;
		}else if(direction == AlienMove.RIGHT){
			this.x+=5;
		} else{}
	}

}
