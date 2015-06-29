package MyGameColumns;

public class Logic {
	private static final int MAXLEVEL = 7;
	private static final int FIGTODROP = 33;
	State _state;
	
	private int DSCore;
	private int k;

	public Logic(State state) {
		_state = state;
	}

	public boolean moveRight() {
		_state.col++;
		if (!isFigureFitsField() | fullField()) {
			_state.col--;
			return false;
		}
		return true;
	}

	public boolean moveLeft() {
		_state.col--;
		if (!isFigureFitsField() | fullField()) {
			_state.col++;

			return false;
		}
		return true;
	}

	public boolean moveDown() {
		_state.row++;
		if (!isFigureFitsField()) {
			_state.row--;

			if (fullField()) {
				_state.gameOver();
				return false;
			}
			pasteFigure();
			if (testField()) {
				PackField();
			}
			newFigure();
			return true;
		}
		return true;
	}

	public void cyclePositionUp() {
		int[] data = _state._figure._data;
		int[] tmpData = new int[data.length];
		if (fullField()) {
			return;
		}
		for (int i = 0; i < data.length; i++) {
			int j = (i + 1) % data.length;
			tmpData[i] = data[j];
		}
		for (int i = 0; i < data.length; i++) {
			data[i] = tmpData[i];
		}

	}

	public void cyclePositionDown() {
		int[] data = _state._figure._data;
		int[] tmpData = new int[data.length];
		if (fullField()) {
			return;
		}
		for (int i = 0; i < data.length; i++) {
			int j = (i + data.length - 1) % data.length;
			tmpData[i] = data[j];
		}
		for (int i = 0; i < data.length; i++) {
			data[i] = tmpData[i];
		}

	}

	public void dropDown() {
		int Depth = _state._field._data.length;
		int zz = Depth;
		int level = _state.level;

		do {
			_state.row++;
			zz--;
		} while (isFigureFitsField());
		if (!isFigureFitsField()) {
			zz++;
			_state.row--;
			DSCore = (((level + 1) * (Depth * 2 - _state.row - zz) * 2) % 5) * 5;
			if (fullField()) {
				_state.gameOver();
				return;
			}
			pasteFigure();
			if (testField()) {
				PackField();
				_state.score += DSCore;
			}

			newFigure();
		}
	}

	private boolean testField() {
		int[][] data = _state._field.getData();
		boolean isCheckNeighbours = false;

		for (int r = 0; r < data.length; r++) {
			for (int c = 0; c < data[r].length; c++) {

				if (data[r][c] == 0) {
					continue;
				}
				if (r > 0 && r < data.length - 1) {
					isCheckNeighbours = isCheckNeighbours
							| checkNeighbours(r, c, r - 1, c, r + 1, c);
				}
				if (c > 0 && c < data[r].length - 1) {
					isCheckNeighbours = isCheckNeighbours
							| checkNeighbours(r, c, r, c - 1, r, c + 1);
				}
				if (r > 0 && c > 0 && r < data.length - 1
						&& c < data[r].length - 1) {
					isCheckNeighbours = isCheckNeighbours
							| checkNeighbours(r, c, r - 1, c - 1, r + 1, c + 1);
				}
				if (r > 0 && c < data[r].length - 1 && r < data.length - 1
						&& c > 0) {
					isCheckNeighbours = isCheckNeighbours
							| checkNeighbours(r, c, r - 1, c + 1, r + 1, c - 1);
				}

			}
		}
		return isCheckNeighbours;

	}

	private boolean checkNeighbours(int r, int c, int r1, int c1, int r2, int c2) {
		int[][] data = _state._field.getData();
		if ((data[r][c] == data[r1][c1]) && (data[r][c] == data[r2][c2])) {
			data[r][c] = data[r1][c1] = data[r2][c2] = 0;
			_state.score += (_state.level + 1) * 10;
			k++;
			return true;
		}
		return false;

	}

	void PackField() {

		int[][] tmpData = new int[15][7];
		int i, j, n;
		int[][] data = _state._field._data;
		System.out.println(data.length + " " + data[0].length);
		for (i = 0; i < data[0].length; i++) {
			n = data.length - 1;
			for (j = data.length - 1; j >= 0; j--) {
				if (data[j][i] > 0) {
					tmpData[n][i] = data[j][i];
					n--;
				}
			}
			;
			for (j = n; j > 0; j--) {
				tmpData[j][i] = 0;
			}
		}
		for (int r = 0; r < data.length; r++) {
			for (int c = 0; c < data[r].length; c++) {
				data[r][c] = tmpData[r][c];

			}

		}

	}

	void newFigure() {
		_state.setFigure(Figure.randomFigure());
		DSCore = 0;
	}

	boolean fullField() {
		int[][] data = _state._field.getData();
		for (int r = 0; r < 7; r++) {
			if (data[r][3] > 0) {
				return true;
			}
		}
		return false;
	}

	private void pasteFigure() {
		int[] figureData = _state._figure.getData();
		int[][] fieldData = _state._field.getData();
		for (int r = 0; r < figureData.length; r++) {
			fieldData[_state.row + r][_state.col] = figureData[r];
		}
		if (k>=FIGTODROP){
			if (_state.level<MAXLEVEL) {
				levelUp();
			}
		}

	}

	public boolean isFigureFitsField() {
		int row = _state.row;
		int col = _state.col;
		if (row < 0
				|| col < 0
				|| row > _state._field.getHeight()
						- _state._figure._data.length
				|| col >= _state._field.getWidth()) {
			return false;
		}
		if (_state._field._data[row + 2][col] > 0) {
			return false;
		}

		return true;

	}

	public State getState() {
		try {
			return (State) _state.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void levelUp() {
		if (_state.level < MAXLEVEL) {
			_state.level++;
		}

	}

	public void levelDown() {
		if (_state.level > 0) {
			_state.level--;

		}

	}
}
