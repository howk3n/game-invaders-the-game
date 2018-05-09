package com.dusan.game.gameinvadersthegame.gameobjects;

import com.dusan.game.gameinvadersthegame.common.Constants;
import com.dusan.game.gameinvadersthegame.common.GOID;

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
