package com.dusan.game.gameinvadersthegame.gameobjects;

import com.dusan.game.gameinvadersthegame.common.Constants;
import com.dusan.game.gameinvadersthegame.common.GOID;

public class MediorAlien extends Alien{

	public MediorAlien(int x, int y, GOID id) {
		super(x, y, id);
		this.width = Constants.ALIEN_WIDTH[Constants.MEDIOR];
		this.points = 20;
	}

	@Override
	public String toString() {
		return "Basic Enemy\nX position: "+x+"\nY position: "+y+"\nWidth: "+width+"\nHeight: "+height+"\nID: "+id;
	}

}
