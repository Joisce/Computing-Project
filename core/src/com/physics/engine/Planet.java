package com.physics.engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Planet {
	
	ShapeRenderer planetRenderer = new ShapeRenderer();
	PhysicsMain physMain;

	private float x;
	private float y;
	private double xv;
	private double yv;
	private int r;
	private int mass;
	
	public Planet(int x, int y, double xv, double yv, int r, int mass, OrbitalMotionWorld omWorld) {
		this.x = x;
		this.y = y;
		this.xv = xv;
		this.yv = yv;
		this.r = r;
		this.mass = mass;
	}
		
	
	
	public void render() {
		
		planetRenderer.begin(ShapeType.Filled);
		
		planetRenderer.setColor(Color.GREEN);
        planetRenderer.circle(x, y, r);        
       
        planetRenderer.end();
	}
	
	
	public float getx() {
		return x;
	}
	
	public void setx(float x) {
		this.x = x;
	}
	
	public float gety() {
		return y;
	}	
	
	public int getr() {
		return r;
	}
	
	public void sety(float y) {
		this.y = y;
	}
	
	public double getxv() {
		return xv;
	}
	
	public void setxv(double xv) {
		this.xv = xv;
	}
	
	public void setyv(double yv) {
		this.yv = yv;
	}
	
	public double getyv() {
		return yv;
	}
	
	public int getMass() {
		return mass;
	}

}
