package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.GOID;

public class JuniorAlien extends Alien{
	
	public JuniorAlien(int x, int y, GOID id) {
		super(x, y, id);
		this.width = Constants.ALIEN_WIDTH[Constants.JUNIOR];
		this.points = 10;
	}

	@Override
	public String toString() {
		return "Basic Enemy\nX position: "+x+"\nY position: "+y+"\nWidth: "+width+"\nHeight: "+height+"\nID: "+id;
	}

	


}
