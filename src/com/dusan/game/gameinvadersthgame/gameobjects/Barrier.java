package com.dusan.game.gameinvadersthgame.gameobjects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.ID;
import com.dusan.game.gameinvadersthgame.main.Handler;

public class Barrier extends GameObject{

	private int health;
	private float alpha;
	
	public Barrier(int x, int y, ID id) {
		super(x, y, id);
		this.width = Constants.DEFAULT_BASIC_BARRIER_WIDTH;
		this.height = Constants.DEFAULT_BASIC_BARRIER_HEIGHT;
		this.color = Constants.DEFAULT_PLAYER_COLOR;
		this.health = 50;
		this.alpha = 1.0f;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void render(Graphics g){
		if(health <=0){
			Handler.removeObject(this);
		}
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(this.color);
		alpha = 1.0f - ((50 - health) * 0.01f);
		AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		g2d.setComposite(alcom);
		g2d.fillRect(x, y, this.width, this.height);
		g2d.setColor(Color.BLACK);
		g2d.drawString(""+this.health, this.x + this.width/2 - 7, this.y + this.height/2 + 4);
	}

	
	public void takeDamage(){
		health--;
	}

}
