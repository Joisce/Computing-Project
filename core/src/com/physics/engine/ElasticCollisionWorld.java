package com.physics.engine;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Scanner;

public class ElasticCollisionWorld {

	
	private SpriteBatch batch;
	boolean keyPressed;
	Rectangle rect;
	Rectangle r;
	BitmapFont text;
	Menu menu;
	PhysicsMain physMain;
	private ArrayList<int[]> checkedPair;
	int collisionCount;
	public static ArrayList<Rectangle> rectangles;
	
	public ElasticCollisionWorld(Menu menu){
		this.menu = menu;
	}
	
	public void setUpRandom() {
		
		rectangles = new ArrayList<Rectangle>();
		//Scanner bucky = new Scanner(System.in);
		
		//char selection;
	//	
	//	batch = new SpriteBatch();

		//BitmapFont font = new BitmapFont(Gdx.files.internal("text.fnt"));

		
		
		//System.out.println("Press R for randomly generated rectangles and press U for user input data");
		//selection = bucky.next().charAt(0);
		//selection = Character.toUpperCase(selection);
		
		//if(selection == Keys.ESCAPE) {
		//	menu.ecRunning = false;
		//}
		
		//if(selection == 'R') {
			int rectNum = randInt(9, 10);
			for(int i = 0; i < rectNum; i++) {
				int width = randInt(80, 120);
				int height = randInt(80, 120);
				int x = randInt(0, 2300);
				int y = randInt(0, 1100);
				int xv = randInt(-8, 8);
				int yv = randInt(-8, 8);
				
				createRect(x, y, xv, yv, width, height);
			}
	}
	
	public void setUpUser() {
		

		//Scanner bucky = new Scanner(System.in);
		//System.out.println("How many rectangles?");
		
		
		//System.out.println("numRectz = " + numRect);
		
		System.out.println("interesting");
		for(int i = 0; i < 5; i++) {
			int width = randInt(80, 120);
			int height = randInt(80, 120);
			int x = randInt(0, 2300);
			int y = randInt(0, 1100);
			int xv = randInt(-8, 8);
			int yv = randInt(-8, 8);
			System.out.println("Very");
			createRect(x, y, xv, yv, width, height);
			System.out.println("REALLY?");
			
		}
		
		
		
		
		
		//int numRect;
		
		/*
		batch.begin();

		GlyphLayout layout = new GlyphLayout();
		String str = "Press R for randomly generated rectangles and press U for user input data";

		font.setColor(102/255f, 255/255f, 102/255f, 1f);
		layout.setText(font, str);
		font.draw(batch, str, 100, 100);

		batch.end();	
		
		
		numRect = bucky.nextInt();
		
		for(int i = 0; i < numRect; i++) {
		
			System.out.println("Enter the width for rectangle " + (i + 1));
			int width = bucky.nextInt();
		
			System.out.println("Enter the hight for rectangle " + (i + 1));
			int height = bucky.nextInt();
		
			System.out.println("Enter the starting x coordinate for rectangle " + (i + 1));
			int x = bucky.nextInt();
			
			System.out.println("Enter the starting y coordinate for rectangle " + (i + 1));
			int y = bucky.nextInt();
		
			System.out.println("Enter the starting velocity in the x direction for rectangle " + (i + 1));
			int xv = bucky.nextInt();
		
			System.out.println("Enter the starting velocity in the y direction for rectangle " + (i + 1));
			int yv = bucky.nextInt();
			
			createRect(x, y, xv, yv, width, height);
			}
			*/
		}
	
	private int randInt(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	public int getIndexPos(Rectangle rectangle) {
		return rectangles.indexOf(rectangle);
	}
	
	public void resetValues() {
		collisionCount = 0;
	}
	
	public void collisionCheck() {
		checkedPair = new ArrayList<int[]>();
		if(keyPressed == false) {
			for(int i = 0; i < rectangles.size(); i++) {
			
				for(int i2 = 0; i2 < rectangles.size(); i2++) {		
				
					if(i != i2 && checkPair(i, i2) == true) {
						checkCollisions(i, i2);
					
						int[] pair = {i, i2};
						checkedPair.add(pair);
					}
				
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
	
	private void printCollisionCount() {
		batch = new SpriteBatch();
		BitmapFont font = new BitmapFont(Gdx.files.internal("text.fnt"));
		
		batch.begin();
		GlyphLayout layout = new GlyphLayout();
		String str = "collision count = " + collisionCount;
		font.setColor(0/255f, 0/255f, 0/255f, 1f);
		layout.setText(font, str);
		font.draw(batch, str, 1150, 1000);
		batch.end();
	}
	
	private void checkCollisions(int i, int i2) {
		int x1 = rectangles.get(i).getx();
		int x2 = rectangles.get(i2).getx();
		int y1 = rectangles.get(i).gety();
		int y2 = rectangles.get(i2).gety();
		int width1 = rectangles.get(i).getWidth();
		int width2 = rectangles.get(i2).getWidth();
		int height1 = rectangles.get(i).getHeight();
		int height2 = rectangles.get(i2).getHeight();
		int deltaX;
		int deltaY;
		boolean xRange = false;
		boolean yRange = false;
		
		if(x1 < x2) {
			deltaX = x2 - x1;
			if(deltaX <= width1) {
				xRange = true;
			}
		} else {
			deltaX = x1 - x2;
			if(deltaX <= width2) {
				xRange = true;
			}
		}
		
		if(y1 < y2) {
			deltaY = y2 - y1;
			if(deltaY <= height1) {
				yRange = true;
			}
		} else {
			deltaY = y1 - y2;
			if(deltaY <= height2) {
				yRange = true;
			}
		}
		
		
		if(xRange && yRange == true) {
			collisionCount ++;
			
			if(deltaX > deltaY) {
				System.out.println("x col " + i + " " + i2);
				
				if(x1 < x2) {
					int overlap = width1 - deltaX;
					rectangles.get(i).setxCollision(x1 - overlap/2);
					rectangles.get(i2).setxCollision(x2 + overlap/2);
				} else {
					int overlap = width2 - deltaX;
					rectangles.get(i).setxCollision(x1 + overlap/2);
					rectangles.get(i2).setxCollision(x2 - overlap/2);
				}	
				xCol(i, i2);
				
			} else {
				System.out.println("y col " + i + " " + i2);
				if(y1 > y2) {
					int overlap = height2 - deltaY;
					rectangles.get(i).setyCollision(y1 + overlap/2);
					rectangles.get(i2).setyCollision(y2 - overlap/2);
				} else {
					int overlap = height1 - deltaY;
					rectangles.get(i).setyCollision(y1 - overlap/2);
					rectangles.get(i2).setyCollision(y2 + overlap/2);
				}
				yCol(i, i2);
			}	
			
		}
		
	}
	
	private void xCol(int i, int i2) {
		int xv = rectangles.get(i).getxv();	
		int xv2 = rectangles.get(i2).getxv();		
		int txv = xv;		
		rectangles.get(i).setxvObjectCollision(xv2);
		rectangles.get(i2).setxvObjectCollision(txv);
	}

	private void yCol(int i, int i2) {
		int yv = rectangles.get(i).getyv();
		int yv2 = rectangles.get(i2).getyv();
		int tyv = yv;
		rectangles.get(i).setyvObjectCollision(yv2);
		rectangles.get(i2).setyvObjectCollision(tyv);	
		
	}
	
	public void update() {

		for(int i = 0; i < rectangles.size(); i++) {
			rectangles.get(i).update();
		}
		printCollisionCount();
		collisionCheck();
				
	}
		
	
	public static float getRectSize(){
		return rectangles.size();
	}
	
	public void createRect(int x, int xv, int y, int yv, int widthRec, int heightRec){
		System.out.println("huh  wow");
		Rectangle rect = new Rectangle(x, xv, y, yv, heightRec, widthRec, this);
		
		
		System.out.println("huh wot");
		rectangles.add(rect); 
		System.out.println("huh");
	}
}







