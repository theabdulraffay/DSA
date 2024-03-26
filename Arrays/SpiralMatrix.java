import java.util.List;
import java.util.ArrayList;
class SpiralMatrix {
	public static void main(String[] args) {
		
	}
	// https://leetcode.com/problems/spiral-matrix/description/
	public List<Integer> spiralOrder(int[][] matrix) { // Input: matrix = [[1,2,3],[4,5,6],[7,8,9]], Output: [1,2,3,6,9,8,7,4,5]
        int rows = matrix.length;
        int col = matrix[0].length;
        List<Integer> ans = new ArrayList<Integer>();
        int i = 0;
        int stRow = 0;
        int enCol = col - 1;
        int enRow = rows - 1;
        int stCol = 0;
        int t = rows * col;

        while (i < t) {
            for (int j = stRow; j <= enCol; j++) {
                ans.add(matrix[stRow][j]);
                i++;
            }
            stRow++;
            if (i == t) break;
            for (int j = stRow; j <= enRow; j++) {
                ans.add(matrix[j][enCol]);
                i++;
            }
            enCol--;
            if (i == t) break;
            for (int j = enCol; j >= stCol; j--) {
                ans.add(matrix[enRow][j]);
                i++;
            }
            enRow--;
            if (i == t) break;
            for (int j = enRow; j >= stRow; j--) {
                ans.add(matrix[j][stCol]);
                i++;
            }
            stCol++;
            if (i == t) break;
        }
        return ans;
        
    }


	// https://leetcode.com/problems/spiral-matrix-ii/
	public int[][] generateMatrix(int n) { // Input: n = 3, Output: [[1,2,3],[8,9,4],[7,6,5]]

        int[][] ans = new int[n][n];
        int i = 0;
        int stRow = 0;
        int enCol = ans[0].length - 1;
        int enRow = ans.length - 1;
        int stCol = 0;
        n *= n;
        while (i < n) {
            for (int j = stRow; j <= enCol; j++) {
                ans[stRow][j] = ++i;
            }
            stRow++;
            if (i == n)break;

            for (int j = stRow; j <= enRow; j++) {
                ans[j][enCol] = ++i;
            }
            enCol--;
            if (i == n)break;

            for (int j = enCol; j >= stCol; j--) {
                ans[enRow][j] = ++i;
            }
            enRow--;
            if (i == n)break;

            for (int j = enRow; j >= stRow; j--) {
                ans[j][stCol] = ++i;
            }
            stCol++;
            if (i == n)break;

        }
        return ans;
    }
    // https://leetcode.com/problems/spiral-matrix-iv/
    // public int[][] spiralMatrix(int m, int n, LinkedList head) {
    //     int[][] ans = new int[m][n];
    //     for (int[] i : ans) Arrays.fill(i, -1);
    //     int i = 0;
    //     int stRow = 0;
    //     int enCol = ans[0].length - 1;
    //     int enRow = ans.length - 1;
    //     int stCol = 0;
    //     n = n * m;
    //     while (i < n) {
    //         for (int j = stRow; j <= enCol && head != null; j++) {
    //             ans[stRow][j] = head.val;
    //             head = head.next;
    //         }
    //         stRow++;
    //         if (i == n || head == null)break;

    //         for (int j = stRow; j <= enRow && head != null; j++) {
    //             ans[j][enCol] = head.val;
    //             head = head.next;
    //         }
    //         enCol--;
    //         if (i == n || head == null)break;

    //         for (int j = enCol; j >= stCol && head != null; j--) {
    //             ans[enRow][j] = head.val;
    //             head = head.next;
    //         }
    //         enRow--;
    //         if (i == n || head == null)break;

    //         for (int j = enRow; j >= stRow && head != null; j--) {
    //             ans[j][stCol] = head.val;
    //             head = head.next;
    //         }
    //         stCol++;
    //         if (i == n || head == null)break;

    //     }
    //     return ans;
    // }
}