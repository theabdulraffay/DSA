class NKnights {
	public static void main(String[] args) {
		boolean[][] board = new boolean[4][4];
		System.out.println(knights2(board, 0));
	}
	static void knights(boolean[][] board, int row, int col, int knights) {
		if (knights == 0) {
			display(board);
			System.out.println();
			return;
		}
		if(row == board.length - 1 && col == board.length) {
			return;
		}

		if (col == board.length) {
			knights(board, row + 1, 0, knights);
			return;
		}

		if (check(board, row, col)) {
			board[row][col] = true;
			knights(board, row, col + 1, knights - 1);
			board[row][col] = false;
		}
		knights(board, row, col + 1, knights);
	}

	static int knights2(boolean[][] board, int r) {
		if(r == board.length) {
			display(board);
			System.out.println();
			return 1;
		}

		int count = 0;
		for (int i = 0; i < board[0].length; i++) {
			if(check(board, r, i)) {
				board[r][i] = true;
				count += knights2(board, r + 1);
				board[r][i] = false;
			}
		}
		return count;
	}

	static boolean check(boolean[][] board, int row, int col) {
		if(isValid(board, row - 1, col - 2)) {
			if(board[row - 1][col - 2]) {
				return false;
			}
		}

		if(isValid(board, row - 1, col + 2)) {
			if(board[row - 1][col + 2]) {
				return false;
			}
		}

		if(isValid(board, row - 2, col - 1)) {
			if(board[row - 2][col - 1]) {
				return false;
			}
		}

		if(isValid(board, row - 2, col + 1)) {
			if(board[row - 2][col + 1]) {
				return false;
			}
		}

		return true;

	}

	static boolean isValid(boolean[][] board, int row, int col) {
		return (row >= 0 && row < board.length && col >= 0 && col < board.length);
	}
	static void display (boolean[][] board) {
		for (boolean[] i : board) {
			for (boolean element : i) {
				if(element) {
					System.out.print("K ");
				} else {
					System.out.print("X ");
				}
			}
			System.out.println();
		}
	}

}