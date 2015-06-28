package MyGameColumns;

public class View {

	private static final int BOX_SIZE = 45;
	private static final int ORIGIN_X = 40;
	private static final int ORIGIN_Y = 10;
	private static final int ORIGIN_SCORE = 240;
	private static final int ORIGIN_LEVEL = 0;
	private static final int ORIGIN_SL_Y = 20;

	Graphics _graphics;

	public void setGraphics(Graphics graphics) {
		_graphics = graphics;
	}

	public void draw(State state) {
		drawField(state.getField().getData());
		drawFigure(state.getFigure().getData(), state.row, state.col);
		showLevel(state.level);
		showScore(state.score);
	}

	private void showScore(int score) {
		drawString("Score:" + score, ORIGIN_SCORE);
	}

	private void showLevel(int level) {
		drawString("Level:" + level, ORIGIN_LEVEL);
	}

	private void drawFigure(int[] data, int row, int col) {
		for (int i = 0; i < data.length; i++) {
			drawBox(data[i], row + i, col);
		}
	}

	private void drawField(int[][] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				drawBox(data[i][j], i, j);
			}

		}
	}

	protected void drawBox(int colorIndex, int row, int col) {
		_graphics.fillRect(ORIGIN_X + col * BOX_SIZE,
				ORIGIN_Y + row * BOX_SIZE, BOX_SIZE, BOX_SIZE, colorIndex);
	}

	protected void drawString(String string, int x) {
		_graphics.drawString(string, ORIGIN_X + x, ORIGIN_Y + BOX_SIZE * 15
				+ ORIGIN_SL_Y);

	}
}
