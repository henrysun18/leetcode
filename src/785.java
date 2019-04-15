import java.util.LinkedList;
import java.util.Queue;

class IsGraphBipartite {
	public boolean isBipartite(int[][] graph) {
		int painted[] = new int[graph.length];
		boolean visited[] = new boolean[graph.length];

		int currColour = 1; //colour from 1 to 2

		//for each node, bfs starting from that node and try to 2-colour the graph
		for (int i = 0; i < graph.length; i++) {
			if (visited[i]) continue;

			Queue<Integer> q = new LinkedList<>();
			q.add(i);
			while (!q.isEmpty()) {
				//poll everything from queue (curr layer) and try to colour it opposite of previous colour
				int nodesToPoll = q.size();
				for (int j = 0; j < nodesToPoll; j++) {
					int node = q.poll();
					if (painted[node] == 0) {
						painted[node] = currColour;
					} else if (painted[node] > 0 && painted[node] != currColour) {
						return false;
					}

					if (!visited[node]) {
						//add all of curr layer's neighbours
						int[] neighbours = graph[node];
						for (int neighbour : neighbours) {
							q.add(neighbour);
						}
						visited[node] = true;
					}
				}

				//change paint colour
				currColour = currColour == 1 ? 2 : 1;
			}
		}

		return true;
	}
}
