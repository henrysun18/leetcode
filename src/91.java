class DecodeWays {
	public int numDecodings(String s) {
		char[] chs = s.toCharArray();

		if (s == null || s.length() == 0) {
			return 0;
		}

		return numDecodings(chs, 0);
	}

	private int numDecodings(char[] chs, int start) {
		if (start == chs.length) {
			return 1;
		}

		if (chs[start] == '0') {
			return 0;
		}

		if (chs[start] == '1') {
			if (start+1 < chs.length) {
				return numDecodings(chs, start+1) + numDecodings(chs, start+2);
			} else {
				return numDecodings(chs, start+1);
			}
		}

		if (chs[start] == '2') {
			if (start+1 < chs.length && chs[start+1] <= '6') {
				return numDecodings(chs, start+1) + numDecodings(chs, start+2);
			} else {
				return numDecodings(chs, start+1);
			}
		}

		return numDecodings(chs, start+1);
	}
}
