package com.akash.jumpybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.akash.jumpybird.FlappyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Flappy Clone";
		config.width = 272;
		config.height = 408;
		new LwjglApplication(new FlappyGame(), config);
	}
}
