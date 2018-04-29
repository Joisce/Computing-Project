package com.physics.engine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
public class Rectangle {
	ShapeRenderer rectRenderer = new ShapeRenderer();
	
	private ElasticCollisionWorld world;
	private int x;
	private int y;
	private int xv;
	private int yv;
	private int heightRec;
	private int widthRec;
	private int mass;
	boolean movement = true;
	
	public Rectangle(int x, int y, int xv, int yv, int widthRec, int heightRec, ElasticCollisionWorld world) {
		System.out.println("wowww");
		this.x = x;
		this.y = y;
		this.xv = xv;
		this.yv = yv;
		this.widthRec = widthRec;
		this.heightRec = heightRec;
		this.world = world;
		System.out.println("ahhhh");
	}
	
	/*
	public Rectangle(int x, int xv, int y, int yv, int heightRec, int widthRec, int mass,
			MassiveObjectCollisionWorld massiveObjectCollisionWorld) {
		this.x = x;
		this.y = y;
		this.xv = xv;
		this.yv = yv;
		this.widthRec = widthRec;
		this.heightRec = heightRec;
		this.mass = mass;
	}
	*/
	public void update() {
		
		if (movement == true) {
			
			x += xv;
			y += yv;
			checkXBounds(x);
			checkYBounds(y);
			render();
		
		}	
	}
	
	public void render() {
		
		rectRenderer.begin(ShapeType.Filled);
		
		rectRenderer.setColor(Color.GOLD);
        rectRenderer.rect(x, y, widthRec, heightRec);
       
        rectRenderer.end();
		
	}
	
	
	void checkXBounds(int x){
		if(x <= 0) {
			setxv0();
		}
		
		if(x >= PhysicsMain.getWidth() - widthRec) {
			setxvMax();
		}
		
	}
	
	void checkYBounds(int y) {
		if(y <= 0) {
			setyv0();
		}
		
		if(y >= PhysicsMain.getHeight() - heightRec) {
			setyvMax();
		}
		
	}
	
	
	public int getx(){
		return x;
	}
	
	public int gety(){
		return y;
	}
	
	public int getxv() {
		return xv;
	}
	
	
	public int getyv() {
		return yv;
	}
	
	public void setxCollision(int x) {
		this.x = x;
	}
	
	public void setyCollision(int y) {
		this.y = y;
	}
	
	public void setyvMax(){
		y = PhysicsMain.getHeight() - heightRec;
		yv=-yv;
	}
	
	public void setyv0() {
		y = 0;
		yv = -yv;
	}
	
	public void setxv0(){
		x = 0;
		xv=-xv;
	}
	
	public void setxvMax() {
		x = PhysicsMain.getWidth() - widthRec;
		xv = - xv;
	}
	
	public void setxvObjectCollision(int tvx) {
		xv = tvx;
		
	}
	
	public void setyvObjectCollision(int tyv) {
		yv = tyv;
	}
	
	public int getHeight(){
		return heightRec;
	}
	
	public int getWidth(){
		return widthRec;
	}
	
	public int getMass() {
		return mass;
	}
}
