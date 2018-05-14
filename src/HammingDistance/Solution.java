package HammingDistance;

/**
 * Created by Henry in 2017.
 * Problem 461
 */
public class Solution {
	public int hammingDistance(int x, int y) {
		return Integer.bitCount(x^y);
	}
}
