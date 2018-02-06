package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.GOID;
import com.dusan.game.gameinvadersthgame.main.Game;
import com.dusan.game.gameinvadersthgame.main.GameObjectManager;

public class Player extends GameObject {
	
	public enum PlayerMove { LEFT, RIGHT };
	public static int lives;
	public boolean isDying;
	private int speed;
	
	protected Player(int x, int y, GOID id) {
		super(x, y, id);
		this.width = Constants.DEFAULT_PLAYER_WIDTH;
		this.height = Constants.DEFAULT_PLAYER_HEIGHT;
		this.color = Constants.DEFAULT_PLAYER_COLOR;
		this.speed = 5;
		this.isDying = false;
	}

	@Override
	public String toString() {
		return "Player\nX position: " + x + "\nY position: " + y + "\nWidth: " + width + "\nHeight: " + height + "\nID: " + id;
	}
	
	public void move(PlayerMove direction){		
		if(direction == PlayerMove.LEFT){
			this.setVelX(-1 * speed);
		}
		else{
			this.setVelX(speed);
		}
	}
	
	public void stop(){
		this.setVelX(0);
	}

	public void shoot() {
		if(GameObjectManager.playerHasBullet()==false){
			try {
				GameObjectManager.makeObject(this.getX() + this.getWidth()/2, this.getY(), GOID.PlayerBullet);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void die(){
		this.isDying = true;

		// todo for each gameobject : death animation
		
//		GameObjectManager.removeObject(this);
		System.out.println(this.getX() + ": currentX");
		System.out.println(Game.PLAYER_STARTING_X + ": should be new X");
		this.setX(Game.PLAYER_STARTING_X);
		System.out.println(this.getX() + ": new X");
		
		lives--;
		
		this.isDying = false;
		
		
	}

}
