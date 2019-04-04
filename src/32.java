class LongestValidParentheses {
	public int longestValidParentheses(String s) {
		//auxiliary array to record the longest valid substring starting at index i
		int[] dp = new int[s.length()];


		for (int i = s.length() - 2; i >= 0; i--) {
			//if it's a closing paren, then longest valid substr is same as dp[i+1]
			char c = s.charAt(i);
			if (c == ')') {
				dp[i] = 0; //not valid if starting with )
			} else {
				// index i has open paren
				if (s.charAt(i+1) == ')') {
					dp[i] = 2 + (i+2 < s.length() ? dp[i+2] : 0);
				} else {
					//current is ( and next starts with (, if next is valid then try to find the next )
					if (dp[i+1] > 0) {
						//open paren beside i is valid! look for closing paren corresponding to i
						int indexOfClosingParen = i + dp[i+1] + 1;
						if (indexOfClosingParen < s.length() && s.charAt(indexOfClosingParen) == ')') {
							//DONT FORGET TO ALSO CHAIN WITH THE DIRECT NEXT VALID SUBSTR AFTER FINDING THE CLOSING PAREN!!
							//ex. (())()() dp[0] should find its partner closing paren at index 3, then with this valid substr, chain it with the subsequent substr!
							dp[i] = 2 + dp[i+1] + (indexOfClosingParen+1 < s.length() ? dp[indexOfClosingParen+1] : 0);
						}
					}
					//if can't find a corresponding closing paren, then dp[i] = 0 since substr starting at i is invalid
				}
			}
			//System.out.println(s.substring(i) + " -> " + dp[i]);
		}

		int max = 0;
		for (int i : dp) {
			max = Math.max(i, max);
		}
		return max;
	}
}
