package com.dusan.game.gameinvadersthgame.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.dusan.game.gameinvadersthgame.main.Game.STATE;

public class MouseInput implements MouseListener{
	
	private static MouseInput instance;
	private MouseInput(){}
	
	public static MouseInput getInstance(){
		if(instance == null){
			instance = new MouseInput();
		}
		return instance;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(Game.getInstance().state == STATE.MENU){
			int mx = e.getX();
			int my = e.getY();
			
			for(int i = 0; i < Menu.buttons.length; i++){
				if(mx >= Menu.buttons[i].x && mx <= Menu.buttons[i].x + Menu.buttons[i].width && my >= Menu.buttons[i].y && my <= Menu.buttons[i].y + Menu.buttons[i].height){
					switch(i){
					case 0:
						Game.getInstance().startGame();
						break;
					case 2:
						System.exit(1);
					case 1:
					default:
						break;
					}
					
				}
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}

}
