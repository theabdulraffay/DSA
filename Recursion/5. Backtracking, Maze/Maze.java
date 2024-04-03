import java.util.ArrayList;
class Maza {
	public static void main(String[] args) {
		int[][] arr = new int[3][3];
		// System.out.println(maze(arr, 0 ,0));
		// System.out.println(maze2(3,3));

		// maze2(arr, 0, 0, "");
		// maze2(3, 3, "");
		// System.out.print(maze3(3, 3, "" ));
		// mazeWithObstacle(3, 3, "" );

		boolean[][] maze = {
			{true, true, true},
			{true, false, true},
			{true, true, true}
		};
		mazeWithObstacle(0, 0, maze, ""); // This got an obstacle names as false in this array, so when ever we hit false this means that we cannot go further (an obstacle is hit)
		
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
	static int maze (int[][] arr, int i, int j) { // This will return all the possible ways to move from a point to another in a maza, i is the no. of rows and j is the no. of columns
		if(i == arr.length - 1 || j == arr[0].length - 1) { // when any of the row or column reaches their end this means they have only one option left to move, if i reaches the last row now this means it can only move right now so we automatically return 1 because that would be our only path. 
			return 1;
		} 
		int c = maze(arr, i + 1, j); // In this call we move a row forward
		int p = maze(arr, i, j + 1); // In this call we move a column forward 

		return c + p;
	} 

	static int maze2 (int i, int j) { // Put size or row and col in i and j and they'll tell you the no. of possible path from 0,0 till end the last block of matrix
		if(i == 1 || j == 1) { 
			return 1;
		} 
		int left = maze2(i - 1, j); // In this call we move a row forward
		int right = maze2(i, j - 1); // In this call we move a column forward 

		return left + right;
	} 

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------

	static void maze2 (int[][] arr, int i, int j, String s) { // This will print all the possible paths to move in a maze to reach your destination which is last block of matrix
		if(i == arr.length - 1 && j == arr[0].length - 1) { 
			System.out.println(s);
			return;
		} 
		if (i != arr.length - 1) {
			maze2(arr, i + 1, j, s +'D');
		}
		if (j != arr[0].length - 1) {
			maze2(arr, i, j+ 1, s + 'R');
		}
	}


	static void maze2 (int i, int j, String s) { // Give the no. of rows and columns to i and j, and it will print all the possible ways to move from first block to last block of matrix
		if(i == 1 && j == 1) { 
			System.out.println(s);
			return;
		} 
		if (i > 1) {
			maze2(i - 1, j, s +'D');
		}
		if (j > 1) {
			maze2(i, j - 1, s + 'R');
		}
	} 

	static ArrayList<String> maze3 (int i, int j, String s) { // Same as above now j returning a arraylist instead of printing
		if(i == 1 && j == 1) { 
			ArrayList<String> list = new ArrayList<String>();
			list.add(s);
			return list;
		} 
		ArrayList<String> newlist = new ArrayList<String>();
		if (i > 1) {
			newlist.addAll(maze3(i - 1, j, s +'D'));
		}
		if (j > 1) {
			newlist.addAll(maze3(i, j - 1, s + 'R'));
		}
		return newlist; 
	} 

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------

	static void diagonalMaze (int i, int j, String s) { // In this maze the pointers can also move diagonal
		if(i == 1 && j == 1) { 
			System.out.println(s);
			return;
		} 
		if (i > 1) {
			diagonalMaze(i - 1, j, s + 'D');
		}

		if (i > 1 && j > 1) {
			diagonalMaze(i - 1, j - 1, s + 'T'); // Here 'T' stands for diagonal move
		}
		if (j > 1) {
			diagonalMaze(i, j - 1, s + 'R');
		}
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------

	static void mazeWithObstacle (int i, int j, boolean[][] maze,  String s) { // Give the no. of rows and columns to i and j, and it will print all the possible ways to move from first block to last block of matrix, but when ever a obstacle hits it will not consider that as a part of that of reursion call or the path for the maze
		if(i == maze.length - 1 && j == maze[0].length - 1) { 
			System.out.println(s);
			return;
		} 
		if(!maze[i][j]) return; // This is where we return from the obstacle, the obstacle is 'false' in this case
		if (i < maze.length - 1) {
			mazeWithObstacle(i + 1, j, maze, s+'D');
		}
		if (j < maze[0].length - 1) {
			mazeWithObstacle(i, j + 1, maze, s+'R');
		}
	} 

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------


}