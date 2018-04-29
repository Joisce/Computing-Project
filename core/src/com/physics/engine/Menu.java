package com.physics.engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class Menu {
	
	//boolean keyPressed = false;
	boolean oPressed = false;
	boolean selection = false;
	boolean cPressed = false;
	boolean mPressed = false;
	boolean ecRunning = false;
	boolean cmRunning = false;
	boolean omRunning = false;
	boolean mocRunning = false;
	boolean vals = false;
	boolean pressed = false;
	
	
	PhysicsMain PhysicsMain;
	OrbitalMotionWorld omWorld;
	ElasticCollisionWorld ecWorld = new ElasticCollisionWorld(this);
	CircularMotionWorld cmWorld;
	MassiveObjectCollisionWorld mocWorld;
	PhysicsMain physMain;
	MyTextInputListener text;
	
	private static SpriteBatch batch;
	int width = (int)(com.physics.engine.PhysicsMain.getWidth());
	int height = (int) (com.physics.engine.PhysicsMain.getHeight());
	int num;
	BitmapFont font = new BitmapFont();
	BitmapFont f = new BitmapFont();
	Menu Menu;
	MyTextInputListener listener = new MyTextInputListener(this);
	
	public Menu() {
		cmWorld = new CircularMotionWorld();
		ecWorld = new ElasticCollisionWorld(this);
		omWorld = new OrbitalMotionWorld();
		mocWorld = new MassiveObjectCollisionWorld();
	}
	
	public void setRectNum(String rectNum) {
		pressed = false;
		num = Integer.parseInt(rectNum);
		System.out.println("num = " + num);
		//vals = false;
		//pressed = false;
		ecWorld.setUpRandom();
		ecRunning = true;
	}
	
	void EcU(){
		
		if (vals == false) {
		Gdx.input.getTextInput(listener, "Dialog Title", "2", "");
		vals = true;
		}
		
		
		
		batch = new SpriteBatch();
		int numRect = 1;
		batch.begin();
		GlyphLayout layout = new GlyphLayout();
		String str = "Enter how many rectangles you want to have in the simulation";
		String r = "Random";
		String u = "User";
		
		
		layout.setText(f, str);
		float strWidth = layout.width;
		int posX2 = (int) ((width / 2) - (strWidth / 2) );
		int posY2 = (int) (height * 0.95);
		f.setColor(0/255f, 0/255f, 0/255f, 1f);
		f.draw(batch, str, posX2, posY2);
		batch.end();
		
		
	}
	
	private void drawText() {
		
		batch = new SpriteBatch();
		
		font = new BitmapFont(Gdx.files.internal("text.fnt"));
		f = new BitmapFont();

		batch.begin();
		
		int x = Gdx.input.getX();
		int y = height - Gdx.input.getY() + 10;

	    //Menu 
		GlyphLayout layout = new GlyphLayout();
		String str = "Menu";
		font.setColor(0/255f, 0/255f, 0/255f, 1f);
		layout.setText(font, str);
		int posX = (int) (width / 2);
		int posY = (int) (height * 0.95);
		font.draw(batch, str, posX, posY);
		
		//EC discription
		String ecDiscription = "Click here for elastic collision simulation - A random or user selected number of rectangles colliding elasticly, without any loss of kinetic energy";
		f.setColor(0/255f, 0/255f, 0/255f, 1f);
		layout.setText(f, ecDiscription);
		
		int posX1 = (int) (width * 1/10);
		int posY1 = (int) (height * 7/10);
		
		float strWidth1 = layout.width;
		float strHeight1 = layout.height;
		if(x > posX1 && x < posX1 + strWidth1) {
			if(y > posY1 && y < posY1 + strHeight1) {
				f.setColor(Color.GRAY);
			}
		}
		
		if(x > posX1 && x < posX1 + strWidth1) {
			if(y > posY1 && y < posY1 + strHeight1) {
				if(Gdx.input.isTouched()) {	
					ecWorld.setUpRandom();
					ecRunning = true;
				}
			}
		}
		
		f.draw(batch, ecDiscription, posX1, posY1);
		
		String ecUDiscription = "Clcik here for the elastic collision simulation but with user input data for the variables";
		f.setColor(0/255f, 0/255f, 0/255f, 1f);
		layout.setText(f, ecUDiscription);
		int posX11 = (int) (width * 1/10);
		int posY11 = (int) (height * 7.5/10);
		
		float strWidth11 = layout.width;
		float strHeight11 = layout.height;
		if(x > posX11 && x < posX11 + strWidth11) {
			if(y > posY11 && y < posY11 + strHeight11) {
				f.setColor(Color.GRAY);
			}
		}
		
		
		if(x > posX11 && x < posX11 + strWidth11) {
			if(y > posY11 && y < posY11 + strHeight11) {
				if(Gdx.input.isTouched()) {	
					pressed = true;
				}
			}
		}
		
		f.draw(batch, ecUDiscription, posX11, posY11);
		
		//MO
		String mocDiscription = "Click here for massive body collisions - A random or user selected number of rectangles of varing mass. All collisions, momentium is concerved";
		f.setColor(0/255f, 0/255f, 0/255f, 1f);
		layout.setText(f, mocDiscription);
		
		int posX2 = (int) (width * 1/10);
		int posY2 = (int) (height * 3/5);
		
		float strWidth2 = layout.width;
		float strHeight2 = layout.height;
		if(x > posX2 && x < posX2 + strWidth2) {
			if(y > posY2 && y < posY2 + strHeight2) {
				f.setColor(Color.GRAY);
			}
		}
		
		if(x > posX2 && x < posX2 + strWidth2) {
			if(y > posY2 && y < posY2 + strHeight2) {
				if(Gdx.input.isTouched()) {
					mocWorld.setUp();
					mocRunning = true;
				}
			}
		}
		
		f.draw(batch, mocDiscription, posX2, posY2);
		
		
		//CM
		String cmDiscription = "Click here for circular motion simulator - An object of random or user selected mass at a fixed distance from a center point";
		f.setColor(0/255f, 0/255f, 0/255f, 1f);
		layout.setText(f, cmDiscription);
		
		int posX3 = (int)(width * 1/10);
		int posY3 = (int)(height * 1/2);
		
		float strWidth3 = layout.width;
		float strHeight3 = layout.height;
		if(x > posX3 && x < posX3 + strWidth3) {
			if(y > posY3 && y < posY3 + strHeight3) {
				f.setColor(Color.GRAY);
			}
		}
		
		if(x > posX3 && x < posX3 + strWidth3) {
			if(y > posY3 && y < posY3 + strHeight3) {
				if(Gdx.input.isTouched()) {
					cmWorld.setUp();
					cmRunning = true;
				}
			}
		}
		
		f.draw(batch, cmDiscription, posX3, posY3);
		
		
		//OM 
		String omDiscription = "Press O for orbital motion simulation - A random or user selected number of planets orbiting a common center of mass";
		f.setColor(0/255f, 0/255f, 0/255f, 1f);
		layout.setText(f, omDiscription);
		
		int posX4 = (int)(width * 1/10);
		int posY4 = (int)(height * 2/5);
		
		float strWidth4 = layout.width;
		float strHeight4 = layout.height;
		if(x > posX4 && x < posX4 + strWidth4) {
			if(y > posY4 && y < posY4 + strHeight4) {
				f.setColor(Color.GRAY);
			}
		}
		
		f.draw(batch, omDiscription, posX4, posY4);
		
		batch.end();
	}
	
	
	public void menuScreen() {
		
		if(cmRunning == false && ecRunning == false && omRunning == false && mocRunning == false && pressed == false) {
			drawText();
		}
		
		if(pressed == true) {
			EcU();
		}

		if(cmRunning == false) {
			if(Gdx.input.isKeyPressed(Keys.C)) {
				cmWorld.setUp();
				cmRunning = true;
			}
		}
		
		if(cmRunning== true) {
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
				cmWorld.remove();
				cmRunning = false;
			}
		}
		
		if(omRunning == false) {
			if(Gdx.input.isKeyPressed(Keys.O)) {
				omWorld.setUp();
				omRunning = true;
			}
		}
		
		if(omRunning == true) {
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
				omRunning = false;
				omWorld.removeAllPlanets();
			}
		}
		
		if(ecRunning == false) {
			if(Gdx.input.isKeyPressed(Keys.ENTER)) {
				ecRunning = true;
				ecWorld.setUpRandom();
			}	
		}
		
		if(ecRunning == true) {
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
				ecRunning = false;
				ecWorld.resetValues();
			}
		}
		
		if(mocRunning == false) {
			if(Gdx.input.isKeyPressed(Keys.M)) {
				mocWorld.setUp();
				mocRunning = true;
			}
		}
		
		if(mocRunning == true) {
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
				mocRunning = false;
				mocWorld.clear();
			}
		}
		
		if(cmRunning == true) {
			cmWorld.update();
		}
		
		if(omRunning == true) {
			omWorld.check();
		}
		
		if(ecRunning == true) {
			ecWorld.update();
		}
		
		if(mocRunning == true) {
			mocWorld.update();
		}
		
		if(selection == false) {
			System.out.println("Press ENTER for elasric collision simulation - A random or user selected number of rectangles colliding elasticly, without any loss of kinetic energy");
			System.out.println(" Press O for orbital motion simulation - A random or user selected number of planets orbiting a common center of mass");
			System.out.println("Press C for circular motion simulator - An object of random or user selected mass at a fixed distance from a center point");
			System.out.println("Press M for massive body collisions - A random or user selected number of rectangles of varing mass. All collisions, momentium is concerved");
			selection = true;
		}
		
	}
}




