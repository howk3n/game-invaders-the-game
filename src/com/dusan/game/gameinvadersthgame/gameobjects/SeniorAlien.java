package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.GOID;

public class SeniorAlien extends Alien {
	public SeniorAlien(int x, int y, GOID id) {
		super(x, y, id);
		this.width = Constants.ALIEN_WIDTH[Constants.SENIOR];
		this.points = 40;
	}

	@Override
	public String toString() {
		return "Basic Enemy\nX position: "+x+"\nY position: "+y+"\nWidth: "+width+"\nHeight: "+height+"\nID: "+id;
	}

}
