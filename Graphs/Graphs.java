
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Graphs {
    // https://leetcode.com/problems/redundant-connection/
    boolean isCyclicConnected(ArrayList<Integer>[] adj, int s, int V, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);

        while (!q.isEmpty()) {
            int v = q.poll();

            if (visited[v]) {
                return true; // Cycle detected
            }

            visited[v] = true; // Mark as visited

            for (int it : adj[v]) {
                if (!visited[it]) {
                    q.add(it);
                }
            }
        }
        return false;
    }

    boolean isCyclicDisconnected(ArrayList<Integer>[] adj, int V) {
        boolean[] visited = new boolean[V + 1];

        for (int i = 0; i < V; i++) {
            if (!visited[i] && isCyclicConnected(adj, i, V, visited)) {
                return true;
            }
        }
        return false;
    }

    void addEdge(ArrayList<Integer>[] adj, int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    void removeEdge(ArrayList<Integer>[] adj, int u, int v) {
        adj[v].remove(Integer.valueOf(u));
        adj[u].remove(Integer.valueOf(v));
    }

    public int[] findRedundantConnection(int[][] edges) {
        int V = edges.length + 1;
        ArrayList<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] i : edges)
            addEdge(adj, i[0], i[1]);
        for (int i = edges.length - 1; i >= 0; i--) {
            removeEdge(adj, edges[i][0], edges[i][1]);
            if (!isCyclicDisconnected(adj, edges.length))
                return edges[i];
            addEdge(adj, edges[i][0], edges[i][1]);
        }
        return new int[] {};

    }


    //---------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/
    int[][] dir = {{0, 1}, {0, -1}, {1,0}, {-1,0}};
    int helper(int[][] grid, int n, int m) {
        if(grid[n][m] == 0) return 0;
        int max = grid[n][m];
        grid[n][m] = 0;
        for (int[] i : dir) {
            int nx = n + i[0];
            int ny = m + i[1];
            if(nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length) {
                max += helper(grid, nx, ny);
            }
        }

        return max;
    }
    public int findMaxFish(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, helper(grid, i, j)); 
            }
        }
        return max;
    }

    //---------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------


}