package com.dusan.game.gameinvadersthgame.gameobjects;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.GOID;

public class FlyingSaucer extends GameObject{

	public int points;
	
	public FlyingSaucer(int x, int y, GOID id) {
		super(x, y, id);
		this.width = Constants.FLYING_SAUCER_WIDTH;
		this.height = Constants.FLYING_SAUCER_HEIGHT;
		this.color = Constants.FLYING_SAUCER_COLOR;
		this.points = 100;
	}

	@Override
	public String toString() {
		return "Large Enemy\nX position: "+x+"\nY position: "+y+"\nWidth: "+width+"\nHeight: "+height+"\nID: "+id;
	}


}
