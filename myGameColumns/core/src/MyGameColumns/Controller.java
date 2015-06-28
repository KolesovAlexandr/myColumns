package MyGameColumns;

public class Controller implements ModelListener {

	private View _view;
	private Model _model;

	@Override
	public void onChange(State state) {
		_view.draw(state);
	}

	public void setView(View view) {
		_view = view;

	}

	public void moveLeft() {
		_model.moveLeft();
	}

	public void moveRight() {
		_model.moveRight();
	}

	public void moveDown() {
		_model.moveDown();
	}
	
	public void dropDown() {
		_model.dropDown();
	}
	
	public void cyclePositionUp() {
		_model.cyclePositionUp();
	}

	public void cyclePositionDown() {
		_model.cyclePositionDown();
	}

	public void setModel(Model model) {
		_model = model;

	}

	public boolean fullField() {
		// TODO Auto-generated method stub
		return _model.fullField();
	}



}
