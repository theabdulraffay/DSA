import java.util.Arrays;
class AllPaths{
	public static void main(String[] args) {
		boolean[][] maze = {
			{true, true, true},
			{true, true, true},
			{true, true, true}
		};
		printallpaths(new int[3][3], 0, 0, "", 1);
		
	}

	static void allpaths (int i, int j, boolean[][] maze,  String s) { // Give the no. of rows and columns to i and j, and it will print all the possible ways to move from first block to last block of matrix, but when ever a obstacle hits it will not consider that as a part of that of reursion call or the path for the maze
		if(i == maze.length - 1 && j == maze[0].length - 1) { 
			System.out.println(s);
			return;
		} 
		if(!maze[i][j]) return; // This is where we return from the obstacle 
		maze[i][j] = false; // mark this as false so that when we traverse it will not get executed due to if statement above
		if (i < maze.length - 1) {
			allpaths(i + 1, j, maze, s+'D'); // move down
		}
		if (j < maze[0].length - 1) {
			allpaths(i, j + 1, maze, s+'R'); // move right
		}
		if (i > 0) { // Lets say we donot change the maze to false then this will form an infinite recursion calls
			allpaths(i - 1, j, maze, s+'U'); // move up
		}
		if(j > 0) {
			allpaths(i, j - 1, maze, s+'L'); // move left
		}

		// Before the func gets over changed the maze values to its original values which is 'true' in this case
		maze[i][j] = true;

	} 
	// for each recursion call we make some changes in the array to let know the future recursin calls that this path is already taken, but when the teminal condition is met and as we move backward from the stack the changse that were made are reduced back to the origional state (change the canges that werre changed previously)in which they were for the future recrsion calls this is backtracking 


	static void printallpaths(int[][] arr, int i, int j, String s, int counter) {
		if (i == arr.length - 1 && j == arr[0].length - 1) {
			arr[i][j] = counter;
			for (int[] xp : arr) {
				System.out.println(Arrays.toString(xp));
			}
			System.out.println(s);
			System.out.println("-------------------------------------");
			return;
		}
		if (arr[i][j] != 0) return;
		arr[i][j] = counter++;
		if (i < arr.length - 1) {
			printallpaths(arr, i + 1, j, s + 'D', counter);
		}

		if (j < arr[0].length - 1) {
			printallpaths(arr, i, j + 1, s + 'R', counter);
		}

		if (i > 0) {
			printallpaths(arr, i - 1, j, s + 'U', counter);
		}

		if (j > 0) {
			printallpaths(arr, i, j - 1, s + 'L', counter);
		}
		arr[i][j] = 0;
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/word-search/
    public static boolean exist(char[][] board, String word) {
        boolean result;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    result = helper(board, i, j, word, 0);
                    if (result)
                        return true;
                }
            }
        }
        return false;
    }
    static boolean helper(char[][] board, int i, int j, String word, int index) {
        if (index == word.length())
            return true;
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 ||
                board[i][j] == '*' || board[i][j] != word.charAt(index)) {
            return false;
        }
        char temp = board[i][j];
        board[i][j] = '*';
        if (helper(board, i + 1, j, word, index + 1) ||
            helper(board, i - 1, j, word, index + 1) ||
            helper(board, i, j + 1, word, index + 1) ||
            helper(board, i, j - 1, word, index + 1)) {
                return true;
        }

        board[i][j] = temp;
        return false;
    }
}