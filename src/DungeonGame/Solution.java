package DungeonGame;

/**
 * Created by Henry on 5/18/2018.
 * Problem 174
 */
public class Solution {
	public static int calculateMinimumHP(int[][] dungeon) {
		int m = dungeon.length;
		int n = dungeon[0].length;
		int[][] minHpDropFromAtoB = new int[m][n];
		minHpDropFromAtoB[m-1][n-1] = dungeon[m-1][n-1];
		for (int i = m-2; i >= 0; i--) {
			int netHpChangeFromAtoB = dungeon[i][n-1] + minHpDropFromAtoB[i+1][n-1];
			if (dungeon[i][n-1] < netHpChangeFromAtoB) {
				minHpDropFromAtoB[i][n-1] = dungeon[i][n-1];
			} else if (netHpChangeFromAtoB <= 0) {
				minHpDropFromAtoB[i][n-1] = netHpChangeFromAtoB;
			} else {
				minHpDropFromAtoB[i][n-1] = 0;
			}
		}
		for (int i = n-2; i >= 0; i--) {
			int netHpChangeFromAtoB = dungeon[m-1][i] + minHpDropFromAtoB[m-1][i+1];
			if (dungeon[m-1][i] < netHpChangeFromAtoB) {
				minHpDropFromAtoB[m-1][i] = dungeon[m-1][i];
			} else if (netHpChangeFromAtoB <= 0) {
				minHpDropFromAtoB[m-1][i] = netHpChangeFromAtoB;
			} else {
				minHpDropFromAtoB[m-1][i] = 0;
			}
		}
		for (int a = m-2; a >= 0; a--) {
			for (int b = n-2; b >= 0; b--) {
				int minNetHpChangeFromAtoB = dungeon[a][b] + Math.max(minHpDropFromAtoB[a+1][b], minHpDropFromAtoB[a][b+1]);
				if (minNetHpChangeFromAtoB <= 0) {
					minHpDropFromAtoB[a][b] = minNetHpChangeFromAtoB;
				} else {
					minHpDropFromAtoB[a][b] = 0;
				}
			}
		}
		return minHpDropFromAtoB[0][0] > 0 ? 1 : -1 * minHpDropFromAtoB[0][0] + 1;
	}

	public static void main(String[] args) {
		int[][] test = new int[][]{
				{-3, 5}
		};
		assert(calculateMinimumHP(test) == 4);
	}
}

