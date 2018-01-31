package com.dusan.game.gameinvadersthgame.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import com.dusan.game.gameinvadersthgame.gameobjects.Player;

public class KeyInput extends KeyAdapter{	
	
	private static KeyInput instance;
	private KeyInput(){	}
	public static KeyInput getInstance(){
		if(instance == null){
			instance = new KeyInput();
			instance.keysPressed = new HashMap<Integer, Boolean>();
			instance.keysPressed.put(KeyEvent.VK_LEFT, false);
			instance.keysPressed.put(KeyEvent.VK_RIGHT, true);
		}
		return instance;
	}
	
	private HashMap<Integer, Boolean> keysPressed;
	private Player player;
	
	public void keyPressed(KeyEvent e){
		if(!(GameObjectManager.getPlayer()==null || GameObjectManager.getPlayer().isDying)){
			player = GameObjectManager.getPlayer();
			int keyCode = e.getKeyCode();
			keysPressed.put(keyCode, true);
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
		
		
		
	}
	
	public void keyReleased(KeyEvent e){
		if(GameObjectManager.getPlayer()!=null || !GameObjectManager.getPlayer().isDying){
			player = GameObjectManager.getPlayer();
			int keyCode = e.getKeyCode();
			if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT){
				
				keysPressed.put(keyCode, false);
				if(keyCode == KeyEvent.VK_LEFT && keysPressed.get(KeyEvent.VK_RIGHT)){
					player.move(Player.PlayerMove.RIGHT);
				}
				else if(keyCode == KeyEvent.VK_RIGHT && keysPressed.get(KeyEvent.VK_LEFT)){
					player.move(Player.PlayerMove.LEFT);
				} 
				else{
					player.stop();
				}
				
			}
			
		}

	}
	
	
	
}
