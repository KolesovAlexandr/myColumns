package MyGameColumns;

import java.util.Random;

public class Figure {

	int[] _data;
	static Random random = new Random();

	public Figure() {
		_data = new int[3];
	}

	public static Figure randomFigure() {

		int [] figureData = new int[3];
		for (int i = 0; i < figureData.length; i++) {
			figureData[i] =(int) (Math.abs(random.nextInt()) % 7 + 1);
		}

		Figure figure = new Figure();
		figure._data = figureData;
		return figure;

	}

	public static Figure testFigure() {
		int[] figureData = { 1, 2, 3 };
		Figure figure = new Figure();
		figure._data = figureData;
		return figure;
	}

	public int[] getData() {
		return _data;
	}

}
