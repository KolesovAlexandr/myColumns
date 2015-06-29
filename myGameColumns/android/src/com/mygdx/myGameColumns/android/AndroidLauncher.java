package com.mygdx.myGameColumns.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy.MeasuredDimension;
import com.mygdx.myGameColumns.Box;
import com.mygdx.myGameColumns.MyGameColumns;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		
		initialize(new MyGameColumns(), config);
	}
}
