package com.dusan.game.gameinvadersthgame.main;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import com.dusan.game.gameinvadersthgame.common.Constants;
import com.dusan.game.gameinvadersthgame.common.GOID;
import com.dusan.game.gameinvadersthgame.gameobjects.Alien;
import com.dusan.game.gameinvadersthgame.gameobjects.Alien.AlienMove;
import com.dusan.game.gameinvadersthgame.gameobjects.AlienBulletFactory;
import com.dusan.game.gameinvadersthgame.gameobjects.BarrierFactory;
import com.dusan.game.gameinvadersthgame.gameobjects.FlyingSaucer;
import com.dusan.game.gameinvadersthgame.gameobjects.FlyingSaucerFactory;
import com.dusan.game.gameinvadersthgame.gameobjects.GameObject;
import com.dusan.game.gameinvadersthgame.gameobjects.GameObjectFactory;
import com.dusan.game.gameinvadersthgame.gameobjects.JuniorAlienFactory;
import com.dusan.game.gameinvadersthgame.gameobjects.MediorAlienFactory;
import com.dusan.game.gameinvadersthgame.gameobjects.Player;
import com.dusan.game.gameinvadersthgame.gameobjects.PlayerBulletFactory;
import com.dusan.game.gameinvadersthgame.gameobjects.PlayerFactory;
import com.dusan.game.gameinvadersthgame.gameobjects.SeniorAlienFactory;

class AlienPositionValues{
	
	public int minX;
	public int maxX;
	public int minY;
	public int maxY;
	
	public AlienPositionValues(){
		setBaseline();
	}
	
	public void expandValueRanges(int x, int y){
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

public class GameObjectManager {

	private GameObjectManager(){
		// nope
	}

	private static AlienPositionValues alienPositions;
	
	private static LinkedList<GameObject> allObjects;
	private static PlayerFactory pf;
	private static JuniorAlienFactory jaf;
	private static MediorAlienFactory maf;
	private static SeniorAlienFactory saf;
	private static FlyingSaucerFactory fsf;
	private static AlienBulletFactory abf;
	private static PlayerBulletFactory pbf;
	private static BarrierFactory barf;
	private static double gameSpeed;
	private static double moveTimer;
	private static double shootTimer;
	private static double flyingSaucerTimer;
	private static AlienMove alienDirection;
	private static int numberOfAliens;
	private static HashMap<Integer, Alien> lowestAliens;
	private static Random randomGenerator;
	private static boolean alienMove;
	private static boolean alienShoot;
	private static boolean flyingSaucerAppear;
	private static boolean flyingSaucerExists;
	private static boolean playerHasBullet;
	
	public static void init() {
		
		allObjects = new LinkedList<GameObject>();
		alienPositions = new AlienPositionValues();
		
		allObjects = new LinkedList<GameObject>();
		pf = new PlayerFactory();
		jaf = new JuniorAlienFactory();
		maf = new MediorAlienFactory();
		saf = new SeniorAlienFactory();
		fsf = new FlyingSaucerFactory();
		abf = new AlienBulletFactory();
		pbf = new PlayerBulletFactory();
		barf = new BarrierFactory();
		gameSpeed = 1;
		moveTimer = 0;
		shootTimer = 0;
		flyingSaucerTimer = 0;
		alienDirection = AlienMove.RIGHT;
		lowestAliens = new HashMap<Integer, Alien>();
		randomGenerator = new Random();
		alienMove = false;
		alienShoot = false;
		flyingSaucerAppear = false;
		flyingSaucerExists = false;
		playerHasBullet = false;

		
		Player.lives = Constants.PLAYER_STARTING_LIVES;
		
	}
	
	public static void killPlayer(){
		Player player = getPlayer();
		if(Player.lives <= 1){
//			allObjects.clear();
			Game.gameOver();
		}
		else{
			player.die();
			System.out.println("GameObjectManager says die player.");
		}
	}
	
	private static HashMap<Integer, Alien> getLowestAliens(){
		HashMap<Integer, Alien> lowestAliens = new HashMap<Integer, Alien>();
		int columnIndex;
		for(int i = 0; i < allObjectsSize(); i++){
			if(allObjects.get(i) instanceof Alien){
				Alien alien = (Alien)allObjects.get(i);
				columnIndex = (alien.getX() - alienPositions.minX) / (Constants.ALIEN_WIDTH[Constants.JUNIOR] - Constants.ALIEN_WIDTH[Constants.JUNIOR] / 3);
				if(lowestAliens.get(columnIndex) == null || lowestAliens.get(columnIndex).getY()<alien.getY()){
					lowestAliens.put(columnIndex, alien);
				}
			}
		}
		return lowestAliens;
	}
	
	public static Player getPlayer(){
		GameObject tempObject = null;
		for(int i = 0; i < allObjectsSize(); i++){
			if(getObjectAt(i) instanceof Player){
				tempObject = getObjectAt(i);
			}
		}
		return (Player)tempObject;
	}
	
	public static void initLevel(int level){
		if(level == 1){ // 80
			try {
				makeObject(Game.PLAYER_STARTING_X, Game.PLAYER_STARTING_Y, GOID.Player);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			for(int i = 0; i < Constants.NUMBER_OF_BARRIERS; i++){
				try {
					makeObject(Constants.DEFAULT_BASIC_BARRIER_WIDTH + (2*i*Constants.DEFAULT_BASIC_BARRIER_WIDTH), Game.HEIGHT - Constants.HUD_HEIGHT * 2, GOID.Barrier);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		int x = 0;
		int y = 0;
		int alienRows = Constants.getTotalAliensPerLevel(level)/Constants.ALIEN_COLUMN_COUNT;
		for(int i = 0; i < Constants.ALIEN_COLUMN_COUNT; i++){
			for(int j = 0; j<alienRows; j++){
				x = Constants.ALIEN_HORIZONTAL_SPEED * 20 + ((Constants.ALIEN_WIDTH[Constants.JUNIOR] + Constants.ALIEN_HORIZONTAL_SPEED * 2) * i);
				y = Constants.HUD_HEIGHT + Constants.HUD_HEIGHT / 2  + j * (Constants.ALIEN_HEIGHT + Constants.ALIEN_VERTICAL_SPEED);
				if(Constants.ALIENS_PER_LEVEL[level][Constants.SENIOR] / Constants.ALIEN_COLUMN_COUNT >= j + 1){
					try {
						makeObject(x+(Constants.ALIEN_WIDTH[Constants.JUNIOR] - Constants.ALIEN_WIDTH[Constants.SENIOR]) / 2, y, GOID.SeniorAlien);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if(Constants.ALIENS_PER_LEVEL[level][Constants.MEDIOR] / Constants.ALIEN_COLUMN_COUNT > j - Constants.ALIENS_PER_LEVEL[level][Constants.SENIOR] / Constants.ALIEN_COLUMN_COUNT){
					try {
						makeObject(x+(Constants.ALIEN_WIDTH[Constants.JUNIOR] - Constants.ALIEN_WIDTH[Constants.MEDIOR]) / 2, y, GOID.MediorAlien);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else{
					try {
						makeObject(x,y,GOID.JuniorAlien);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				numberOfAliens++;
			}
		}
		
	}
	
	private static void flyingSaucerAppears(){
		int sl;
		if(randomGenerator.nextInt(2) == 1){
			sl = -1;
		}
		else{
			sl = Game.WIDTH / Constants.FLYING_SAUCER_WIDTH;
		}
		try {
			makeObject(sl * Constants.FLYING_SAUCER_WIDTH, Constants.HUD_HEIGHT + Constants.FLYING_SAUCER_HEIGHT / 2, GOID.FlyingSaucer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	public static void tick(){
//		This class should not have the responsibility of keeping time between ticks, that should belong to the Game class.
//		One way to do it it make all tick() methods receive a parameter called deltaTime (which is difference between current and previous tick).
//		Another way (which Unity uses) is implement a static class or singleton called GameTime, with a field called deltaTime. Game class would set that value in each tick,
//		and whoever needs that value can just check the GameTime class.
		
		if(numberOfAliens == 0){
			Game.nextLevel();
		}else{
			gameSpeed = 1 + Game.currentLevel * 0.012 * (Constants.getTotalAliensPerLevel(Game.currentLevel) - numberOfAliens);
		}
		
		if(moveTimer >= 1 / gameSpeed){
			alienMove = true;
			moveTimer = 0;
			if((alienDirection == AlienMove.RIGHT && alienPositions.maxX >= Game.WIDTH - Constants.ALIEN_WIDTH[Constants.JUNIOR] - Constants.ALIEN_HORIZONTAL_SPEED) || (alienDirection == AlienMove.LEFT && alienPositions.minX <= 0)){
				alienDirection = AlienMove.DOWN;
			} else if(alienDirection == AlienMove.DOWN){
				if(alienPositions.maxX >= Game.WIDTH - Constants.ALIEN_WIDTH[Constants.JUNIOR] - Constants.ALIEN_HORIZONTAL_SPEED){
					alienDirection = AlienMove.LEFT;
				} else if(alienPositions.minX <= 0){
					alienDirection = AlienMove.RIGHT;
				}
			}
		}
		
		if(shootTimer>=3.75 / gameSpeed){
			alienShoot = true;
			shootTimer = 0;
			lowestAliens = getLowestAliens();
		}
		
		if(flyingSaucerTimer>=5){
			flyingSaucerTimer = 0;
			if(randomGenerator.nextInt(5) == 4){
				flyingSaucerAppears();
			}
		}
		
		alienPositions.setBaseline();
		playerHasBullet = false;
		
		CollisionManager.getInstance().tick();
		
		int newNumberOfAliens = 0;
		flyingSaucerExists = false;
		for(int i = 0; i < allObjects.size(); i++){
			
			GameObject currentObject = allObjects.get(i);

//			CollisionManager.getInstance().doCollision(i);
			
			if(currentObject instanceof Alien){
				newNumberOfAliens++;
				
				alienPositions.expandValueRanges(currentObject.getX(), currentObject.getY());
				
				if(alienMove){
					try {
						((Alien) currentObject).shouldMoveInDirection = alienDirection;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(alienShoot){
					
					if(lowestAliens.containsValue(currentObject) && (1 + randomGenerator.nextInt(5))>4){
						((Alien) currentObject).shouldShoot = true;
					}
				}
			}
			numberOfAliens = newNumberOfAliens;
			if(currentObject instanceof FlyingSaucer){
				flyingSaucerExists = true;
				System.out.println(currentObject.getVelX());
				int newVelX;
				if(currentObject.getVelX()>0){
					newVelX = (int)Math.floor((Constants.FLYING_SAUCER_BASE_VELOCITY + Game.currentLevel / 2) + (0.05 * (Constants.getTotalAliensPerLevel(Game.currentLevel) - numberOfAliens)));
				}
				else{
					newVelX = (int)Math.floor((int)(-1 * Constants.FLYING_SAUCER_BASE_VELOCITY - Game.currentLevel / 2) - (0.05 * (Constants.getTotalAliensPerLevel(Game.currentLevel) - numberOfAliens)));
				}
				currentObject.setVelX(newVelX);
				System.out.println(currentObject.getVelX());
			}
			currentObject.tick();
			
			
			if(currentObject.getId() == GOID.PlayerBullet){
				playerHasBullet = true;
			}
			
		}
		if(!alienMove){
			moveTimer += GameTime.delta;
			
		}
		else{
			alienMove = false;
		}
		if(!alienShoot){
			shootTimer += GameTime.delta;
		}
		else{
			alienShoot = false;
		}
		if(!flyingSaucerAppear && !flyingSaucerExists){
			flyingSaucerTimer += GameTime.delta;
		}
		else{
			flyingSaucerAppear = false;
		}
		
	}

	public static void render(Graphics g){
		for(int i = 0; i < allObjects.size(); i++){
			allObjects.get(i).render(g);
		}
	}
	
	public static void getPoints(Alien a){
		Game.incrementScore(a.points);
	}
	public static void getPoints(FlyingSaucer fs){
		Game.incrementScore(fs.points);
	}
	
	public static void makeObject(int x, int y, GOID id) throws Exception{
		GameObjectFactory factory;
		switch (id){
		
		case Player:
			factory = pf;
			break;
		case JuniorAlien:
			factory = jaf;
			break;
		case MediorAlien:
			factory = maf;
			break;
		case SeniorAlien:
			factory = saf;
			break;
		case FlyingSaucer:
			factory = fsf;
			break;
		case PlayerBullet:
			factory = pbf;
			break;
		case AlienBullet:
			factory = abf;
			break;
		case Barrier:
			factory = barf;
			break;
		default:
			throw new Exception("Unidentified object type. Cannot reference to a factory.");		
		}
		addObject(factory.getObject(x, y, id));
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
	public static LinkedList<GameObject> getAllObjects(){
		return allObjects;
	}
	public static boolean playerHasBullet(){
		return playerHasBullet;
	}
	
}
