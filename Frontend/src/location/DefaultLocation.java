package location;

public class DefaultLocation implements Location {

	private int row;
	private int column;

	public DefaultLocation(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	@Override
	public int getFileName() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}

}
