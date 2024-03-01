import java.util.Random;

public class ConnectFourGameLogic {
	private GameBoard board;
    private final String colorOne;
    private final String colorTwo;
    private static final int FOUR_IN_ROW = 4;
    private int rowPostion;
	private Random randomG;
    
    // true if player 1's turn
    // false if player 2's turn
    private boolean isOneplaying;
    

    public ConnectFourGameLogic(String color1, String color2, int rows, int columns) {
        this.board = new GameBoard(rows, columns);
        this.colorOne = color1;
        this.colorTwo = color2;
		randomG = new Random();
		isOneplaying = randomG.nextBoolean();

    }
    
    public boolean isPlayerOneplaying() {
        return isOneplaying;
    }
    
    public GameBoard getBoard() {
		return board;
    }
    
    public int round(int col) {
        String color = isPlayerOneplaying() ? colorOne : colorTwo;
        rowPostion = board.placePiece(col, color);
        isOneplaying = !isOneplaying;
        return rowPostion;
    }
    
	public boolean checkForWinnerInGUI(int column) {
		boolean won = checkWinPoint(board.getOurBoard(), rowPostion, column);
		if (!won) {
			return checkForWinner();
		} else {
			return won;
		}
	}
    
	private boolean checkForWinner() {
		Piece[][] pieceBoard = board.getOurBoard();
		//this loop will run for all the board's rows
		for (int i = 0; i < board.getRows(); i++) {
			//this loop will run for all the board's columns
			for (int j = 0; j < board.getColumns(); j++) {
				if (pieceBoard[i][j] != null) {
					boolean win = checkWinPoint(pieceBoard, i, j);
					if (win) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean checkWinPoint(Piece[][] board, int rowPlaced, int colPlaced) {
		int[] xDirections = { 1, -1, 1, 0, -1};
		int[] yDirections = { 0, -1, -1, -1, 0};
		int i = 0;
		//this loop will run for the length of directions which is 4
		
		while (i < xDirections.length) {
			int counter = 1;
			//this loop will run while the direction is in bounds and the pieces adjacent are equal
				while (isValid(board, colPlaced, rowPlaced, xDirections, yDirections, i, counter) &&
						board[rowPlaced][colPlaced].getColor().equals(board[rowPlaced + yDirections[i] * counter]
								[colPlaced + xDirections[i] * counter].getColor())) {
					
					boolean samePiece = board[rowPlaced][colPlaced].getColor().equals(board[rowPlaced + yDirections[i] * counter]
							[colPlaced + xDirections[i] * counter].getColor());
					if (samePiece) {
						counter++;
					}
					if (counter == FOUR_IN_ROW) {
						return true;
					}
				}
				i++;
			}
		return false;
	}
	
	private boolean isValid(Piece[][] board, int colPlaced, int rowPlaced, int[] xDirections, 
			int[] yDirections, int i, int counter) {
		if (colPlaced + xDirections[i] * counter < 0 || 
				colPlaced + xDirections[i] * counter > (board.length - 1)) {
			return false;
		} else if (rowPlaced + yDirections[i] * counter < 0 || 
				rowPlaced + yDirections[i] * counter > (board[0].length - 1)) {
			return false;
		}
		else if (board[rowPlaced][colPlaced] == null || board[rowPlaced + yDirections[i] * counter]
				[colPlaced + xDirections[i] * counter] == null) {
			return false;
		}
		return true;
	}

    public void reset(int rows, int columns) {
        this.board = new GameBoard(rows, columns);
        isOneplaying = randomG.nextBoolean();
    }
    
}