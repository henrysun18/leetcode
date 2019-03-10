class LongestSubstringWithAtMostTwoDistinctCharacters {
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int max = 0;
		char char1 = ' ';
		char char2 = ' ';
		int count1 = 0, count2 = 0;
		int left = 0, right = 0;

		while (right < s.length()) {
			// move right over until 3rd distinct char is found
			while (right < s.length()) {
				char c = s.charAt(right);
				if (char1 == ' ' || c == char1) {
					char1 = c;
					count1++;
				} else if (char2 == ' ' || c == char2) {
					char2 = c;
					count2++;
				} else {
					break;
				}
				right++;
			}

			if (count1 + count2 > max) {
				max = count1 + count2;
			}

			//move left over until we only have one distinct char
			while (true) {
				char c = s.charAt(left);
				if (c == char1) {
					count1--;
				} else if (c == char2) {
					count2--;
				}
				left++;
				if (count1 == 0 || count2 == 0) {
					break;
				}
			}

			//make char1 the char remaining
			if (count2 > 0) {
				count1 = count2;
				char1 = char2;
			}
			char2 = ' ';
			count2 = 0;
		}

		return max;
	}
}
