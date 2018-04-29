package com.physics.engine;
import java.util.ArrayList;
public class MassiveObjectCollisionWorld {
	
	public static ArrayList<Rectangle> Rectangles;
	private ArrayList<int[]> checkedPair;
	int colNum = 0;
	public MassiveObjectCollisionWorld() {
		Rectangles = new ArrayList<Rectangle>();
	}
	
	public void setUp() {
		int rectNum = randInt(5, 15);
	/*	for(int i = 0; i < rectNum; i++) {
			int width = randInt(80, 120);
			int height = randInt(80, 120);
			int x = randInt(0, 2300);
			int y = randInt(0, 1100);m
			int xv = randInt(1, 8);
			int yv = randInt(1, 8);
			int mass = randInt(50, 50);
			createRect(x, xv, y, yv, width, height, mass);
		}*/
		
		createRect(100, 5, 100, -4, 100, 100, 10);
		createRect(400, 4, 100, 6, 110, 110, 10);
		createRect(600, -7, 100, 5, 110, 110, 10);
		createRect(800, 4, 800, 6, 110, 110, 10);
		createRect(400, 4, 600, 6, 110, 110, 10);
		createRect(900, 4, 100, 6, 110, 110, 10);
	
	}
	
	private int randInt(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	public void clear() {
		for(int i = 0; i < Rectangles.size(); i++) {
				Rectangles.remove(i);
				for(int i2 = 0; i2 < Rectangles.size(); i2++) {
					Rectangles.remove(i2);
				}
		}
	}
	
	public void update() {
		for(int i = 0; i < Rectangles.size(); i++) {
			Rectangles.get(i).update();
		}		
		collisionCheck();
	}
	
	public int getIndexPos(Rectangle rectangle) {
		return Rectangles.indexOf(rectangle);
	}
	
	public void collisionCheck() {
		checkedPair = new ArrayList<int[]>();
		
		for(int i = 0; i < Rectangles.size(); i++) {
			
			for(int i2 = 0; i2 < Rectangles.size(); i2++) {		
				
				if(i != i2 && checkPair(i, i2) == true) {
					checkCollisions(i, i2);
					
					int[] pair = {i, i2};
					checkedPair.add(pair);
				}
				
			}
			
		}
		
	}
	
	private boolean checkPair(int i, int i2) {
		boolean valid = true;
		
		for(int i3 = 0; i3 < checkedPair.size(); i3++) {
			int[] pair = checkedPair.get(i3);
			
			if((pair[0]) == i && (pair[1] == i2) || (pair[0] == i2 && pair[1] == i)){
				valid = false;
			}	
		}
		return valid;
	}
	
	private void checkCollisions(int i, int i2) {
		int x1 = Rectangles.get(i).getx();
		int x2 = Rectangles.get(i2).getx();
		int y1 = Rectangles.get(i).gety();
		int y2 = Rectangles.get(i2).gety();
		int width1 = Rectangles.get(i).getWidth();
		int width2 = Rectangles.get(i2).getWidth();
		int height1 = Rectangles.get(i).getHeight();
		int height2 = Rectangles.get(i2).getHeight();
		int deltaX;
		int deltaY;
		boolean xRange = false;
		boolean yRange = false;
		
		if(x1 < x2) {
			deltaX = x2 - x1;
			if(deltaX <= width1) {
				xRange = true;
				//System.out.println("1");
			}
		} else {
			deltaX = x1 - x2;
			if(deltaX <= width2) {
				xRange = true;
				//System.out.println("2" + " x1 = " + x1 + " y1 = " + y2 + " x2 = " + x2 + " y2 = " + y2);
			}
		}
		
		if(y1 < y2) {
			deltaY = y2 - y1;
			if(deltaY <= height1) {
				yRange = true;
				//System.out.println("3");
			}
		} else {
			deltaY = y1 - y2;
			if(deltaY <= height2) {
				yRange = true;
				//System.out.println("4");
			}
		}
				
		if(xRange && yRange == true) {
	
			if(deltaX > deltaY) {
				System.out.println("x col " + i + " " + i2);
				
				if(x1 < x2) {
					int overlap = width1 - deltaX;
					Rectangles.get(i).setxCollision(x1 - overlap/2);
					Rectangles.get(i2).setxCollision(x2 + overlap/2);
				} else {
					int overlap = width2 - deltaX;
					Rectangles.get(i).setxCollision(x1 + overlap/2);
					Rectangles.get(i2).setxCollision(x2 - overlap/2);
				}	
				xCol(i, i2);
			} else {
				System.out.println("y col " + i + " " + i2);
				
				if(y1 > y2) {
					int overlap = height2 - deltaY;
					Rectangles.get(i).setyCollision(y1 + overlap/2);
					Rectangles.get(i2).setyCollision(y2 - overlap/2);
				} else {
					int overlap = height1 - deltaY;
					Rectangles.get(i).setyCollision(y1 - overlap/2);
					Rectangles.get(i2).setyCollision(y2 + overlap/2);
				}
				yCol(i, i2);
			}	
			
		}
		
	}
	
	private void xCol(int i, int i2) {
		colNum++;
		int xv = Rectangles.get(i).getxv();
		int xv2 = Rectangles.get(i2).getxv();	
		int mass1 = Rectangles.get(i).getMass();
		int mass2 = Rectangles.get(i2).getMass();
		int momentum1 = xv * mass1;
		
		int momentum2 = xv2 * mass2;
		
		int tmomentum = momentum1;
		momentum1 = momentum2;
		momentum2 = tmomentum;
		xv = momentum1 / mass1;
		System.out.println("xv = " + xv);
		xv2 = momentum2 / mass2;
		System.out.println("xv2 = " + xv2);
		System.out.println("momentum 1 = " + momentum1 + " momentum2 = " + momentum2);
		Rectangles.get(i).setxvObjectCollision(xv);
		Rectangles.get(i2).setxvObjectCollision(xv2);
		System.out.println("EOCEOCEOCEOCEOCEOCOEOCEOCOECO" + colNum);
		}
	
	private void yCol(int i, int i2) {
		colNum++;
		int yv = Rectangles.get(i).getyv();
		System.out.println("about to switch, yv = " + yv);
		int yv2 = Rectangles.get(i2).getyv();
		int mass1 = Rectangles.get(i).getMass();
		System.out.println("about to switch, mass1 = " + mass1);
		int mass2 = Rectangles.get(i2).getMass();
		int momentum1 = yv * mass1;
		System.out.println("Y momentum 1 = " + momentum1);
		int momentum2 = yv2 * mass2;
		System.out.println("Y momentum 2 = " + momentum2);
		
		int tmomentum = momentum1;
		momentum1 = momentum2;
		momentum2 = tmomentum;
		yv = momentum1 / mass1;
		yv2 = momentum2 / mass2;
		
		Rectangles.get(i).setyvObjectCollision(yv);
		Rectangles.get(i2).setyvObjectCollision(yv2);
		System.out.println("EOCEOCEOCEOCEOCEOCOEOCEOCOECO" + colNum);
		
	}
	
	public void createRect(int x, int xv, int y, int yv, int widthRec, int heightRec, int mass){
	//	Rectangle rect = new Rectangle(x, xv, y, yv, heightRec, widthRec, mass, this);
		//Rectangles.add(rect); 
	}
	
	
}







