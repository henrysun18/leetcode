class EditDistance {
	public int minDistance(String word1, String word2) {
		// dp relation is...
		// we need n*m matrix to count min number of operations needed to get from first i chars from word1 to first j chars in word2
		int[][] dp = new int[word1.length()+1][word2.length()+1];

		//base case: word1 is empty and word2 is empty -> 0 operations needed
		//base case: word1 is empty and word2 is not -> word2.length operations needed (vice versa for word2 empty)
		for (int i = 0; i <= word1.length(); i++) { //don't forget that i should also be word.length inclusive
			dp[i][0] = i;
		}
		for (int j = 0; j <= word2.length(); j++) {
			dp[0][j] = j;
		}

		for (int i = 1; i <= word1.length(); i++) {
			for (int j = 1; j <= word2.length(); j++) {
				// if last letter is same, answer might be dp[i-1][j-1]
				if (word1.charAt(i-1) == word2.charAt(j-1)) { //MAKE SURE NOT TO TREAT charAt PARAMETER AS "NUM LETTERS", INSTEAD TREAT AS 0-INDEXED
					dp[i][j] = dp[i-1][j-1];
					//System.out.println(1);
				} else {
					//if not the same, then we need to either replace last letter, add, or remove to make them the same
					//check if a replace operation yields the min distance
					int distanceAfterReplace = 1 + dp[i-1][j-1];
					int distanceAfterAdd = 1 + dp[i-1][j];
					int distanceAfterDelete = 1 + dp[i][j-1];
					//if (i == j) {
					dp[i][j] = Math.min(Math.min(distanceAfterReplace, distanceAfterAdd), distanceAfterDelete);
					//} else {
					//    dp[i][j] = Math.min(distanceAfterAdd, distanceAfterDelete);
					//}

					//System.out.println(2);
				}
				//System.out.println("i:" + i + ", j:" + j + " -> " + dp[i][j]);
			}
		}

		return dp[word1.length()][word2.length()];
	}
}
