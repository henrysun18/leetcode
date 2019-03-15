import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class EvaluateDivision {
	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
		// bidirectional graph
		// a -> b is 2.0
		// b -> a is 1 / 2.0
		// we can use adjacency list
		// map each variable to a list of other variables, where this list of other variables is a map of divisor & quotient
		Map<String, Map<String, Double>> adjacencyMap = new HashMap<>();

		for (int i = 0; i < equations.length; i++) {
			String[] equation = equations[i];
			String numerator = equation[0];
			String denominator = equation[1];
			if (!adjacencyMap.containsKey(numerator)) {
				adjacencyMap.put(numerator, new HashMap<>());
			}
			adjacencyMap.get(numerator).put(denominator, values[i]);

			if (!adjacencyMap.containsKey(denominator)) {
				adjacencyMap.put(denominator, new HashMap<>());
			}
			adjacencyMap.get(denominator).put(numerator, 1/values[i]);
		}

		double[] res = new double[queries.length];
		for (int i = 0; i < queries.length; i++) {
			res[i] = evaluate(queries[i], adjacencyMap);
		}
		return res;
	}

	private double evaluate(String[] query, Map<String, Map<String, Double>> adjacencyMap) {
		if (!adjacencyMap.containsKey(query[0]) || !adjacencyMap.containsKey(query[1])) {
			return -1.0;
		}

		Set<String> visited = new HashSet<>();
		List<String> path = new LinkedList<>();

		path.add(query[0]);

		if (!findPath(query[1], visited, path, adjacencyMap)) {
			return -1.0;
		}

		double res = 1.0;
		for (int i = 0; i < path.size()-1; i++) {
			res *= adjacencyMap.get(path.get(i)).get(path.get(i+1));
		}
		return res;
	}

	private boolean findPath(String target, Set<String> visited, List<String> path, Map<String, Map<String, Double>> adjacencyMap) {
		String curr = path.get(path.size()-1);
		visited.add(curr);

		if (curr.equals(target)) {
			return true;
		}

		Set<String> neighbours = adjacencyMap.get(curr).keySet();
		for (String neighbour : neighbours) {
			if (visited.contains(neighbour)) {
				continue;
			}

			path.add(neighbour);

			if (findPath(target, visited, path, adjacencyMap)) {
				return true;
			}

			path.remove(neighbour);
		}

		return false;
	}
}
