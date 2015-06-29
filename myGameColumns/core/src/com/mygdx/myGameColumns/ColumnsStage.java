package com.mygdx.myGameColumns;

import MyGameColumns.Controller;
import MyGameColumns.Model;
import MyGameColumns.View;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AddListenerAction;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;

public class ColumnsStage extends Stage {
	protected static final int ORIGIN_X = 50;
	private OrthographicCamera camera;
//	float height = Gdx.graphics.getHeight();
//	float width = Gdx.graphics.getWidth();
//	float CAMERA = height*2/width; 
	public ColumnsStage() {
		camera = new OrthographicCamera();
		camera.zoom = 1f;
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
				Text text = new Text(x+50, 50, string);
				ColumnsStage.this.addActor(text);

			}

		};
		controller.setView(view);
		controller.setModel(model);
		final int level=0;
		System.out.println(level);
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				
				controller.moveDown();
				
			}
		}, 1.0f, 1.0f/(level+1));

		Gdx.input.setInputProcessor(this);
		addListener(new ActorGestureListener(){
			@Override
			public void pan(InputEvent event, float x, float y, float deltaX,
					float deltaY) {
				if(deltaX > 20 ) {
					controller.moveRight();
				}
				if (deltaX<-20) {
					controller.moveLeft();
				}
				if (deltaY>20){
					controller.cyclePositionDown();
				}
				if (deltaY<-20){
					controller.cyclePositionUp();
				}
				
			}
			@Override
			public void tap(InputEvent event, float x, float y, int count,
					int button) {
				controller.dropDown();
			}
		});
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
				case Input.Keys.PLUS:
					controller.levelUp();
					break;
				case Input.Keys.MINUS:
					controller.levelDown();
					break;

				}
				return true;
			}

		});

	}

}
