package com.mygdx.myGameColumns;

import MyGameColumns.Controller;
import MyGameColumns.Model;
import MyGameColumns.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ColumnsStage extends Stage {
	protected static final int ORIGIN_X = 80;
	private OrthographicCamera camera;

	public ColumnsStage() {
		camera = new OrthographicCamera();
		
		camera.setToOrtho(true);
		setViewport(new ScreenViewport(camera));
	}

	public void init() {
		Model model = new Model();

		final Controller controller = new Controller();

		model.addListener(controller);

		View view = new View() {
			private Box[][] _boxes = new Box[Model.ROWS][Model.COLUMNS];

			@Override
			protected void drawBox(int colorIndex, int row, int col) {
//				camera.setToOrtho(true);
				if (_boxes[row][col] == null) {
					Box box = new Box(colorIndex);
					_boxes[row][col] = box;
					ColumnsStage.this.addActor(box);
					box.setBounds(col * Box.SIZE + ORIGIN_X, row * Box.SIZE,
							Box.SIZE, Box.SIZE);
				}

				_boxes[row][col].setColor(colorIndex);
			}
			

			@Override
			protected void drawString(String string, int x) {
				Text text = new Text(x+10, 200, string);
				ColumnsStage.this.addActor(text);
				
			}

		};
		controller.setView(view);
		controller.setModel(model);

		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				controller.moveDown();
			}
		}, 1.0f, 1.0f);

		Gdx.input.setInputProcessor(this);

		addListener(new InputListener() {

			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				switch (keycode) {
				case Input.Keys.LEFT:
					controller.moveLeft();
					break;
				case Input.Keys.RIGHT:
					controller.moveRight();
					break;
				case Input.Keys.UP:
					controller.cyclePositionUp();
					break;

				case Input.Keys.DOWN:
					controller.cyclePositionDown();
					break;
				case Input.Keys.SPACE:
					controller.dropDown();
					break;

				}
				return true;
			}
			@Override
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				// TODO Auto-generated method stub
				super.touchDragged(event, x, y, pointer);
			}

		});

			
	}

}
