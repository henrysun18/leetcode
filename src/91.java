import java.util.HashMap;
import java.util.Map;

class DecodeWays {
	public int numDecodings(String s) {
		char[] chs = s.toCharArray();

		Map<Integer, Integer> decodingsFromStart = new HashMap<>();

		if (s == null || s.length() == 0) {
			return 0;
		}

		return numDecodings(chs, 0, decodingsFromStart);
	}

	private int numDecodings(char[] chs, int start, Map<Integer, Integer> decodingsFromStart) {
		if (start == chs.length) {
			return 1;
		}

		if (decodingsFromStart.containsKey(start)) {
			return decodingsFromStart.get(start);
		}

		if (chs[start] == '0') {
			return 0;
		}

		int res = 0;

		if (chs[start] == '1') {
			if (start+1 < chs.length) {
				res = numDecodings(chs, start+1, decodingsFromStart) + numDecodings(chs, start+2, decodingsFromStart);
			} else {
				res = numDecodings(chs, start+1, decodingsFromStart);
			}
		} else if (chs[start] == '2') {
			if (start+1 < chs.length && chs[start+1] <= '6') {
				res = numDecodings(chs, start+1, decodingsFromStart) + numDecodings(chs, start+2, decodingsFromStart);
			} else {
				res = numDecodings(chs, start+1, decodingsFromStart);
			}
		} else {
			res = numDecodings(chs, start+1, decodingsFromStart);
		}

		decodingsFromStart.put(start, res);
		return res;
	}
}
