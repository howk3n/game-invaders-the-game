package com.dusan.game.gameinvadersthgame.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.dusan.game.gameinvadersthgame.gameobjects.Player;

public class KeyInput extends KeyAdapter{

	// move to Player class
	
	
	private static KeyInput instance;
	private Player player = Player.getInstance();
	private KeyInput(){	}
	public static KeyInput getInstance(){
		if(instance == null){
			instance = new KeyInput();
		}
		return instance;
	}
	
	public void keyPressed(KeyEvent e){
		
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_LEFT){
			player.move(Player.PlayerMove.LEFT);
		}
		if(keyCode == KeyEvent.VK_RIGHT){
			player.move(Player.PlayerMove.RIGHT);
		}
		
		if(keyCode == KeyEvent.VK_SPACE){
			player.shoot();
		}

		if(keyCode == KeyEvent.VK_ESCAPE){
			System.exit(1);
		}
		
	}
	
	public void keyReleased(KeyEvent e){
		
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT){
			player.stop();
		}

	}
	
}
