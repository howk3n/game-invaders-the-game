package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.ID;
import com.dusan.game.gameinvadersthgame.main.Handler;

public class Player extends GameObject implements CanShoot {
	
	public enum PlayerMove { LEFT, RIGHT };
	private static Player instance;
	public static int lives;
	
	protected Player(int x, int y, ID id) {
		super(x, y, id);
		this.width = Constants.DEFAULT_PLAYER_WIDTH;
		this.height = Constants.DEFAULT_PLAYER_HEIGHT;
		this.color = Constants.DEFAULT_PLAYER_COLOR;
	}
	
	public static Player getInstance(){
		if(instance == null){
			instance = new Player(Constants.DEFAULT_BASIC_BARRIER_WIDTH + ((Constants.DEFAULT_BASIC_BARRIER_WIDTH-Constants.DEFAULT_PLAYER_WIDTH)/2), Constants.DEFAULT_SCREEN_HEIGHT - Constants.DEFAULT_PLAYER_HEIGHT * 2, ID.Player);
		}
		return instance;
	}

	@Override
	public String toString() {
		return "Player\nX position: "+x+"\nY position: "+y+"\nWidth: "+width+"\nHeight: "+height+"\nID: "+id;
	}
	
	public void move(PlayerMove direction){		
		if(direction == PlayerMove.LEFT){
			setVelX(-5);
		}
		else{
			setVelX(5);
		}
	}
	
	public void stop(){
		setVelX(0);
	}

	@Override
	public void shoot() {
		if(Handler.playerHasBullet()==false){
			Handler.makeObject(this.getX() + this.getWidth()/2, this.getY(), ID.PlayerBullet);
		}
		
	}

}
