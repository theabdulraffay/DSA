import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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

        // int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};

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

    //---------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/course-schedule-iv/
    boolean BFS(ArrayList<Integer>[] arr, int start, int end) {
        Queue<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[arr.length];
        if (arr[start] != null)
        que.addAll(arr[start]);
        while (!que.isEmpty()) {
            int req = que.poll();
            if(visited[req]) continue;
            if (req == end) return true;
            visited[req] = true;
            if (arr[req] != null) que.addAll(arr[req]);
        }
        return false;
    }
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        ArrayList<Integer>[] arr = new ArrayList[numCourses];
        List<Boolean> toret = new ArrayList<>();
        for (int[] i : prerequisites) {
            int pre = i[0], sub = i[1];
            if (arr[pre] == null) arr[pre] = new ArrayList<>();
            arr[pre].add(sub);
        }
        for(int[] i : queries) {
            toret.add(BFS(arr, i[0], i[1]));
        }
        return toret;
    }

    //---------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/
    private int bfs(
        int startNode,
        Set<Integer> visitedNodes,
        List<List<Integer>> reversedGraph
    ) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { startNode, 0 });
        int maxDistance = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int currentDistance = current[1];
            for (int neighbor : reversedGraph.get(currentNode)) {
                if (visitedNodes.contains(neighbor)) continue; // Skip already visited nodes
                visitedNodes.add(neighbor);
                queue.offer(new int[] { neighbor, currentDistance + 1 });
                maxDistance = Math.max(maxDistance, currentDistance + 1);
            }
        }
        return maxDistance;
    }

    public int maximumInvitations(int[] favorite) {
        int numPeople = favorite.length;
        List<List<Integer>> reversedGraph = new ArrayList<>(numPeople);
        for (int i = 0; i < numPeople; i++) {
            reversedGraph.add(new ArrayList<>());
        }
        for (int person = 0; person < numPeople; person++) {
            reversedGraph.get(favorite[person]).add(person);
        }

        int longestCycle = 0;
        int twoCycleInvitations = 0;
        boolean[] visited = new boolean[numPeople];

        for (int person = 0; person < numPeople; person++) {
            if (!visited[person]) {
                Map<Integer, Integer> visitedPersons = new HashMap<>();
                int currentPerson = person;
                int distance = 0;
                while (true) {
                    if (visited[currentPerson]) break;
                    visited[currentPerson] = true;
                    visitedPersons.put(currentPerson, distance++);
                    int nextPerson = favorite[currentPerson];

                    if (visitedPersons.containsKey(nextPerson)) {
                        int cycleLength =
                            distance - visitedPersons.get(nextPerson);
                        longestCycle = Math.max(longestCycle, cycleLength);

                        if (cycleLength == 2) {
                            Set<Integer> visitedNodes = new HashSet<>();
                            visitedNodes.add(currentPerson);
                            visitedNodes.add(nextPerson);
                            twoCycleInvitations +=
                                2 +
                                bfs(nextPerson, visitedNodes, reversedGraph) +
                                bfs(currentPerson, visitedNodes, reversedGraph);
                        }
                        break;
                    }
                    currentPerson = nextPerson;
                }
            }
        }
        return Math.max(longestCycle, twoCycleInvitations);
    }

    //---------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/
    public int magnificentSets(int n, int[][] edges) {
        // int n = edges.length;
        ArrayList<Integer>[] arr = new ArrayList[n];
        for(int i = 0; i < n; i++) arr[i] = new ArrayList<>();
        for (int[] i : edges) {
            arr[i[0] - 1].add(i[1] - 1);
            arr[i[1] - 1].add(i[0] - 1);
        }

        int[] colors = new int[n];
        Arrays.fill(colors, -1);
        for (int i = 0; i < n; i++) {
            if (colors[i] != -1) continue;
            colors[i] = 0;
            if (!isBarpartite(arr, i, colors)) return -1;
        }
        int max = 0;
        int[] distance = new int[n];
        for(int i = 0; i < n; i++) {
            distance[i] = longestDistance(arr, i, n);
        }

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if(visited[i]) continue;
            max += numberOfGroups(arr, i, distance, visited);
        }
        return max;

        
    }

    boolean isBarpartite(ArrayList<Integer>[] arr, int node, int[] colors) {
        for (int i : arr[node]) {
            if(colors[i] == colors[node]) return false;
            if (colors[i] != -1) continue;
            colors[i] = (colors[node] + 1) % 2;
            if (!isBarpartite(arr, i, colors)) return false;
        }
        return true;
    }


    int longestDistance(ArrayList<Integer>[]arr, int node, int n) {
        Queue<Integer> que = new LinkedList<Integer>();
        boolean[] visited = new boolean[n];

        que.add(node);
        visited[node] = true;
        int distance = 0;
        while(!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int curr = que.poll();
                for (int adj : arr[curr]) {
                    if(visited[adj]) continue;
                    visited[adj] = true;
                    que.add(adj);
                }
            }
                distance++;
        }
        return distance;
    } 

    int numberOfGroups(ArrayList<Integer>[] arr, int i, int[] distances, boolean[] visited) {
        int max = distances[i];
        visited[i] = true;

        for(int adj : arr[i]) {
            if (visited[adj]) continue;
            max = Math.max(max, numberOfGroups(arr, adj, distances, visited));
        }
        return max;
    }

    //---------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------


}