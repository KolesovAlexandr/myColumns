package com.mygdx.myGameColumns.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.myGameColumns.MyGameColumns;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 400;
		config.height = 750;
		MyGameColumns myGameColumns = new MyGameColumns();
		
		new LwjglApplication(myGameColumns, config);
	}
}
