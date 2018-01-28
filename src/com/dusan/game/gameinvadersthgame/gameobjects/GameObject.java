package com.dusan.game.gameinvadersthgame.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.dusan.game.gameinvadersthgame.common.ID;
import com.dusan.game.gameinvadersthgame.main.Game;

public abstract class GameObject{

	protected int x, y, width, height, health, velX, velY;
	protected Color color;
	protected ID id;

	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public void tick(){
		x+=velX;
		y+=velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 72);
		y = Game.clamp(y, 0, Game.HEIGHT - 64);
	}
	
	public void render(Graphics g){
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	};
	
	public abstract String toString();
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
}
