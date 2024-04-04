class Sudoku {
	public static void main(String[] args) {
		
	}

	// https://leetcode.com/problems/sudoku-solver/
	boolean sudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        if (check(board, i, j, k)) {
                            board[i][j] = k;
                            boolean x = sudoku(board);
                            if(x) return true;
                            board[i][j] = '.';
                            
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
	// When a choice effect future answers use backtracking
	static boolean check(char[][] board, int row, int col, char target) {
		for(int i = 0; i < board.length; i++) {
			if(board[row][i] == target) {
				return false;
			}

			if(board[i][col] == target) {
				return false;
			}
		}
		int r = row - (row % 3);
		int c = col - (col % 3);
		for(int i = r; i < r + 3; i++){
			for(int j = c; j < c + 3; j++) {
				if(board[i][j] == target) {
					return false;
				}
			}
		} 
		return true;
	}

	//--------------------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/valid-sudoku/
	public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(board[i][j] != '.' && isValid(board, i, j, board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
	static boolean isValid(char[][] board, int row, int col, char target) {
        for (int i = 0; i < board.length; i++) {
            if (i != col && board[row][i] == target) { return true; }
            if (i != row && board[i][col] == target) { return true; }
        }
        int r = row - (row % 3);
        int c = col - (col % 3);
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if ((i != row || j != col) && board[i][j] == target) { return true; }
            }
        }
        return false;
    }
    

}