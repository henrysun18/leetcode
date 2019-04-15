import java.util.LinkedList;
import java.util.Queue;

class IsGraphBipartite {
	public boolean isBipartite(int[][] graph) {
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
