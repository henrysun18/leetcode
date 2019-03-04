class PalindromicSubstring {
	public int countSubstrings(String s) {
		if (s == null) {
			return 0;
		}

		int res = 0;
		char[] chs = s.toCharArray();

		for (int i = 0; i < s.length(); i++) {
			// chs[i] is the start point
			res++;

			res += countSubstrings(chs, i-1, i+1);
		}

		for (int i = 0; i < s.length() - 1; i++) {
			//chs[i] and chs[i+1] is the start substring
			res += countSubstrings(chs, i, i+1);
		}

		return res;
	}

	private int countSubstrings(char[] chs, int left, int right) {
		int count = 0;

		while (left >= 0 && right < chs.length) {
			if (chs[left] == chs[right]) {
				count++;
				left--;
				right++;
			} else {
				break;
			}
		}

		return count;
	}
}
