import java.util.LinkedList;
import java.util.Queue;

class IsGraphBipartite {

	public boolean isBipartiteDFS(int[][] graph) {
		int[] colours = new int[graph.length];

		//for each node, dfs starting from that node and try to 2-colour the graph
		for (int i = 0; i < graph.length; i++) {
			if (colours[i] != 0) continue; //don't try to dfs starting at an already coloured node

			if (!dfs(graph, colours, i, 1)) {
				return false;
			}
		}

		return true;
	}

	private boolean dfs(int[][] graph, int[] colours, int node, int colour) {
		if (colours[node] == -colour) {
			return false; //node already coloured, and it's opposite of what we wanted to colour it, so return false
		}
		if (colours[node] == 0) {
			//colour this one, then dfs neighbours
			colours[node] = colour;
			for (int neighbour : graph[node]) {
				if (!dfs(graph, colours, neighbour, -colour)) {
					return false;
				}
			}
		}

		return true;
	}





	public boolean isBipartiteBFS(int[][] graph) {
		int colour[] = new int[graph.length];

		//for each node, bfs starting from that node and try to 2-colour the graph
		for (int i = 0; i < graph.length; i++) {
			if (colour[i] != 0) continue;

			Queue<Integer> q = new LinkedList<>();
			q.add(i);
			colour[i] = 1; //initialize node at beginning of island with 1, then set all neighbours to -1 and so on

			while (!q.isEmpty()) {
				//poll from queue and try to colour it opposite of previous colour
				int node = q.poll();
				for (int neighbour : graph[node]) {
					if (colour[neighbour] == colour[node]) {
						return false;
					}
					if (colour[neighbour] == 0) {
						colour[neighbour] = -colour[node];
						q.add(neighbour);
					}
				}
			}
		}

		return true;
	}
}
