package com.physics.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;





public class MyTextInputListener implements TextInputListener {
	
	String text;
	Menu menu;
	Label label;
	
	MyTextInputListener(Menu menu){
	this.menu = menu;
	}


   @Override
   public void input (String text) {
	  // menu.setRectNum(text);
   }

   //public void setVal() {
	   
	//   Gdx.input.getTextInput(listener, "Dialog Title", "2", "");
	   
	//   System.out.println("text = " + text);
	//	  menu.setRectNum(text);
	   
	//   System.out.println("text = " + text);
	//   int rectNum = Integer.parseInt(text);
	//   menu.setRectNum(rectNum);
	  
   
   
   @Override
   public void canceled () {
   }
}