package MyGameColumns;

import java.util.ArrayList;
import java.util.List;

public class Model {

	public static final int ROWS = 15;
	public static final int COLUMNS = 7;
	Logic _logic;

	public Model() {
		Field field = new Field(COLUMNS, ROWS);
		State state = new State();
		state.setField(field);
		state.setFigure(Figure.randomFigure());
		_logic = new Logic(state);
//		_logic.setModel(this);
	}

	List<ModelListener> _listeners = new ArrayList<>();

	public void addListener(final ModelListener listener) {
		_listeners.add(listener);
	}

	public void removeListener(final ModelListener listener) {
		_listeners.remove(listener);
	}

	void fireChangedEvent() {
		for (ModelListener modelListener : _listeners) {
			modelListener.onChange(_logic.getState());
		}

	}

	public void moveLeft() {
		if (_logic.moveLeft()) {
			fireChangedEvent();
		}
	}

	public void moveRight() {
		if (_logic.moveRight()) {
			fireChangedEvent();
		}
	}

	public void moveDown() {
		if (_logic.moveDown()) {
			fireChangedEvent();
		}
	}
	
	public void dropDown() {
		_logic.dropDown();
		fireChangedEvent();
	}

	public void cyclePositionUp() {
		_logic.cyclePositionUp();
		fireChangedEvent();
	}

	public void cyclePositionDown() {
		_logic.cyclePositionDown();
		fireChangedEvent();
	}

	public boolean fullField() {
		// TODO Auto-generated method stub
		return _logic.fullField();
	}

}
