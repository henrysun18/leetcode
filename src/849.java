class MaximizeDistanceToClosestPerson {
	public int maxDistToClosest(int[] seats) {
		//we can just do one pass to find the longest consecutive chain of zeros
		//the ceil divide that length by 2 (or just add 1 then divide by 2)
		int longestChain = 0;
		int chain = 0;

		boolean oneSeen = false;
		int chainFromLeft = 0;
		for (int seat : seats) {
			if (seat == 0) { //failed once due to swappin 1 and 0 here
				chain++;
				if (!oneSeen) {
					chainFromLeft++;
				}
				if (chain > longestChain) {
					longestChain = chain;
				}
			} else {
				oneSeen = true;
				chain = 0;
			}
		}

		int chainFromRight = chain;

		return Math.max((longestChain + 1) / 2, Math.max(chainFromLeft, chainFromRight)); //o fk forgot to consider edge case of [1,0,0,0] where we don't need to divide
	}
}
