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

			int left = i-1;
			int right = i+1;
			while (left >= 0 && right < s.length()) {
				if (chs[left] == chs[right]) {
					res++;
					left--;
					right++;
				} else {
					break;
				}
			}
		}

		for (int i = 0; i < s.length() - 1; i++) {
			//chs[i] and chs[i+1] is the start substring
			int left = i;
			int right = i+1;
			while (left >= 0 && right < s.length()) {
				if (chs[left] == chs[right]) {
					res++;
					left--;
					right++;
				} else {
					break;
				}
			}
		}

		return res;
	}
}
