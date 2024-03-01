public class GameBoard {

	private int rows;
	private int columns;
	private Piece[][] board;

	public GameBoard(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;

		board = new Piece[rows][columns];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				board[row][col] = null;
			}
		}
	}

	public Piece[][] getOurBoard() {
		return board;
	}

	public int getColumns() {
		return columns;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int placePiece(int col, String color) {
		int choiceRow = 0;
		if (checkInput(col)) {
			choiceRow = lowerPiece(col, color);
			return choiceRow;
		}
		else {
			return -1;
		}
	}
	
	private boolean checkInput(int chosenCol) {
		if (chosenCol >= 0 && chosenCol <= columns) {
			return board[0][chosenCol] == null;
		}
			//not a valid column
			return false;
	}
	
	public int lowerPiece(int col, String color) {
		int rowCounter = 5;
		boolean open = true;
		//this loop will run while the spot isn't a .
		while (open) {
			if (board[rowCounter][col] == null) {
				board[rowCounter][col] = new Piece();
				board[rowCounter][col].setColor(color);
				open = false;
			} else {
				rowCounter--;
			}
		}
		return rowCounter;
	}
	
}
