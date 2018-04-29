package com.physics.engine; 

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.physics.engine.GameScreen;

public class PhysicsMain extends Game {
	private static int width;
	private static int height;
	
	ShapeRenderer sr;
	
	public PhysicsMain(int width, int height) {
		PhysicsMain.width = width;
		PhysicsMain.height = height;
	}
	
	@Override
	public void create () {
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(229/255.0f, 57/225.0f, 57/255.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
	

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
}




//GOOD MORNING, WHAT A LOVELY DAY
//TRY TO GET IT TO LAUNCH IN FULL SCREEN IF POSSIBLE
//GET THE WIDTH AND HEIGHT OF THE SCREEN AND USE THESE
//VALUES IN THE SIMS. 
//HAVE AN AREA OF THE SCREEN THAT CAN BE CLICKED FOR 
//SELECTING RANDOM OR USER ENTERED VALUES AND WORK OUT
//HOW WE ARE GOING TO TAKE A VALUE STRIAGHT FROM THE OLD KEYBOARD

//THEN START ON THE DOCUMENTATION, UPDATING OBJECTIVES AND TESTS ETC