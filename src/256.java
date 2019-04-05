class PaintHouse {
	public int minCost(int[][] costs) {
		//can easily do this if we have auxiliary 3*n matrix
		//min cost to paint house i red = min(min cost to paint house i+1 blue, min cost to paint house i+1 green)
		//and similar for house i green/blue
		//then return min(dp[0][0], dp[0][1], dp[0][2])

		if (costs == null || costs.length == 0 || costs[0].length == 0) return 0;

		//min cost of painting next house red/blue/green
		//can also just use the costs matrix itself
		int[] dp = new int[3];

		int lastHouse = costs.length-1;
		dp[0] = costs[lastHouse][0];
		dp[1] = costs[lastHouse][1];
		dp[2] = costs[lastHouse][2];

		for (int house = costs.length-2; house >= 0; house--) {
			int priceIfHouseIsRed = costs[house][0] + Math.min(dp[1], dp[2]);
			int priceIfHouseIsBlue = costs[house][1] + Math.min(dp[0], dp[2]);
			int priceIfHouseIsGreen = costs[house][2] + Math.min(dp[0], dp[1]);
			dp[0] = priceIfHouseIsRed;
			dp[1] = priceIfHouseIsBlue;
			dp[2] = priceIfHouseIsGreen;
		}

		return Math.min(dp[0], Math.min(dp[1], dp[2]));
	}
}
