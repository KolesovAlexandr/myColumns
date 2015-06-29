package com.mygdx.myGameColumns;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Box extends Actor {

	public static int SIZE = 45;

	static Texture[] textures = new Texture[9];

	static Color[] colors = { Color.BLACK, Color.CYAN,
		Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.PINK,
		Color.MAGENTA, Color.BLACK };

	static {

		for (int i = 0; i < colors.length; i++) {
			textures[i] = createTexture(colors[i]);
		}
	}

	private static Texture createTexture(Color color) {
		Texture texture = null;

		Pixmap image = new Pixmap(SIZE, SIZE, Pixmap.Format.RGBA8888);

		image.setColor(color);
		image.fill();

		texture = new Texture(image);

		return texture;
	}


	private Texture _texture;

	public Box(int colorIndex) {
		_texture = textures[colorIndex];
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(_texture, this.getX(), getY(), this.getOriginX(),
				this.getOriginY(), _texture.getWidth(), _texture.getHeight(),
				this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
				_texture.getWidth(), _texture.getHeight(), false, false);
	}

	public void setColor(int colorIndex) {
		_texture = textures[colorIndex];
	}

}
