package com.physics.engine;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.physics.engine.GameScreen;

public class GameScreen implements Screen {
	
	ShapeRenderer sr;
	
	float a;
	float b;
	float c;
	float d;
	float collNum;

	boolean movement = true;
	boolean collisionx = false;
	boolean collisiony = false;
	boolean set = false;
	boolean ctf = false;
	boolean testC = false;
	boolean dis = false;
	
	ElasticCollisionWorld myWorld;
	Rectangle rectangle;
	Menu menu;
	
	PhysicsMain game;
	
	public GameScreen (PhysicsMain game) {
		this.game = game;
		//myWorld = new ElasticCollisionWorld();
		menu = new Menu();
	}

	

	@Override
	public void show() {
		sr = new ShapeRenderer();
	}


	@Override
	public void render(float delta) {
		menu.menuScreen();
		
	}
    
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}



