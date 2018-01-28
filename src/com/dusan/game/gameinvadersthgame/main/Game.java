package com.dusan.game.gameinvadersthgame.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

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
	
	private Game(){	
		Handler.init();
		hud = HUD.getInstance();
		this.addKeyListener(KeyInput.getInstance());
		
		currentLevel=1;
		Handler.initLevel(currentLevel);
		
		new DefaultWindow(WIDTH, HEIGHT, "Game Invaders Try 2", this);
	}
	
	public static Game getInstance(){
		
		if(instance == null){
			instance = new Game();
		}
		return instance;
		
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
		Handler.tick();
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
		
		Handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	public static int clamp(int var, int min, int max){
		
		if(var >= max){
			return var = max;
		}
		else if(var <= min){
			return var = min;
		}
		else return var;
		
	}
	
	public static void main(String args[]){
		
		getInstance();
		
	}
	
}
