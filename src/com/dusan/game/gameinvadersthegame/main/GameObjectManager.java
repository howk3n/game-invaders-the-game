package com.dusan.game.gameinvadersthegame.main;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import com.dusan.game.gameinvadersthegame.common.Constants;
import com.dusan.game.gameinvadersthegame.common.GOID;
import com.dusan.game.gameinvadersthegame.common.MyMath;
import com.dusan.game.gameinvadersthegame.gameobjects.Alien;
import com.dusan.game.gameinvadersthegame.gameobjects.AlienBullet;
import com.dusan.game.gameinvadersthegame.gameobjects.AlienBulletFactory;
import com.dusan.game.gameinvadersthegame.gameobjects.BarrierFactory;
import com.dusan.game.gameinvadersthegame.gameobjects.FlyingSaucer;
import com.dusan.game.gameinvadersthegame.gameobjects.FlyingSaucerFactory;
import com.dusan.game.gameinvadersthegame.gameobjects.GameObject;
import com.dusan.game.gameinvadersthegame.gameobjects.GameObjectFactory;
import com.dusan.game.gameinvadersthegame.gameobjects.JuniorAlien;
import com.dusan.game.gameinvadersthegame.gameobjects.JuniorAlienFactory;
import com.dusan.game.gameinvadersthegame.gameobjects.MediorAlien;
import com.dusan.game.gameinvadersthegame.gameobjects.MediorAlienFactory;
import com.dusan.game.gameinvadersthegame.gameobjects.Player;
import com.dusan.game.gameinvadersthegame.gameobjects.PlayerBulletFactory;
import com.dusan.game.gameinvadersthegame.gameobjects.PlayerFactory;
import com.dusan.game.gameinvadersthegame.gameobjects.SeniorAlien;
import com.dusan.game.gameinvadersthegame.gameobjects.SeniorAlienFactory;
import com.dusan.game.gameinvadersthegame.gameobjects.Alien.AlienMove;

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
	private static double gameTimer;
	private static AlienMove alienDirection;
	private static int numberOfAliens;
	private static HashMap<Integer, Alien> lowestAliens;
	private static Random randomGenerator;
	private static boolean alienMove;
	private static boolean alienShoot;
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
		gameTimer = 0;
		alienDirection = AlienMove.RIGHT;
		lowestAliens = new HashMap<Integer, Alien>();
		randomGenerator = new Random();
		alienMove = false;
		alienShoot = false;
		playerHasBullet = false;

		Player.lives = Constants.PLAYER_STARTING_LIVES;
		
	}
	
	public static void killPlayer(){
		Player player = getPlayer();
		if(Player.lives <= 1){
//			allObjects.clear();
			Game.getInstance().gameOver();
		}
		else{
			player.die();
			System.out.println("GameObjectManager says die player.");
		}
	}
	
	private static HashMap<Integer, Alien> getLowestAliens(){
		HashMap<Integer, Alien> lowestAliens = new HashMap<Integer, Alien>();
		for(int i = 0; i < allObjectsSize(); i++){
			if(allObjects.get(i) instanceof Alien){
				Alien alien = (Alien) allObjects.get(i);
				int columnIndex = alien.getColumnIndex();
				if(lowestAliens.get(columnIndex) == null || lowestAliens.get(columnIndex).getY() < alien.getY()){
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
		if(level == 0){
			try {
				makeObject(Game.getInstance().playerStartingX, Game.getInstance().playerStartingY, GOID.Player);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			for(int i = 0; i < Constants.NUMBER_OF_BARRIERS; i++){
				try {
					makeObject(Constants.DEFAULT_BASIC_BARRIER_WIDTH + (2*i*Constants.DEFAULT_BASIC_BARRIER_WIDTH), Game.getInstance().height - Constants.HUD_HEIGHT * 2, GOID.Barrier);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else{
			Player.lives++;
		}
		
		int x = 0;
		int y = 0;
		int alienRows = Constants.getTotalAliensPerLevel(level)/Constants.ALIEN_COLUMN_COUNT;
		
		for(int i = 0; i < alienRows; i++){
			for(int j = 0; j < Constants.ALIEN_COLUMN_COUNT; j++){
				
				x = Constants.ALIEN_COLUMN_WIDTH * 5 / 2 + (Constants.ALIEN_COLUMN_WIDTH * j);
				y = Constants.HUD_HEIGHT + Constants.FLYING_SAUCER_HEIGHT + Constants.ALIEN_VERTICAL_SPEED / 2  + i * (Constants.ALIEN_HEIGHT + Constants.ALIEN_VERTICAL_SPEED) + (Constants.ALIEN_VERTICAL_SPEED / 2) * MyMath.clamp(level - 1, 0, 10);
				
				if(Constants.ALIENS_PER_LEVEL[level][Constants.SENIOR] / Constants.ALIEN_COLUMN_COUNT > i){
					try {
						makeAlienWithColumnIndex(x + (Constants.ALIEN_WIDTH[Constants.JUNIOR] - Constants.ALIEN_WIDTH[Constants.SENIOR]) / 2, y, j, GOID.SeniorAlien);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else if((Constants.ALIENS_PER_LEVEL[level][Constants.MEDIOR] + Constants.ALIENS_PER_LEVEL[level][Constants.SENIOR]) / Constants.ALIEN_COLUMN_COUNT > i){
					try {
						makeAlienWithColumnIndex(x + (Constants.ALIEN_WIDTH[Constants.JUNIOR] - Constants.ALIEN_WIDTH[Constants.MEDIOR]) / 2, y, j, GOID.MediorAlien);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else {
					try {
						makeAlienWithColumnIndex(x, y, j, GOID.JuniorAlien);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
			numberOfAliens++;
		}
		
	}
	
	private static void flyingSaucerAppears(){
		try {
			makeObject(9999, Constants.HUD_HEIGHT, GOID.FlyingSaucer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Flying Saucer Appears!");
	}
	

	
	public static void tick(){
		
		if(numberOfAliens == 0){
			Game.getInstance().nextLevel();
		}
		else{
			double levelSpeedCoefficient = 1 + (Game.getInstance().currentLevel * 0.2);
			double maxLevelSpeedModifier = 2;
			double totalAliens = Constants.getTotalAliensPerLevel(Game.getInstance().currentLevel);
			double speedAlg = levelSpeedCoefficient * (maxLevelSpeedModifier - numberOfAliens / totalAliens);
			gameSpeed = MyMath.clamp(speedAlg, Constants.MINIMUM_GAME_SPEED, Constants.MAXIMUM_GAME_SPEED);
		}
		
		if(gameTimer >= 1 / gameSpeed){
			gameTimer = 0;
			
			for(int i = 0; i < allObjectsSize(); i++){
				GameObject currentObject = allObjects.get(i);
				if(currentObject instanceof Alien){
					alienPositions.expandValueRanges(currentObject.getX(), currentObject.getY());
				}
			}
			
//			Alien Movement
			alienMove = true;
			System.out.println(alienPositions.maxX);
			if((alienDirection == AlienMove.RIGHT && alienPositions.maxX >= Game.getInstance().width - Constants.ALIEN_WIDTH[Constants.JUNIOR] - Constants.ALIEN_HORIZONTAL_SPEED) || (alienDirection == AlienMove.LEFT && alienPositions.minX <= 0)){
				alienDirection = AlienMove.DOWN;
			} else if(alienDirection == AlienMove.DOWN){
				if(alienPositions.maxX >= Game.getInstance().width - Constants.ALIEN_WIDTH[Constants.JUNIOR] - Constants.ALIEN_HORIZONTAL_SPEED){
					alienDirection = AlienMove.LEFT;
				} else if(alienPositions.minX <= 0){
					alienDirection = AlienMove.RIGHT;
				}
			}
			
			
			
//			Alien Shootment
			alienShoot = true;
			lowestAliens = getLowestAliens();
			
//			Flying Saucer Spawnment
			if(randomGenerator.nextInt(30) == 29){
				flyingSaucerAppears();
			}
			
		}
		
		playerHasBullet = false;
		
		CollisionManager.getInstance().tick();
		alienPositions.setBaseline();
		int newNumberOfAliens = 0;
		for(int i = 0; i < allObjects.size(); i++){
			
			GameObject currentObject = allObjects.get(i);
			
			if(currentObject instanceof Alien){
				newNumberOfAliens++;
				
				if(alienMove){
					try {
						((Alien) currentObject).shouldMoveInDirection = alienDirection;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(alienShoot){
					if(lowestAliens.containsValue(currentObject) && (randomGenerator.nextInt(25)) >= 23){
						((Alien) currentObject).shouldShoot = true;
					}
					else{
						((Alien) currentObject).shouldShoot = false;
					}
				}
			}
			numberOfAliens = newNumberOfAliens;
			if(currentObject instanceof FlyingSaucer){
				int newVelX;
				if(currentObject.getVelX() > 0){
					newVelX = (int)Math.floor((Constants.FLYING_SAUCER_BASE_VELOCITY + (Game.getInstance().currentLevel + 1) / 3) + (0.04 * (Constants.getTotalAliensPerLevel(Game.getInstance().currentLevel) - numberOfAliens)));
				}
				else{
					newVelX = (int)Math.floor((int)(-1 * Constants.FLYING_SAUCER_BASE_VELOCITY - (Game.getInstance().currentLevel + 1) / 3) - (0.04 * (Constants.getTotalAliensPerLevel(Game.getInstance().currentLevel) - numberOfAliens)));
				}
				currentObject.setVelX(newVelX);
			}
			currentObject.tick();
			
			
			if(currentObject.getId() == GOID.PlayerBullet){
				playerHasBullet = true;
			}
			
		}
		
		gameTimer += GameTime.delta;
		alienShoot = false;
		alienMove = false;
		
	}

	public static void render(Graphics g){
		for(int i = 0; i < allObjects.size(); i++){
			allObjects.get(i).render(g);
		}
	}
	
	public static void getPoints(Alien a){
		Game.getInstance().incrementScore(a.points);
	}
	public static void getPoints(FlyingSaucer fs){
		Game.getInstance().incrementScore(fs.points);
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
	
	public static void makeAlienBulletWithVelocity(int x, int y, int velX, int velY){
		AlienBullet tempBullet = (AlienBullet) abf.getObject(x, y, GOID.AlienBullet);
		tempBullet.setVelX(velX);
		tempBullet.setVelY(velY);
		addObject(tempBullet);
	}
	
	public static void makeAlienWithColumnIndex(int x, int y, int columnIndex, GOID id) throws Exception{
		Alien alien;
		switch(id){
		case JuniorAlien:
			alien = (JuniorAlien) jaf.getObject(x, y, id);
			break;
		case MediorAlien:
			alien = (MediorAlien) maf.getObject(x, y, id);
			break;
		case SeniorAlien:
			alien = (SeniorAlien) saf.getObject(x, y, id);
			break;
		default:
			throw new Exception("Invalid alien ID");
		}
		alien.setColumnIndex(columnIndex);
		addObject(alien);
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
