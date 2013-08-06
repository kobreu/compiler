package location;

public class DefaultLocation implements Location {

	private final int row;
	private final int column;

	public DefaultLocation(int row, int column) {
		this.row = row;
		this.column = column;
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
