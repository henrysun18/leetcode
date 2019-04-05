class PaintHouse {
	public int minCost(int[][] costs) {
		//can easily do this if we have auxiliary 3*n matrix
		//min cost to paint house i red = min(min cost to paint house i+1 blue, min cost to paint house i+1 green)
		//and similar for house i green/blue
		//then return min(dp[0][0], dp[0][1], dp[0][2])

		if (costs == null || costs.length == 0 || costs[0].length == 0) return 0;

		int[][] minCosts = new int[costs.length][costs[0].length];

		int lastHouse = costs.length-1;
		minCosts[lastHouse][0] = costs[lastHouse][0];
		minCosts[lastHouse][1] = costs[lastHouse][1];
		minCosts[lastHouse][2] = costs[lastHouse][2];

		for (int house = costs.length-2; house >= 0; house--) {
			minCosts[house][0] = costs[house][0] + Math.min(minCosts[house+1][1], minCosts[house+1][2]);
			minCosts[house][1] = costs[house][1] + Math.min(minCosts[house+1][0], minCosts[house+1][2]);
			minCosts[house][2] = costs[house][2] + Math.min(minCosts[house+1][0], minCosts[house+1][1]);
		}

		return Math.min(minCosts[0][0], Math.min(minCosts[0][1], minCosts[0][2]));
	}
}
