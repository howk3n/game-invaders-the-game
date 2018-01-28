package com.dusan.game.gameinvadersthgame.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.ID;
import com.dusan.game.gameinvadersthgame.gameobjects.Alien;
import com.dusan.game.gameinvadersthgame.gameobjects.Alien.AlienMove;
import com.dusan.game.gameinvadersthgame.gameobjects.AlienBulletFactory;
import com.dusan.game.gameinvadersthgame.gameobjects.BarrierFactory;
import com.dusan.game.gameinvadersthgame.gameobjects.BasicAlienFactory;
import com.dusan.game.gameinvadersthgame.gameobjects.FlyingSaucer;
import com.dusan.game.gameinvadersthgame.gameobjects.FlyingSaucerFactory;
import com.dusan.game.gameinvadersthgame.gameobjects.GameObject;
import com.dusan.game.gameinvadersthgame.gameobjects.MediorAlienFactory;
import com.dusan.game.gameinvadersthgame.gameobjects.PlayerBulletFactory;
import com.dusan.game.gameinvadersthgame.gameobjects.PlayerFactory;
import com.dusan.game.gameinvadersthgame.gameobjects.SeniorAlienFactory;

class AlienPositionValues{
	public AlienPositionValues(){
		setBaseline();
	}
	
	public AlienPositionValues(int minX, int maxX, int minY, int maxY){
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
	}
	public int minX;
	public int maxX;
	public int minY;
	public int maxY;
	
	public AlienPositionValues getPositionValues(int minX, int maxX, int minY, int maxY){
		return new AlienPositionValues(minX, maxX, minY, maxY);
	}
	
	public void newValues(int x, int y){
//		System.out.println("Old left most position: "+ minX);
//		System.out.println("Old right most position: "+ maxX);
//		System.out.println("Old bottom most position: "+ maxY);
		this.minX = Math.min(this.minX, x);
		this.maxX = Math.max(this.maxX, x);
		this.minY = Math.min(this.minY, y);
		this.maxY = Math.max(this.maxY, y);
//		System.out.println("New left most position: "+ minX);
//		System.out.println("New Right most position: "+ maxX);
//		System.out.println("New bottom most position: "+ maxY);
	}
	
	public void setBaseline(){
		this.minX = 9999;
		this.maxX = 0;
		this.minY = 9999;
		this.maxY = 0;
	}
}

public class Handler {

	private Handler(){
		// nope
	}

	private static AlienPositionValues alienPositions;
	
	private static LinkedList<GameObject> allObjects;
	private static PlayerFactory pf;
	private static BasicAlienFactory baf;
	private static MediorAlienFactory maf;
	private static SeniorAlienFactory saf;
	private static FlyingSaucerFactory fcf;
	private static AlienBulletFactory abf;
	private static PlayerBulletFactory pbf;
	private static BarrierFactory barf;
	private static long moveTimer;
	private static long shootTimer;
	private static AlienMove alienDirection;
	private static ArrayList<Alien> lowestAliens;
	private static Random randomGenerator;
	private static boolean alienMove;
	private static boolean alienShoot;
	private static boolean playerHasBullet;
	
	private static long current;
	private static long previous;
	
	public static void init() {
		
		allObjects = new LinkedList<GameObject>();
		alienPositions = new AlienPositionValues();
		
		allObjects = new LinkedList<GameObject>();
		pf = new PlayerFactory();
		baf = new BasicAlienFactory();
		maf = new MediorAlienFactory();
		saf = new SeniorAlienFactory();
		fcf = new FlyingSaucerFactory();
		abf = new AlienBulletFactory();
		pbf = new PlayerBulletFactory();
		barf = new BarrierFactory();
		moveTimer = 0;
		shootTimer = 0;
		alienDirection = AlienMove.RIGHT;
		lowestAliens = new ArrayList<Alien>();
		randomGenerator = new Random();
		alienMove = false;
		alienShoot = false;
		playerHasBullet = false;
		
		current = 0;
		previous = 0;
		
	}
	
	private static ArrayList<Alien> getLowestAliens(){
		ArrayList<Alien> lowestAliens = new ArrayList<Alien>();
		int columnIndex;
		for(int i = 0; i < allObjects.size(); i++){
			if(allObjects.get(i) instanceof Alien){
				columnIndex = (allObjects.get(i).getX() - alienPositions.minX)/40;
				lowestAliens.add(null);
				if(lowestAliens.get(columnIndex) == null){
					lowestAliens.set(columnIndex, (Alien)allObjects.get(i));
				} else if (lowestAliens.get(columnIndex).getY()<allObjects.get(i).getY()){
					lowestAliens.set(columnIndex, (Alien)allObjects.get(i));
				}
			}
		}
		return lowestAliens;
	}
	
	public static void initLevel(int level){
		if(level == 1){
			makeObject(0, 0, ID.Player);
			for(int i = 0; i < Constants.NUMBER_OF_BARRIERS; i++){
				makeObject(70 + (2*i*70), Game.HEIGHT - 160, ID.Barrier);
			}
		}
		
		int x = 0;
		int y = 0;
		int alienRows = Constants.ALIENS_PER_LEVEL[level]/11;
		for(int i = 0; i<Constants.ALIENS_PER_LEVEL[level] / alienRows; i++){
			for(int j = 0; j<alienRows; j++){
				x = 100 + ((Constants.BASIC_ALIEN_WIDTH + 10) * i);
				y = 40 + ((j+1)*50);
				if(Constants.SENIOR_ALIENS_PER_LEVEL[level]/11 >= j+1){
					makeObject(x+2,y,ID.SeniorAlien);
				} else if(Constants.MEDIOR_ALIENS_PER_LEVEL[level]/11 > j - Constants.SENIOR_ALIENS_PER_LEVEL[level]/11){
					makeObject(x+1, y, ID.MediorAlien);
				} else{
					makeObject(x,y,ID.BasicAlien);
				}
			}
		}
	}
	
	public static void tick(){
		
		previous = current;
		current = System.currentTimeMillis();
		
//		System.out.println(current - previous);
//		System.out.println("movetimer : " + moveTimer);
		
		if(moveTimer >=1000){
			alienMove = true;
			moveTimer = 0;
			if((alienDirection == AlienMove.RIGHT && alienPositions.maxX >= Game.WIDTH - Constants.BASIC_ALIEN_WIDTH - 5) || (alienDirection == AlienMove.LEFT && alienPositions.minX <= 0)){
				alienDirection = AlienMove.DOWN;
			} else if(alienDirection == AlienMove.DOWN){
				if(alienPositions.maxX >= Game.WIDTH - Constants.BASIC_ALIEN_WIDTH - 5){
					alienDirection = AlienMove.LEFT;
				} else if(alienPositions.minX <= 0){
					alienDirection = AlienMove.RIGHT;
				}
			}
		}
		
		if(shootTimer>=3750){
			alienShoot = true;
			shootTimer = 0;
			lowestAliens = getLowestAliens();
		}
		
		alienPositions.setBaseline();
		playerHasBullet = false;
		
		for(int i = 0; i < allObjects.size(); i++){
			
			GameObject tempObject = allObjects.get(i);
			tempObject.tick();
			if(tempObject instanceof Alien){
				
				alienPositions.newValues(tempObject.getX(), tempObject.getY());
				
				if(alienMove){
					((Alien) tempObject).move(alienDirection);
				}
				if(alienShoot){
					
					if(lowestAliens.contains(tempObject) && (1 + randomGenerator.nextInt(5))>4){
						((Alien) tempObject).shoot();
					}
				}
			}
			if(tempObject.getId() == ID.PlayerBullet){
				playerHasBullet = true;
			}
			
		}
		
		if(!alienMove){
			moveTimer += (current - previous);
		}else{
			alienMove = false;
		}
		if(!alienShoot){
			shootTimer += (current - previous);
		}else{
			alienShoot = false;
		}
	}

	public static void render(Graphics g){
		for(int i = 0; i < allObjects.size(); i++){
			GameObject tempObject = allObjects.get(i);
			tempObject.render(g);
		}
	}
	
	public static void getPoints(Alien a){
		HUD.incrementScore(a.points);
	}
	public static void getPoints(FlyingSaucer fs){
		HUD.incrementScore(fs.points);
	}
	
	public static void makeObject(int x, int y, ID id){
		if(id == ID.Player){
			addObject(pf.getObject(x, y, id));
		}
		if(id == ID.BasicAlien){
			addObject(baf.getObject(x, y, id));
		}
		if(id == ID.MediorAlien){
			addObject(maf.getObject(x, y, id));
		}
		if(id == ID.SeniorAlien){
			addObject(saf.getObject(x, y, id));
		}
		if(id == ID.FlyingSaucer){
			addObject(fcf.getObject(x, y, id));
		}
		if(id == ID.PlayerBullet){
			addObject(pbf.getObject(x, y, id));
		}
		if(id == ID.AlienBullet){
			addObject(abf.getObject(x, y, id));
		}
		if(id == ID.Barrier){
			addObject(barf.getObject(x, y, id));
		}
	}
	
	public static void addObject(GameObject o){
		allObjects.add(o);
	}
	public static void removeObject(GameObject o){
		allObjects.remove(o);
	}
	public static int allObjectsSize() {
		return allObjects.size();
	}
	public static GameObject getObjectAt(int i) {
		return allObjects.get(i);
	}
	public static boolean playerHasBullet(){
		return playerHasBullet;
	}
	
}
