package com.mygdx.myGameColumns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Text extends Actor {

	private BitmapFont font;
	private float _posX, _posY;
	private String _fontText;
	Texture texture;
	{
		Pixmap image = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
		image.setColor(0.5f, 0.5f, 0.5f, 1f);
		image.fill();
		texture = new Texture(image);
	}

	public Text(float posX, float posY, String fontText) {
		_posX = posX;
		_posY = posY;
		_fontText = fontText;

		font = new BitmapFont(true);
		
		font.setColor(Color.RED);
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(texture, _posX, Gdx.graphics.getHeight() - _posY, 100, 100);
		font.draw(batch, _fontText, _posX, Gdx.graphics.getHeight() - _posY);

	}
}
