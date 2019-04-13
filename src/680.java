class ValidPalindromeII {
	public boolean validPalindrome(String s) {
		//brute force is to take one character from each position and revalidate, which is O(n) time
		//or we can simply start from the ends and move our way in
		//when there's a conflict, then the only way to have palindrome is if deleting one of the sides works
		if (s == null) return true;

		int left = 0;
		int right = s.length()-1;

		while (left < right) {
			if (s.charAt(left) == s.charAt(right)) {
				left++;
				right--;
			} else {
				//try removing left and check remaining, or remove right and check remaining chars
				return validate(s.substring(left+1, right+1)) || validate(s.substring(left, right));
			}
		}

		return true;
	}

	private boolean validate(String s) {
		int left = 0;
		int right = s.length()-1;
		while (left < right) {
			if (s.charAt(left) != s.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}
