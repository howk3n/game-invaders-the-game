package com.dusan.game.gameinvadersthegame.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.dusan.game.gameinvadersthegame.common.Constants;
import com.dusan.game.gameinvadersthegame.gfx.Assets;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -4822824580043367729L;
	
	protected enum STATE{ MENU, GAME, ENDGAME_SCREEN };
	protected STATE state;
	
	private static Game instance;
	private Thread thread;
	private boolean running;
	private Color backgroundColor;
	private HUD hud;
	
	public int width;
	public int height;
	public int currentLevel;	
	public int score;
	public int playerStartingY;
	public int playerStartingX;
	
	private void initGame(){
		
		state = STATE.MENU;
		running = false;
		width = Constants.DEFAULT_SCREEN_WIDTH;
		height = Constants.DEFAULT_SCREEN_HEIGHT;
		backgroundColor = Constants.DEFAULT_BACKGROUND_COLOR;
		playerStartingY = height - Constants.DEFAULT_PLAYER_HEIGHT;
		playerStartingX = Constants.DEFAULT_BASIC_BARRIER_WIDTH + (Constants.DEFAULT_BASIC_BARRIER_WIDTH - Constants.DEFAULT_PLAYER_WIDTH) / 2;
		
		initFrame(Constants.DEFAULT_SCREEN_WIDTH, Constants.DEFAULT_SCREEN_HEIGHT, "Game Invaders The Game");
		hud = HUD.getInstance();
		this.addMouseListener(MouseInput.getInstance());
		this.addKeyListener(KeyInput.getInstance());
		score = 0;
		currentLevel = 0;
		this.start();
	}
	
	private void initFrame(int width, int height, String title){
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		Assets.init();
		Menu.init();
		
		
		frame.add(this);
		frame.setVisible(true);
		this.requestFocusInWindow();
	}
	
	private Game(){}
	
	public static Game getInstance(){
		
		if(instance == null){
			instance = new Game();
			instance.initGame();
		}
		return instance;
		
	}
	
	public void startGame(){
		GameObjectManager.init();
		GameObjectManager.initLevel(currentLevel);
		state = STATE.GAME;
	}
	
	public void incrementScore(int points){
		score += points;
	}
	
	public void gameOver(){
		state = STATE.ENDGAME_SCREEN;
		EndGameScreen.init();
		GameObjectManager.getAllObjects().clear();
	}
	
	public void nextLevel(){
		currentLevel++;
		GameObjectManager.initLevel(currentLevel);
	}
	
	public void saveScore(){
//		persist score and then...
		state = STATE.MENU;
		reset();
	}
	
	private void reset(){
		score = 0;
		currentLevel = 0;
	}

	public synchronized void start(){
		
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		
		long lastTime = System.nanoTime();
		long tickLastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				long tickNow = System.nanoTime();
				GameTime.delta = ((double) (tickNow - tickLastTime)) / 1000000000;
				tickLastTime = tickNow;
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}
	
	private void tick(){
		if(state == STATE.GAME){
			GameObjectManager.tick();
			hud.tick();
		}
		else if(state == STATE.MENU){
			
		}
		else if(state == STATE.ENDGAME_SCREEN){
			
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);
		
		if(state == STATE.GAME){
			GameObjectManager.render(g);
			hud.render(g);
		}
		else if(state == STATE.MENU){
			Menu.render(g);
		}
		else if(state == STATE.ENDGAME_SCREEN){
			EndGameScreen.render(g);
		}
		
		
		g.dispose();
		bs.show();
		
	}
	
	public static void main(String args[]){
		
		getInstance();
		
	}
	
}
