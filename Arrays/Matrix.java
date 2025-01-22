
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Matrix {
    // https://leetcode.com/problems/map-of-highest-peak/
    public int[][] highestPeak(int[][] isWater) {
        int n = isWater.length;
        int m = isWater[0].length;
        int[][] arr = new int[n][m];
        Queue<int[]> que = new LinkedList<>();
        for (int i = 0 ;i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = -1;
                if (isWater[i][j] == 1) {
                    arr[i][j] = 0;
                    que.add(new int[]{i, j});
                }
           }
        }

        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};

        while(!que.isEmpty()) {
            int[] r = que.poll();
            int x = r[0];
            int y = r[1];
            for (int[] direction : dir) {
                int nx = x + direction[0];
                int ny = y + direction[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny] == -1) {
                    arr[nx][ny] = arr[x][y] + 1;
                    que.add(new int[] {nx, ny});
                }
            }
        }
        return arr;
    }

    //-------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/trapping-rain-water-ii/
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) 
            return 0;
            
        int m = heightMap.length;
        int n = heightMap[0].length;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        boolean[][] visited = new boolean[m][n];
        
        for (int j = 0; j < n; j++) {
            pq.offer(new int[]{heightMap[0][j], 0, j});
            pq.offer(new int[]{heightMap[m-1][j], m-1, j});
            visited[0][j] = visited[m-1][j] = true;
        }
        
        for (int i = 1; i < m-1; i++) {
            pq.offer(new int[]{heightMap[i][0], i, 0});
            pq.offer(new int[]{heightMap[i][n-1], i, n-1});
            visited[i][0] = visited[i][n-1] = true;
        }
        
        int water = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int height = curr[0], row = curr[1], col = curr[2];
            
            for (int k = 0; k < 4; k++) {
                int newRow = row + dx[k];
                int newCol = col + dy[k];
                
                if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n || 
                    visited[newRow][newCol]) continue;
                    
                if (heightMap[newRow][newCol] < height) {
                    water += height - heightMap[newRow][newCol];
                    pq.offer(new int[]{height, newRow, newCol});
                } else {
                    pq.offer(new int[]{heightMap[newRow][newCol], newRow, newCol});
                }
                visited[newRow][newCol] = true;
            }
        }
        return water;
    }


}