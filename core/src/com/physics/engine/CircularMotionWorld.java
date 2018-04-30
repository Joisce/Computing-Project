package com.physics.engine;

import java.util.ArrayList;
import java.util.Scanner;

public class CircularMotionWorld {
	public static ArrayList<Object> objects;
	
	Object Object;
	PhysicsMain physMain;
	int cx = (int) (PhysicsMain.getWidth() / 2);
	int cy = (int) (PhysicsMain.getHeight() / 2);
	double g = 9.81;
	
	public CircularMotionWorld() {
		objects = new ArrayList<Object>();
	}

	public void setUpRandom() {	
		objects = new ArrayList<Object>();
			int objectNum = randInt(9, 10);

			for(int i = 0; i < objectNum; i++) {
				float x = randInt(300, 600);
				float y = randInt(300, 600);
				int v = randInt(1,5);
				int r = randInt(15, 25);
				int mass = randInt(10, 1000);
				
				createObject(x, y, v, v, r, mass);
			}
	}
	
	public void setUpUser(){
		
	
		Scanner bucky = new Scanner(System.in);
		
		System.out.println("How many objects?");
		
		int numRect;
		
		numRect = bucky.nextInt();
		
		for(int i = 0; i < numRect; i++) {
		
			System.out.println("Enter the starting x possition for the object ");
			int x = bucky.nextInt();
		
			System.out.println("Enter the starting y coordinate for the object ");
			int y = bucky.nextInt();
			
			System.out.println("Enter the starting velocity for the object");
			int v = bucky.nextInt();
			
			System.out.println("Enter the radius of the object ");
			int r = bucky.nextInt();
			
			System.out.println("Enter the mass of the object ");
			int mass = bucky.nextInt();
			
			int xv = v;
			int yv = v;
			createObject(x, y, xv, yv, r, mass);
			}
		}

	private int randInt(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	public void remove() {
		for(int i = objects.size(); i > 0; i--) {
			objects.remove((i-1));
		}
	}	
	
	public void update() {
       
		for(int i = 0; i < objects.size(); i++) {
		
			double angleRight = 0;
			double angleLeft = 0;
			double d = 0;
			
			float x = objects.get(i).getx();
			float y = objects.get(i).gety();
			double xv = objects.get(i).getxv();
			double yv = objects.get(i).getyv();
			
			System.out.println("xv = " + xv + " yv = " + yv);
			
			d = solveD(x, y);

			if(x > cx && y < cy) {
				angleRight = angleRightDown(x, y, d);
				System.out.println("angle right = " + angleRight);
			}
			
			if(x < cx && y < cy) {
				angleLeft = angleLeftDown(x, y, d);
				System.out.println("angle left = " + angleLeft);
			}
			
			if(x > cx && y > cy) {
				angleRight = angleRightUp(x, y, d);
				System.out.println("angle right = " + angleRight);
			}
			
			if(x < cx && y > cy) {
				angleLeft = angleLeftUp(x, y, d);
				System.out.println("angle left = " + angleLeft);
			}
			
		//	if(angleLeft > 0) {
		//		angle = angleLeft;
		//	}
			
			//if(angleRight > 0) {
			//	angle = angleRight;
			//}
			
		//	double angularVelocity = xv;
			
			System.out.println("y = " + y + " x = " + x);
			
			//System.out.println("angle = " + angle);

			if(x < cx && y < cy) {
				xv = xv*Math.sin(angleLeft);
				yv = yv*Math.cos(angleLeft);
				x += xv;
				y += -yv;	
				System.out.println("real xv = " + xv);
			}
			
			if(x > cx && y < cy) {
				xv = xv*Math.sin(angleRight);
				yv = yv*Math.cos(angleRight);
				x += xv;
				y += yv;	
			}
			
			if(x > cx && y > cy) {
				xv = xv*Math.sin(angleRight);
				yv = yv*Math.cos(angleRight); 
				System.out.println("real xv = " + xv);
				x += -xv;
				y += yv;	
			}
			
			if(x < cx && y > cy) {
				xv = xv*Math.sin(angleLeft);
				yv = yv*Math.cos(angleLeft);
				x += -xv;
				y += -yv;	
				System.out.println("real xv = " + xv);
			}
			
			int mass = objects.get(i).getMass();
			int r = objects.get(i).getR();
			
			double force = (mass*xv*xv)/r;
			System.out.println("The centripetal force = " + force);
			
			objects.get(i).setx(x);
			objects.get(i).sety(y);			
			objects.get(i).render();
			//objects.get(i).renderString();
		}
		
	}
	
	public double solveD(float x, float y) {
		double d = 0;
		double deltaX = x - cx;
		double deltaY = y - cy;		
		d = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
		return d;
	}

	public double angleRightDown(float x, float y, double d) {

		
		double deltaY = cy - y;
		double deltaX = x - cx;
		double angle = Math.atan(deltaY / deltaX);
		return angle;
	}
	
	public double angleRightUp(float x, float y, double d) {
		
		double deltaY = y - cy;
		double deltaX = x - cx;
		double angle = Math.atan(deltaY / deltaX);
		return angle;
	}
			
	public double angleLeftDown(float x, float y, double d) {
		
		double deltaY = cy - y;
		double deltaX = cx - x;
		double angle = Math.atan(deltaY / deltaX);
		return angle;
	}
	
	public double angleLeftUp(float x, float y, double d) {

		
		double deltaY = y - cy;
		double deltaX = cx - x;
		double angle = Math.atan(deltaY / deltaX);
		return angle;
	}
		

	public void createObject(float x, float y, int xv, int yv, int r, int mass){
		Object object = new Object(x, y, xv, yv, r, mass, this);
		objects.add(object); 
	
	} 
}
