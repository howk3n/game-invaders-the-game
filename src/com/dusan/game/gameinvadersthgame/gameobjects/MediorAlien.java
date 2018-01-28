package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.ID;
import com.dusan.game.gameinvadersthgame.main.Handler;

public class MediorAlien extends Alien{

	public MediorAlien(int x, int y, ID id) {
		super(x, y, id);
		this.width = Constants.MEDIOR_ALIEN_WIDTH;
		this.points = 20;
	}

	@Override
	public String toString() {
		return "Basic Enemy\nX position: "+x+"\nY position: "+y+"\nWidth: "+width+"\nHeight: "+height+"\nID: "+id;
	}

	@Override
	public void shoot() {
		
		Handler.makeObject(this.getX() + this.getWidth()/2, this.getY()+this.getHeight(), ID.AlienBullet);
		
	}


}
