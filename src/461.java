/**
 * Created by Henry in 2017.
 * Problem 461
 */
class HammingDistance {
	public int hammingDistance(int x, int y) {
		return Integer.bitCount(x^y);
	}
}
