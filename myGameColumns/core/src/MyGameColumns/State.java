package MyGameColumns;

public class State implements Cloneable {
	
	Figure _figure;
	Field _field;
	int row,col;
	int level,score;

	public Figure getFigure() {
		return _figure;
		
	}
	public void setFigure(Figure figure) {
		_figure = figure;
		row = 0;
		col = _field.getWidth()/2;
	}
	public Field getField() {
		return _field;
	}
	public void setField(Field field) {
		_field = field;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
