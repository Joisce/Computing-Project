package com.physics.engine;
import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class OrbitalMotionWorld {
	

	
	public static ArrayList<Planet> planets;
	Planet planet;
	PhysicsMain physMain;
	private ArrayList<int[]> checkedPair;
	int index = 0;
	int index1 = 0;
	public OrbitalMotionWorld() {
		

		 
		planets = new ArrayList<Planet>();
	
	}
	
	
	
	
	public void setUp() {
		//drawText();
		Scanner bucky = new Scanner(System.in);
		
		char selection;
		System.out.println("Press R for randomly generated planets and press U for user input data");
		selection = bucky.next().charAt(0);
		selection = Character.toUpperCase(selection);
		
		//file:///C:/Users/richa/Desktop/test/core/assets/text.png
		if(selection == 'R') {
			int planetNum = randInt(9, 10);
			for(int i = 0; i < planetNum; i++) {
				int mass = randInt(1000000, 55000000);
				int r = randInt(20, 20);
				int x = randInt(0, 2300);
				int y = randInt(0, 1100);
				int xv = randInt(-2, 2);
				int yv = randInt(-1, 2);
				
				createPlanet(x, y, xv, yv, r, mass);
			}
			
		} else {
		
		System.out.println("How many planets?");
		
		int numPlanets;
		
		numPlanets = bucky.nextInt();
		
		for(int i = 0; i < numPlanets; i++) {
		
			System.out.println("Enter the x coordinate for planet " + (i + 1));
			int x = bucky.nextInt();
		
			System.out.println("Enter the y coordinate for planet " + (i + 1));
			int y = bucky.nextInt();

			System.out.println("Enter the starting velocity in the x direction for planet " + (i + 1));
			int xv = bucky.nextInt();
		
			System.out.println("Enter the starting velocity in the y direction for planet " + (i + 1));
			int yv = bucky.nextInt();
			
			System.out.println("Enter the radius of the planet " + (i + 1));
			int r = bucky.nextInt();
			
			System.out.println("Enter the mass of the planet " + (i + 1));
			int mass = bucky.nextInt();
			
			createPlanet(x, y, xv, yv, r, mass);
			
			}
		}
		
		//createPlanet(700, 400, 0, 0, 20, 45000000);	//p2
		//createPlanet(800, 800, -3, 0, 20, 1000000);  //p1
		//createPlanet(700, 300, 0, 0, 20, 1000000);
		//createPlanet(500, 1000, 0, 0, 20, 55000000);
		}
		
		private int randInt(int min, int max) {
			return min + (int)(Math.random() * ((max - min) + 1));
		}
	
	
	//The number of times each planet is rendered each frame is effected by how many planets there are
	//interesting right?
	
	public void check() {
		System.out.println("interesting 1");
		boolean collision = false;
		for(int i = 0; i < planets.size(); i++) {
			System.out.println("interesting 2");
			for(int i2 = 0; i2 < planets.size() && i != i2 && validPair(i, i2) == true; i2++) {
				
				float x = planets.get(i).getx();
				float y = planets.get(i).gety();
				
				float x2 = planets.get(i2).getx();
				float y2 = planets.get(i2).gety();
				double deltaX = x - x2;
				double deltaY = y - y2;
				double seperation = Math.sqrt((deltaY * deltaY) + (deltaX * deltaX));
				
				int r1 = planets.get(i).getr();
				int r2 = planets.get(i2).getr();
				if(r1 + r2 >= seperation) {
					removeCollidingPlanets(i, i2);
					collision = true;
				}	
			}
		}
		if(collision == true) {
			removePlanets();
		}
		System.out.println("interesting 3");
		update();
	}
	
	private void removeCollidingPlanets(int i, int i2) {
		System.out.println("need to remove " + i + ", " + i2);
		index = i;
		index1 = i2;
	}
	
	private void removePlanets() {
		planets.remove(index);
		planets.remove(index1);
		index = 0;
		index1 = 0;
	}
	
	public void removeAllPlanets(){
		for(int i = planets.size(); i > 0; i--) {
			System.out.println("i - 1 = " + (i-1));
			planets.remove((i-1));
		}
	}
	
	public void update() {
		
		if(planets.size() != 1) {
			
			for(int i = 0; i < planets.size(); i++) {
				float x = planets.get(i).getx();
				float y = planets.get(i).gety();
				for(int i2 = 0; i2 < planets.size() && i2 != i && validPair(i, i2) == true; i2++) {
					float checksx = planets.get(i).getx();
					float checksy = planets.get(i2).gety();
					System.out.println("checksx = " + checksx);
					System.out.println("checksy = " + checksy);
					float x2 = planets.get(i2).getx();
					float y2 = planets.get(i2).gety();
					double deltaX = x - x2;
					double deltaY = y - y2;
					double r = Math.sqrt((deltaY * deltaY) + (deltaX * deltaX));
					double mass = planets.get(i).getMass();
					double mass2 = planets.get(i2).getMass();
					double G = 6.67E-4; //The gravitational constant
					double f = ((G * mass * mass2) / (r * r)); //Newton's gravitational equation
					double a = f / mass;
					System.out.println("force acting = " + f);
					double a2 = f / mass2;
					double xv = planets.get(i).getxv();
					double yv = planets.get(i).getyv();
					double xv2 = planets.get(i2).getxv();
					double yv2 = planets.get(i2).getyv();
					double angle = Math.atan(deltaY / deltaX);
					
					if(x >= x2) {
						angle += Math.PI;
					}
				
					//System.out.println("force x 1 = " + a*Math.cos(angle));
					xv += a * Math.cos(angle);				
					yv += a * Math.sin(angle);				
					//System.out.println("force y 1 = " + a*Math.sin(angle));				
					xv2 += a2 * Math.cos(angle);				
					//System.out.println("force x 2 = " + a*Math.cos(angle));				
					yv2 += a2 * Math.sin(angle);				
					//System.out.println("force y 2 = " + a*Math.sin(angle));				
					System.out.println("x = " + x + " x2 = " + x2);
					System.out.println("y = " + y + " y2 = " + y2);
				
					if(x > x2) {					
						System.out.println("x = " + x);
						//System.out.println("xv = " + xv);
						x += xv;					
						planets.get(i).setx(x);
						x2 += xv2;
						planets.get(i2).setx(x2);
						System.out.println(  "planet " + i + " xv = " + xv + " planet " + i2 +  " xv2 = " + xv2);
						System.out.println("in 1");					
					} else {
						x += xv;
						planets.get(i).setx(x);					
						x2 += xv2;
						planets.get(i2).setx(x2);
						System.out.println(  "planet " + i + " xv = " + xv + " planet " + i2 +  " xv2 = " + xv2);
						System.out.println("in 2");
					}

					if(y > y2) {
						y += yv;
						planets.get(i).sety(y);					
						y2 += - yv2;
						planets.get(i2).sety(y2);
						System.out.println("yv = " + yv + " yv2 = " + yv2);
						System.out.println("in 3");
					} else {
						y += yv;
						planets.get(i).sety(y);				
						y2 += - yv2;
						planets.get(i2).sety(y2);
						System.out.println("yv = " + yv + " yv2 = " + yv2);
						System.out.println("in 4");
					}			
				} 			
			}
			
		} else {
			double xv = planets.get(0).getxv();
			double yv = planets.get(0).getyv();
			float x = planets.get(0).getx();
			float y = planets.get(0).gety();	
			x += xv;
			y += yv;
			planets.get(0).sety(y);
			planets.get(0).setx(x);
		}
		
		for(int i = 0; i < planets.size(); i++) {
			double xv = planets.get(i).getxv();
			System.out.println("i = " + i + " xv = " + xv);
			System.out.println("WE ARE RENDINGERING!!!");
			planets.get(i).render();
			
		}
		
		
	//	batch = new SpriteBatch();
		
		
		
		
		
	}


	private boolean validPair(int i, int i2) {
		boolean validPair = false;
		boolean pairChecked = false;
		checkedPair = new ArrayList<int[]>();
		
		pairChecked = pairChecked(i, i2);
		
		if(i != i2 && pairChecked == true) {
			validPair = true;
		} else {
			validPair = false;
		}
		
		int[] pair = {i, i2};
		checkedPair.add(pair);
		
		return validPair;
	}
	
	private boolean pairChecked(int i, int i2){
		boolean valid = true;
		
		for(int i3 = 0; i3 < checkedPair.size(); i3++) {
			int[] pair = checkedPair.get(i3);
			if((pair[0]) == i && (pair[1] == i2) || (pair[0] == i2 && pair[1] == i)){
				valid = false;
			}				
			
		}
		
		
		return valid;
	}
	
	public void createPlanet(int x, int y, int vx, int vy, int r, int mass){
		Planet planet = new Planet(x, y, vx, vy, r, mass, this);
		planets.add(planet); 
	
	}	
	
}











