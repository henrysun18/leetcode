import java.util.HashMap;
import java.util.Map;

class MinimumWindowSubstring {
	public String minWindow(String s, String t) {

		// have a counter = t.length
		// populate a charsNeeded map to map A = 1, B = 1, C = 1
		// have a windowStart = 0, windowEnd = 1;
		// minStart = 0;
		// minEnd = 0;
		// keep increasing windowEnd while decreasing counter and charsNeeded values until counter = 0
		// update minStart/minEnd with windowStart/windowEnd if needed
		// increase windowStart until counter is just about to be incremented TWICE
		// keep windowStart and move windowEnd until we meet all again

		int count = t.length();
		Map<Character, Integer> charsNeeded = new HashMap<>();
		int windowStart = 0, windowEnd = 0, minStart = -1, minEnd = -1;

		for (char c : t.toCharArray()) {
			int numNeeded = charsNeeded.getOrDefault(c, 0);
			charsNeeded.put(c, numNeeded + 1);
		}

		//move start cursor to first possible windowStart
		while (windowStart < s.length()) {
			if (charsNeeded.containsKey(s.charAt(windowStart))) {
				windowEnd = windowStart;
				break;
			} else {
				windowStart++;
			}
		}

		boolean decreasingCount = true;
		while (windowStart < s.length() && windowEnd < s.length()) {
			if (decreasingCount) {
				char c = s.charAt(windowEnd);
				Integer numNeeded = charsNeeded.get(c);
				if (numNeeded != null) {
					charsNeeded.put(c, numNeeded-1);
					if (numNeeded > 0) {
						count--; // if we find multiple B's, we still need to keep looking for A's and C's
					}
				}

				if (count == 0) {
					if (minStart == -1 || windowEnd-windowStart < minEnd-minStart) {
						minStart = windowStart;
						minEnd = windowEnd;
					}
					decreasingCount = false;
				} else {
					windowEnd++;
				}
			} else {
				char cToRemove = s.charAt(windowStart);
				Integer numNeeded = charsNeeded.get(cToRemove);
				if (numNeeded != null) {
					if (count == 1 && numNeeded >= 0) {
						decreasingCount = true;
						windowEnd++;
						continue;
					} else {
						charsNeeded.put(cToRemove, numNeeded+1);
						if (numNeeded >= 0) {
							count++;
						}
					}
				}
				windowStart++;

				// if increasing windowStart still allows window to match t
				if (count == 0) {
					if (windowEnd-windowStart < minEnd-minStart) {
						minStart = windowStart;
						minEnd = windowEnd;
					}
				}
			}
		}


		if (minStart == -1) return "";
		return s.substring(minStart, minEnd+1);
	}
}