class NQueen {
	public static void main(String[] args) {
		boolean[][] board = new boolean[4][4];
		System.out.print(queens(board, 0));
		
	}
	// theere will not be a single row ans column where two queen can place simultaneously, each queen have a seperate row and col, so no two queens share same row or column
	static int queens (boolean[][] board, int r) { 
		if(r == board.length) {
			display(board);
			System.out.println();
			return 1;
		}

		int count = 0;
		for (int i = 0; i < board[0].length; i++) {
			if(check(board, r, i)) {
				board[r][i] = true;
				count += queens(board, r + 1);
				board[r][i] = false;
			}
		}
		return count;

	}

	static boolean check(boolean[][] board, int r, int c) {
		int row = r;
		int col = c;
		// checking vertically
		while(r >= 0) {
			if (board[r--][c]) return false;
		}

		r = row; // These to ensure everytime it start from the same point 
		c = col;
		// checking left diagonal
		while (r >= 0 && c >= 0) {
			if (board[r--][c--]) return false;
		}

		r = row;
		c = col;
		// checking right diagonal
		while (r >= 0 && c < board.length) {
			if (board[r--][c++]) return false;
		}

		return true;

	}

	static void display (boolean[][] board) {
		for (boolean[] i : board) {
			for (boolean element : i) {
				if(element) {
					System.out.print("Q ");
				} else {
					System.out.print("X ");
				}
			}
			System.out.println();
		}
	}
}