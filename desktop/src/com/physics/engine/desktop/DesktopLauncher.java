package com.physics.engine.desktop;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.physics.engine.PhysicsMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//DisplayMode displayMode = LwjglApplicationConfiguration.getDesktopDisplayMode();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Collin the Physics Engine by Richard";
		//config.setFromDisplayMode(displayMode);
		config.foregroundFPS = 60;
		config.width = 1200;
		config.height = 1000;
		//config.fullscreen = true;
		config.vSyncEnabled = true;
		new LwjglApplication(new PhysicsMain(config.width, config.height), config);
	}
}
