import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class StrobogrammaticNumberII {
	public List<String> findStrobogrammatic(int n) {
		// we can use backtracking
		// bsaically make a burger of strobogrammatic pairs
		// can start with 1, 6, 8, 9
		// can sandwich 0, 1, 6, 8, 9 in the middle if given as pairs
		// can sandwich 0, 1, 8 if n is odd and we're at the middle

		if (n == 0) return new ArrayList<>();
		if (n == 1) return Arrays.asList("0", "1", "8");

		List<String> res = new LinkedList<>();

		char[] outer = new char[]{'1', '6', '8', '9'};
		char[] inner = new char[]{'0', '1', '6', '8', '9'};
		char[] middle = new char[]{'0', '1', '8'};
		Map<Character, Character> pairings = new HashMap<>();
		pairings.put('0', '0');
		pairings.put('1', '1');
		pairings.put('6', '9');
		pairings.put('8', '8');
		pairings.put('9', '6');

		char[] chs = new char[n];

		for (char out : outer) {
			chs[0] = out;
			chs[n-1] = pairings.get(out);

			find(chs, 1, inner, middle, pairings, res);
		}

		return res;
	}

	private void find(char[] chs, int layer, char[] inner, char[] middle, Map<Character, Character> pairings, List<String> res) {
		int n = chs.length;
		if (n%2 == 1 && layer == n/2) {
			for (char mid : middle) {
				chs[layer] = mid;
				res.add(String.valueOf(chs));
			}
		} else if (layer < n/2) {
			for (char in : inner) {
				chs[layer] = in;
				chs[n-layer-1] = pairings.get(in); //pls don't forget the -1 since it's indexes we're dealing with

				find(chs, layer+1, inner, middle, pairings, res);
			}
		} else {
			res.add(String.valueOf(chs));
		}
	}
}
