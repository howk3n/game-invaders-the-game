package com.dusan.game.gameinvadersthgame.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.dusan.game.gameinvadersthgame.common.Constants;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -4822824580043367729L;
	
	private static Game instance;
	private Thread thread;
	private boolean running = false;
	
	public static int WIDTH = Constants.DEFAULT_SCREEN_WIDTH;
	public static int HEIGHT = Constants.DEFAULT_SCREEN_HEIGHT;
	private static Color BACKGROUND_COLOR = Constants.DEFAULT_BACKGROUND_COLOR;
	private HUD hud;
	public static int currentLevel;	
	public static int score;
	public static int PLAYER_STARTING_X = Constants.DEFAULT_BASIC_BARRIER_WIDTH + (Constants.DEFAULT_BASIC_BARRIER_WIDTH - Constants.DEFAULT_PLAYER_WIDTH) / 2;
	public static int PLAYER_STARTING_Y = HEIGHT - Constants.DEFAULT_PLAYER_HEIGHT;
//	private static boolean isPlaying = false;
	
	private void initFrame(int width, int height, String title){
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		frame.add(this);
		frame.setVisible(true);
		this.requestFocusInWindow();
	}
	
	private Game(int width, int height, String title){	
		
		initFrame(WIDTH,HEIGHT, title);

		hud = HUD.getInstance();
		this.addKeyListener(KeyInput.getInstance());
		GameObjectManager.init();
		currentLevel = 0;
		GameObjectManager.initLevel(currentLevel);
		this.start();
		
	}
	
	public static Game getInstance(){
		
		if(instance == null){
			instance = new Game(WIDTH, HEIGHT, "Game Invaders The Game");
		}
		return instance;
		
	}
	
	public static void incrementScore(int points){
		score += points;
	}
	
	public static void gameOver(){
//		isPlaying = false;
	}
	
	public static void nextLevel(){
		currentLevel++;
		GameObjectManager.initLevel(currentLevel);
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
//		if(isPlaying){
//			GameObjectManager.tick();
//		}
		GameObjectManager.tick();
		hud.tick();
	}
	
	private void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
//		if(isPlaying){
//			GameObjectManager.render(g);
//		}
		GameObjectManager.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	public static void main(String args[]){
		
		getInstance();
		
	}
	
}
