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
	private float posX, posY;
	private String fontText;
	Texture texture;
	{
		Pixmap image = new Pixmap(50, 15, Pixmap.Format.RGBA8888);
		image.setColor(0.5f, 0.5f, 0.5f, 1f);
		image.fill();
		texture = new Texture(image);
	}

	public Text(float posX, float posY, String fontText) {
		this.posX = posX;
		this.posY = posY;
		this.fontText = fontText;

		font = new BitmapFont(true);
		font.setColor(Color.RED);
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(texture, posX, Gdx.graphics.getHeight() - posY, 50, 15);
		font.draw(batch, fontText, posX, Gdx.graphics.getHeight() - posY, 20,
				10, false);

	}
}
