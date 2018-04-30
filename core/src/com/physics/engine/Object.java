package com.physics.engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Object {
	
	int cx = (int)PhysicsMain.getWidth() / 2;
	int cy = (int)PhysicsMain.getHeight() / 2;
	ShapeRenderer objectRenderer = new ShapeRenderer();
	PhysicsMain physMain;
	private CircularMotionWorld cmWorld;
	private float x;
	private float y;
	private float xv;
	private float yv;
	private int r;
	private int mass;
	
	public Object(float x, float y, float xv, float yv, int r, int mass, CircularMotionWorld cmWorld) {
		this.x = x;
		this.y = y;
		this.xv = xv;
		this.yv = yv;
		this.r = r;
		this.mass = mass;
		this.cmWorld = cmWorld;
	}
	
	public void render() {
		
		objectRenderer.begin(ShapeType.Filled);
		
		objectRenderer.setColor(Color.GREEN);
        objectRenderer.circle(x, y, r);

        objectRenderer.setColor(Color.YELLOW);
        objectRenderer.circle(PhysicsMain.getWidth() / 2, PhysicsMain.getHeight() / 2, 20);
        objectRenderer.end();
	}

	public float getx() {
		return x;
	}
	
	public void setx(float x) {
		this.x = x;
	}
	
	public void sety(float y) {
		this.y = y;
	}
	
	public float gety() {
		return y;
	}
	
	public float getxv() {
		return xv;
	}
	
	public int getR() {
		return r;
	}
	
	public void setxv(float xv) {
		this.xv = xv;
	}
	
	public void setyv(float yv) {
		this.yv = yv;
	}
	
	public float getyv() {
		return yv;
	}
	
	public int getMass() {
		return mass;
	}


}
