package com.dusan.game.gameinvadersthgame.main;

import java.util.LinkedList;
import java.util.Random;

import com.dusan.game.gameinvadersthgame.gameobjects.Alien;
import com.dusan.game.gameinvadersthgame.gameobjects.AlienBullet;
import com.dusan.game.gameinvadersthgame.gameobjects.Barrier;
import com.dusan.game.gameinvadersthgame.gameobjects.Bullet;
import com.dusan.game.gameinvadersthgame.gameobjects.FlyingSaucer;
import com.dusan.game.gameinvadersthgame.gameobjects.GameObject;
import com.dusan.game.gameinvadersthgame.gameobjects.Player;
import com.dusan.game.gameinvadersthgame.gameobjects.PlayerBullet;

public class CollisionManager {

	private static CollisionManager instance;
	private CollisionManager(){}
	public static CollisionManager getInstance(){
		if(instance == null){
			instance = new CollisionManager();
		}
		return instance;
	}
	
	public void tick(){
		LinkedList<GameObject> allObjects = GameObjectManager.getAllObjects();
		for(int i = 0; i < GameObjectManager.allObjectsSize(); i++){
			GameObject currentObject = allObjects.get(i);
			for(int j = i + 1; j < allObjects.size(); j++){
				if(intersects(currentObject, allObjects.get(j))){
					GameObject otherObject = allObjects.get(j);
					if((currentObject instanceof Alien && otherObject instanceof PlayerBullet) || (otherObject instanceof Alien && currentObject instanceof PlayerBullet)){
						if(currentObject instanceof Alien){
							GameObjectManager.getPoints((Alien)currentObject);
						}
						else{
							GameObjectManager.getPoints((Alien)otherObject);
						}
						GameObjectManager.removeObject(currentObject);
						GameObjectManager.removeObject(otherObject);
					}
					else if((currentObject instanceof FlyingSaucer && otherObject instanceof PlayerBullet) || (currentObject instanceof PlayerBullet && otherObject instanceof FlyingSaucer)){
						if(currentObject instanceof FlyingSaucer){
							GameObjectManager.getPoints((FlyingSaucer)currentObject);
						}
						else{
							GameObjectManager.getPoints((FlyingSaucer)otherObject);
						}
						GameObjectManager.removeObject(currentObject);
						GameObjectManager.removeObject(otherObject);
					}
					else if((currentObject instanceof Barrier && otherObject instanceof Bullet) || (currentObject instanceof Bullet && otherObject instanceof Barrier)){
						if(currentObject instanceof Barrier){
							((Barrier)currentObject).takeDamage();
							GameObjectManager.removeObject(otherObject);
						}
						else{
							((Barrier)otherObject).takeDamage();
							GameObjectManager.removeObject(currentObject);
						}
					}
					else if((currentObject instanceof Player && otherObject instanceof AlienBullet) || (currentObject instanceof AlienBullet && otherObject instanceof Player)){
						GameObjectManager.killPlayer();
						if(currentObject instanceof AlienBullet){
							GameObjectManager.removeObject(currentObject);
						}
						else{
							GameObjectManager.removeObject(otherObject);
						}
					}
					else if((currentObject instanceof PlayerBullet && otherObject instanceof AlienBullet) || (currentObject instanceof AlienBullet && otherObject instanceof PlayerBullet)){
						GameObjectManager.removeObject(currentObject);
						GameObjectManager.removeObject(otherObject);
					}
					else if(currentObject instanceof FlyingSaucer && otherObject instanceof FlyingSaucer){
						
						Random random = new Random();
						int minX = Math.min(currentObject.getX(), otherObject.getX());
						int maxX = Math.max(currentObject.getX(), otherObject.getX());
						if(minX > 0 && maxX < Game.getInstance().width){
							for(int k = 0; k < 50; k++){
								int val = random.nextInt(maxX - minX);
								int velX = 0;
								int velY = 0;
//								Makes sure the velY isn't 0
								while(velY == 0){
									velX = random.nextInt(11) - 5;
									velY = (int) Math.sqrt(Math.pow(5, 2) - Math.pow(velX, 2));
								}
								try {
									GameObjectManager.makeAlienBulletWithVelocity((minX + val), currentObject.getY() + currentObject.getHeight(), velX, velY);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
						
						GameObjectManager.removeObject(currentObject);
						GameObjectManager.removeObject(otherObject);
						
					}
				}
			}
		}
	}
	
	private boolean intersects(GameObject firstObject, GameObject secondObject){
		int firstX = firstObject.getX();
		int firstY = firstObject.getY();
		int secondX = secondObject.getX();
		int secondY = secondObject.getY();		
		
		if(!(firstX + firstObject.getWidth() < secondX || secondX + secondObject.getWidth() < firstX || firstY + firstObject.getHeight() < secondY || secondY + secondObject.getHeight() < firstY)){
			return true;
		}
		else{
			return false;
		}
		
		
	}

}
