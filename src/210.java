import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class CourseScheduleII {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		//need to find topological sort of the graph
		//prerequisite pair [1,0] = directed edge from 0 to 1 (0 -> 1), where 1 depends on 0
		//perform dfs on every node, and only add to stack when all downstream neighbours are visited

		State[] states = new State[numCourses]; //numCourses amount of nodes that we can visit

		//need to build a map of course# and its, list of antireq courses so we can find the downstream neighbours of each node
		Map<Integer, List<Integer>> antireqs = new HashMap<>();

		for (int i = 0; i < numCourses; i++) {
			states[i] = State.UNVISITED;
			antireqs.put(i, new ArrayList<>());
		}

		for (int[] prereq : prerequisites) {
			int edgeStart = prereq[1];
			int edgeEnd = prereq[0];
			antireqs.get(edgeStart).add(edgeEnd);
		}

		//perform dfs on every node, in O(V+E) time since we will visit every node and check every edge
		LinkedList<Integer> stack = new LinkedList<>();

		for (int i = 0; i < numCourses; i++) {
			dfs(i, antireqs, states, stack);
		}
		if (shouldBreak(stack)) {
			return new int[0];
		}

		int[] res = new int[numCourses];
		int curr = 0;
		for (int i : stack) {
			res[curr] = i;
			curr++;
		}
		return res;
	}

	private void dfs(int node, Map<Integer, List<Integer>> antireqs, State[] states, LinkedList<Integer> stack) {
		if (shouldBreak(stack) || states[node] == State.VISITED) {
			return;
		}
		states[node] = State.VISITING;

		List<Integer> neighbours = antireqs.get(node);
		for (int neighbour : neighbours) {
			if (states[neighbour] == State.VISITING) {
				stack.push(-1); //signal break out and return empty array
				return;
			}
			if (states[neighbour] == State.UNVISITED) {
				dfs(neighbour, antireqs, states, stack);
			}
		}

		if (shouldBreak(stack)) return;

		stack.push(node);
		states[node] = State.VISITED;
	}

	private boolean shouldBreak(LinkedList<Integer> stack) {
		return !stack.isEmpty() && stack.peek() == -1;
	}
}

enum State {
	UNVISITED,
	VISITING,
	VISITED
}