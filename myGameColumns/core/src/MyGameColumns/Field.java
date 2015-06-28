package MyGameColumns;

public class Field {
	
	int[][] _data;
	public Field(int width,int height) {
		_data = new int[height][width];
	}
	public int getWidth() {
		return _data[0].length;
	}
	public int getHeight() {
		// TODO Auto-generated method stub
		return _data.length;
	}
	public int[][] getData() {
		// TODO Auto-generated method stub
		return _data;
	}

}
 