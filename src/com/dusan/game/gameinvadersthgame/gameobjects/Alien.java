package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.GOID;
import com.dusan.game.gameinvadersthgame.main.GameObjectManager;

public abstract class Alien extends GameObject {
	
	public enum AlienMove {DOWN, LEFT, RIGHT, NONE };
	
	public int points;
	public boolean shouldShoot;
	public AlienMove shouldMoveInDirection;
	private int columnIndex;
	
	public Alien(int x, int y, GOID id) {
		super(x, y, id);
		this.height = Constants.ALIEN_HEIGHT;
		this.color = Constants.ALIEN_COLOR;
		this.shouldShoot = false;
		this.shouldMoveInDirection = AlienMove.NONE;
	}

	@Override
	public void tick(){

		if(this.shouldMoveInDirection != AlienMove.NONE){
			try {
				this.move(shouldMoveInDirection);
				this.shouldMoveInDirection = AlienMove.NONE;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(this.shouldShoot){
			this.shoot();
			this.shouldShoot = false;
		}
		
	}
	
	protected void move(AlienMove direction) throws Exception{
		if(direction == AlienMove.DOWN){
			this.y+=Constants.ALIEN_VERTICAL_SPEED;
		}
		else if(direction == AlienMove.LEFT){
			this.x-=Constants.ALIEN_HORIZONTAL_SPEED;
		}else if(direction == AlienMove.RIGHT){
			this.x+=Constants.ALIEN_HORIZONTAL_SPEED;
		}else{
			throw new Exception("Alien movement not valid. Aliens may only move down, left or right.");
		}
	}
	
	protected void shoot() {
		
		try {
			GameObjectManager.makeObject(this.getX() + this.getWidth()/2, this.getY()+this.getHeight(), GOID.AlienBullet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

}
