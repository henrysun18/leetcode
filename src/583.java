class DeleteOperationForTwoStrings {
	public int minDistance(String word1, String word2) {
		//if the first 2 chars are the same, then the min distance is
		// dp[i+1][j+1] if that exists
		//if they're not the same, then the min distance is
		// dp[i][j+1] + 1 or dp[i+1][j] + 1

		int[][] dp = new int[word1.length()+1][word2.length()+1];
		for (int i = 0; i < word1.length(); i++) {
			dp[i][word2.length()] = word1.length() - i;
		}
		for (int i = 0; i < word2.length(); i++) {
			dp[word1.length()][i] = word2.length() - i;
		}

		for (int i = word1.length()-1; i >= 0; i--) {
			for (int j = word2.length()-1; j >= 0; j--) {
				char char1 = word1.charAt(i);
				char char2 = word2.charAt(j);
				if (char1 == char2) {
					dp[i][j] = dp[i+1][j+1];
				} else {
					dp[i][j] = 1 + Math.min(dp[i][j+1], dp[i+1][j]);
				}
			}
		}

		return dp[0][0];
	}


	/*

	public int minDistance(String word1, String word2) {
        //one way is to look at the smaller word, and make a hashmap of all the subwords
        //then look at the larger word and try to find matches

        //it would take O(n^2) time and space to make hashmap of all the words
        //and another O(n^2) time to find the answer

        //word length wont exceed 500, so at most we'll be storing 20k words

        //dont worry about null cases
        String longWord, shortWord;
        if (word1.length() > word2.length()) {
            longWord = word1;
            shortWord = word2;
        } else {
            longWord = word2;
            shortWord = word1;
        }

        Set<String> subWords = new HashSet<>();
        for (int i = 0; i < shortWord.length(); i++) {
            for (int j = i+1; j <= shortWord.length(); j++) {
                subWords.add(shortWord.substring(i, j));
            }
        }

        //go through longer word and try to find a match, starting with substr with lengh equal to shortword's length
        for (int len = shortWord.length(); len > 0; len--) {
            for (int start = 0; start <= longWord.length()-len; start++) {
                if (subWords.contains(longWord.substring(start, start+len))) {
                    return (longWord.length() - len) + (shortWord.length() - len);
                }
            }
        }

        return word1.length() + word2.length(); //words don't share anything in common, must remove everything
    }

	 */
}
