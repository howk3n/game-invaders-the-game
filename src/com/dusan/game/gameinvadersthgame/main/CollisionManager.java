package com.dusan.game.gameinvadersthgame.main;

import java.util.LinkedList;

import com.dusan.game.gameinvadersthgame.gameobjects.Alien;
import com.dusan.game.gameinvadersthgame.gameobjects.AlienBullet;
import com.dusan.game.gameinvadersthgame.gameobjects.Barrier;
import com.dusan.game.gameinvadersthgame.gameobjects.Bullet;
import com.dusan.game.gameinvadersthgame.gameobjects.FlyingSaucer;
import com.dusan.game.gameinvadersthgame.gameobjects.GameObject;
import com.dusan.game.gameinvadersthgame.gameobjects.Player;
import com.dusan.game.gameinvadersthgame.gameobjects.PlayerBullet;

public class CollisionManager {
	
	private static enum ObjectTypes { PLAYER, ALIEN, FLYINGSAUCER, BARRIER, PLAYERBULLET, ALIENBULLET, BULLET };

	private static CollisionManager instance;
	private CollisionManager(){}
	public static CollisionManager getInstance(){
		if(instance == null){
			instance = new CollisionManager();
		}
		return instance;
	}
	
	public void doCollision(GameObject currentObject, LinkedList<GameObject> allObjects, int position){
		for(int j = position + 1; j < allObjects.size(); j++){
			if(currentObject.getBounds().intersects(allObjects.get(j).getBounds())){
				GameObject otherObject = allObjects.get(j);
				if(checkIf(currentObject, otherObject, ObjectTypes.ALIEN, ObjectTypes.PLAYERBULLET)){
					if(currentObject instanceof Alien){
						GameObjectManager.getPoints((Alien)currentObject);
					}
					else{
						GameObjectManager.getPoints((Alien)otherObject);
					}
					GameObjectManager.removeObject(currentObject);
					GameObjectManager.removeObject(otherObject);
				}
				else if(checkIf(currentObject, otherObject, ObjectTypes.FLYINGSAUCER, ObjectTypes.PLAYERBULLET)){
					if(currentObject instanceof FlyingSaucer){
						GameObjectManager.getPoints((FlyingSaucer)currentObject);
					}
					else{
						GameObjectManager.getPoints((FlyingSaucer)otherObject);
					}
					GameObjectManager.removeObject(currentObject);
					GameObjectManager.removeObject(otherObject);
				}
				else if(checkIf(currentObject, otherObject, ObjectTypes.BARRIER, ObjectTypes.BULLET)){
					if(currentObject instanceof Barrier){
						((Barrier)currentObject).takeDamage();
						GameObjectManager.removeObject(otherObject);
					}
					else{
						((Barrier)otherObject).takeDamage();
						GameObjectManager.removeObject(currentObject);
					}
				}
				else if(checkIf(currentObject, otherObject, ObjectTypes.PLAYER, ObjectTypes.ALIENBULLET)){
					GameObjectManager.killPlayer();
					if(currentObject instanceof AlienBullet){
						GameObjectManager.removeObject(currentObject);
					}
					else{
						GameObjectManager.removeObject(otherObject);
					}
				}
				else if(checkIf(currentObject, otherObject, ObjectTypes.BULLET, ObjectTypes.BULLET)){
					GameObjectManager.removeObject(currentObject);
					GameObjectManager.removeObject(otherObject);
				}
			}
		}
	}


	private boolean checkIf(GameObject currentObject, GameObject otherObject, ObjectTypes first, ObjectTypes second){
		boolean result = false;
		
		if((first == ObjectTypes.ALIEN && second == ObjectTypes.PLAYERBULLET) || (first == ObjectTypes.PLAYERBULLET && second == ObjectTypes.ALIEN)){
			if((currentObject instanceof Alien && otherObject instanceof PlayerBullet) || (currentObject instanceof PlayerBullet && otherObject instanceof Alien)){
				result = true;
			}
		}
		else if((first == ObjectTypes.FLYINGSAUCER && second == ObjectTypes.PLAYERBULLET) || (first == ObjectTypes.PLAYERBULLET && second == ObjectTypes.FLYINGSAUCER)){
			if((currentObject instanceof FlyingSaucer && otherObject instanceof PlayerBullet) || (currentObject instanceof PlayerBullet && otherObject instanceof FlyingSaucer)){
				result = true;
			}
		}
		else if((first == ObjectTypes.BARRIER && second == ObjectTypes.BULLET) || (first == ObjectTypes.BULLET && second == ObjectTypes.BARRIER)){
			if((currentObject instanceof Barrier && otherObject instanceof Bullet) || (currentObject instanceof Bullet && otherObject instanceof Barrier)){
				result = true;
			}
		}
		else if((first == ObjectTypes.PLAYER && second == ObjectTypes.ALIENBULLET) || (first == ObjectTypes.ALIENBULLET && second == ObjectTypes.PLAYER)){
			if((currentObject instanceof Player && otherObject instanceof AlienBullet) || (currentObject instanceof AlienBullet && otherObject instanceof Player)){
				result = true;
			}
		}
		else if(first == ObjectTypes.BULLET && second == ObjectTypes.BULLET){
			if(currentObject instanceof Bullet && otherObject instanceof Bullet){
				result = true;
			}
		}
		
		return result;
	}
}
