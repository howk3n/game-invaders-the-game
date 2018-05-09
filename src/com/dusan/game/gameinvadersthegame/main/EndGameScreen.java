package com.dusan.game.gameinvadersthegame.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.dusan.game.gameinvadersthegame.common.MyMath;

public class EndGameScreen{

	private static String name;
	
	public final static int HEADER = 0;
	public final static int SCORE = 1;
	public final static int NAME = 2;
	public final static int SUBMIT = 3;
	
	public final static Rectangle[] RECTANGLES = {
			new Rectangle(),
			new Rectangle(),
			new Rectangle(),
			new Rectangle()
	};
	
	public static void init(){		
		name = "Type your name";
		RECTANGLES[HEADER].setBounds(0, 0, Game.getInstance().width, Game.getInstance().height / 5);
		RECTANGLES[SCORE].setBounds(0, (int)RECTANGLES[HEADER].getHeight(), Game.getInstance().width, Game.getInstance().height / 5);
		RECTANGLES[NAME].setBounds(0, (int)(RECTANGLES[HEADER].getHeight() + RECTANGLES[SCORE].getHeight()), Game.getInstance().width, Game.getInstance().height / 5);
		RECTANGLES[SUBMIT].setBounds(0, (int)(RECTANGLES[HEADER].getHeight() + RECTANGLES[SCORE].getHeight() + RECTANGLES[NAME].getHeight()), Game.getInstance().width, Game.getInstance().height / 5);
	}
	
	public static void type(char c){
		if(name.length() >= 3){
			name = "";
		}
		name += c;
	}
	
	public static void submit(){
		if(!name.equals("") || !name.equals("Type your name")){
			Game.getInstance().saveScore();
		}
	}
	
	public static void render(Graphics g){
		g.setColor(Color.RED);
		g.setFont(new Font("arial", Font.BOLD, 28));
		
		MyMath.drawCenteredString(g, "Game Over", RECTANGLES[HEADER], g.getFont());
		MyMath.drawCenteredString(g, "Your score: " + Game.getInstance().score, RECTANGLES[SCORE], g.getFont());
		MyMath.drawCenteredString(g, name, RECTANGLES[NAME], g.getFont());
		g.fillRect((int)RECTANGLES[SUBMIT].getX(), (int)RECTANGLES[SUBMIT].getY(), (int)RECTANGLES[SUBMIT].getWidth(), (int)RECTANGLES[SUBMIT].getHeight());
		g.setColor(Color.BLACK);
		MyMath.drawCenteredString(g, "Submit", RECTANGLES[SUBMIT], g.getFont());
	}
	
}
