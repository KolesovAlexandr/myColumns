package com.mygdx.myGameColumns;

import com.badlogic.gdx.Game;


public class MyGameColumns extends Game {
	public ColumnsScreen _columnsScreen;

	@Override
	public void create() {
		_columnsScreen = new ColumnsScreen();
		setScreen(_columnsScreen);
	}
	
}
